import socket
import zipfile
import json
import tqdm
import os
import subprocess
import sys
import time

# setup and general handling of connections
def setup_server():
        global s
        global SERVER_HOST
        global SERVER_PORT
        global BUFFER_SIZE
        global SEPARATOR

        # device's IP address
        SERVER_HOST = "0.0.0.0"
        SERVER_PORT = 5001

        # receive 4096 bytes each time
        BUFFER_SIZE = 4096
        SEPARATOR = "[-]"

        # create and bind
        try:
                s = socket.socket()
                s.bind((SERVER_HOST, SERVER_PORT))
        except socket.error as e:
                print(f"[X] Error creating and binding socket: {e}")
                sys.exit(1)
        else:
                print(f"[...] Socket created and bound successfully")

def handle_connection():
        # enabling our server to accept connections
        # 5 here is the number of unaccepted connections that
        # the system will allow before refusing new connections
        s.listen(5)
        print(f"[*] Listening as {SERVER_HOST}:{SERVER_PORT}")

        # accept connection
        try:
                client_socket, address = s.accept()
        except socket.error as e:
                print(f"[X] Error establishing connection: {e}")
                sys.exit(1)
        else:
                print(f"[+] {address} is connected.")

        return (client_socket, address)

def process_request(client_socket, address):
        # receive request from client
        try:
                received = client_socket.recv(BUFFER_SIZE).decode()
        except socket.error as e:
                print(f"[X] Error receiving request: {e}")
                sys.exit(1)
        else:
                print(f"[<] Received {received} from {address}")
                request, data = received.split('!')

        # execute function based on request
        switcher = {
                "REQ_UPLOAD_FILE": receive_file,
                "REQ_PATIENT_ADD": add_patient,
                "REQ_PATIENT_EDIT": edit_patient,
                "REQ_FETCH": send_patients_info,
                "REQ_FILES": send_patient_files
                }

        args = {
                "REQ_UPLOAD_FILE": [client_socket, address, data],
                "REQ_PATIENT_ADD": [data, address],
                "REQ_PATIENT_EDIT": [data, address],
                "REQ_FETCH": [client_socket, address],
                "REQ_FILES": [client_socket, address, data]
                }

        message = switcher[request](args[request])
        print(message)

# corresponding functions for each client script
def receive_file(args):
        client_socket = args[0]
        address = args[1]
        data = args[2]

        # receive and transform the file infos
        patient_name, filename, filesize = data.split(SEPARATOR)
        filename = os.path.basename(filename)
        filesize = int(filesize)

        # receive file
        progress = tqdm.tqdm(range(filesize), f"[<] Receiving {filename}", unit="B", unit_scale=True, unit_divisor=1024)
        with open("client.zip", "wb") as f:
                        try:
                                while True:
                                        bytes_read = client_socket.recv(BUFFER_SIZE)
                                        if not bytes_read:
                                                        break
                                        f.write(bytes_read)
                                        progress.update(len(bytes_read))
                        except socket.error as e:
                                print(f"[X] Error receiving file: {e}")
                                sys.exit(1)
                        except IOError as e:
                                print(f"[X] Error writing file: {e}")
                                sys.exit(1)
                        else:
                                print(f"[<] {filename} received from {address}")

        unzip_file("./client.zip")
        put_to_irods(filename, patient_name)

        return f"[O] REQ_UPLOAD_FILE by {address} fulfilled"

def add_patient(args):
        patient_data = args[0]
        address = args[1]

        try:
                data = json.loads(patient_data)
        except ValueError as e:
                print(f"Error loading json: {e}")
                sys.exit(1)

        dir_path = f"/tempZone/home/public/{data['last_name'].upper()}_{data['first_name'].upper()}"

        # build a command sequence
        cmdstrs = []
        cmdstrs.append(f"imkdir {dir_path}")
        cmdstrs.append(f"imeta add -C {dir_path} first_name {data['first_name']}")
        cmdstrs.append(f"imeta add -C {dir_path} last_name {data['last_name']}")
        cmdstrs.append(f"imeta add -C {dir_path} date_created {data['date_created']}")
        cmdstrs.append(f"imeta add -C {dir_path} date_modified {data['date_modified']}")
        cmdstrs.append(f"imeta add -C {dir_path} height {data['height']}")
        cmdstrs.append(f"imeta add -C {dir_path} weight {data['weight']}")
        cmdstrs.append(f"imeta add -C {dir_path} dob {data['dob']}")
        cmdstrs.append(f"imeta add -C {dir_path} sex {data['sex']}")

        # optional fields
        cmdstrs.append(f"imeta add -C {dir_path} middle_name {data['middle_name']}")
        cmdstrs.append(f"imeta add -C {dir_path} ethnicity {data['ethnicity']}")

        for cmd in cmdstrs:
                try:
                        subprocess.run(cmd, shell = True, check = True)
                except subprocess.CalledProcessError as e:
                        print(f"Error creating new patient file: {e}")
                        sys.exit(1)

        return f"[O] REQ_PATIENT_ADD by {address} fulfilled"

def edit_patient(args):
        patient_data = args[0]
        address = args[1]

        try:
                data = json.loads(patient_data)
        except ValueError as e:
                print(f"[X] Error loading json: {e}")
                sys.exit(1)

        dir_path = f"/tempZone/home/public/{data['last_name'].upper()}_{data['first_name'].upper()}"

        # build a command sequence
        cmdstrs = []
        cmdstrs.append(f"imeta set -C {dir_path} first_name {data['first_name']}")
        cmdstrs.append(f"imeta set -C {dir_path} last_name {data['last_name']}")
        cmdstrs.append(f"imeta set -C {dir_path} date_created {data['date_created']}")
        cmdstrs.append(f"imeta set -C {dir_path} date_modified {data['date_modified']}")
        cmdstrs.append(f"imeta set -C {dir_path} height {data['height']}")
        cmdstrs.append(f"imeta set -C {dir_path} weight {data['weight']}")
        cmdstrs.append(f"imeta set -C {dir_path} dob {data['dob']}")
        cmdstrs.append(f"imeta set -C {dir_path} sex {data['sex']}")
        cmdstrs.append(f"imeta set -C {dir_path} ethnicity {data['ethnicity']}")

        # optional fields
        cmdstrs.append(f"imeta set -C {dir_path} middle_name {data['middle_name']}")
        cmdstrs.append(f"imeta set -C {dir_path} ethnicity {data['ethnicity']}")

        for cmd in cmdstrs:
                try:
                        subprocess.run(cmd, shell = True, check = True)
                except subprocess.CalledProcessError as e:
                        print(f"[X] Error editing patient file: {e}")
                        sys.exit(1)

        return f"[O] REQ_PATIENT_EDIT by {address} fulfilled"

def send_patients_info(args):
        client_socket = args[0]
        address = args[1]

        patient_data = []

        # fetch patient dir names
        cmd = "ils /tempZone/home/public | awk -F '/' '/_/ {print $5}'"
        dir_names = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8').splitlines()

        # fetch patient dir metadata
        for dir_name in dir_names:
                meta = {}
                cmd = f"imeta ls -C /tempZone/home/public/{dir_name} | awk '/^[av]/' | cut -f2 -d ' '"
                result = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8').splitlines()
                for i in range(0, len(result), 2):
                        meta[result[i]] = result[i+1]
                patient_data.append(meta)

        # send to client
        try:
                client_socket.send(json.dumps(patient_data).encode())
        except OSError as e:
                print(f"[X] Error sending patient data: {e}")
                sys.exit(1)
        else:
                print(f"[>] Patient data sent successfully")

        return f"[O] REQ_FETCH by {address} fulfilled"

def send_patient_files(args):
        now = time.time()
        AGE_YEAR = now - 31,536,000
        AGE_MONTH = now - 2,629,746

        client_socket = args[0]
        address = args[1]
        data = args[2]

        file_data = []
        patient_name, search_terms = data.split(SEPARATOR)
        patient_dir = '/tempZone/home/public/' + patient_name

        cmd = 'icd ' + patient_dir      # navigate to patient dir
        subprocess.run(cmd, shell = True)

        # TODO: set up protocol for search_terms
        if search_terms == 'YEAR':
                cmd = f"imeta qu -d date_create '>=' {AGE_YEAR} | awk '/dataObj:/ {{print $2}}'"

        file_matches = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8').splitlines()

        # retreive file metadata and parse to json format in a dict
        for match in file_matches:
                meta = {}
                cmd = f"imeta ls -d '{match}' | awk '/^[av]/' | cut -f2 -d ' '"
                try:
                        result = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8').splitlines()
                except subprocess.CalledProcessError as e:
                        print(f"[X] Error retreiving file data: {e}")
                        sys.exit(1)
                for i in range(0, len(result), 2):
                        meta[result[i]] = result[i+1]
                file_data.append(meta)

        # send to client
        try:
                client_socket.send(json.dumps(file_data).encode())
        except OSError as e:
                print(f"[X] Error sending file data: {e}")
                sys.exit(1)
        else:
                print(f"[>] File data sent successfully")

        return f"[O] REQ_FILES by {address} fulfilled"

# utility
def unzip_file(path):
        try:
                with zipfile.ZipFile(path, 'r') as zip_ref:
                        zip_ref.extractall("./temp")
        except Exception as e:
                print(f"Error unzipping file: {e}")
                sys.exit(1)
        else:
                os.system("rm client.zip")

def put_to_irods(filename, patient_name):
        # read data from file
        try:
                with open("./temp/meta.txt") as f:
                        data = json.load(f)
        except IOError as e:
                print(f"[X] Error opening metadata file: {e}")
                sys.exit(1)
        except ValueError as e:
                print(f"[X] Error loading json: {e}")
                sys.exit(1)

        # find any files matching the name exactly, as well as any names that match the format of a copy
        cmd = f"ils /tempZone/home/public/{patient_name} | awk '/^  example\(?[0-9]*?\)?.txt$/' | cut -d ' ' -f3"
        output = subprocess.run(cmd, shell = True, stdout=subprocess.PIPE).stdout.decode('utf-8')
        namelets = filename.split('.')
        count = output.count(namelets[0])
        if count > 0:   # if filename exists
                copy_number = count
                for x in range(1, count-1):
                        if output.find(f"{namelets[0]}({x}).{namelets[1]}") == -1:
                                copy_number = x
                                break
                # build new filename using copy format
                new_filename = f"'{namelets[0]}({copy_number}).{namelets[1]}'"
        else:
                # use original filename
                new_filename = filename

        # build the string of the command that will iput the file with metadata attached
        cmd = f"iput ./temp/{filename} /tempZone/home/public/{patient_name}/{new_filename} --metadata=\"date_create;{data['date']};;title;{data['title']};;overseeing;{data['overseeing']};;notes;{data['notes']};;\""

        try:
                subprocess.run(cmd, shell = True, check = True)
        except subprocess.CalledProcessError as e:
                print(f"[X] Error executing iput: {e}")
                sys.exit(1)
        else:
                print(f"[O] {filename} successfully put to database")

#########################################################

setup_server()
client_socket, address = handle_connection()
process_request(client_socket, address)
s.close()
