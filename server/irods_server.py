import socket
import zipfile
import json
import tqdm
import os
import subprocess

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
	SEPARATOR = "<SEPARATOR>"

	# create the TCP server socket
	s = socket.socket()

	# bind the socket to our local address
	s.bind((SERVER_HOST, SERVER_PORT))

def handle_connection():
	# enabling our server to accept connections
	# 5 here is the number of unaccepted connections that
	# the system will allow before refusing new connections
	s.listen(5)
	print(f"\n[*] Listening as {SERVER_HOST}:{SERVER_PORT}")

	# accept connection if there is any
	client_socket, address = s.accept()
	# if below code is executed, that means the sender is connected
	print(f"[+] {address} is connected.")

	return (client_socket, address)

def receive_file(args):
	client_socket = args[0]
	address = args[1]
	data = args[2]

	# receive and transform the file infos
	patient_name, filename, filesize = data.split(SEPARATOR)
	filename = os.path.basename(filename)
	filesize = int(filesize)

	# receive file
	progress = tqdm.tqdm(range(filesize), f"Receiving {filename}", unit="B", unit_scale=True, unit_divisor=1024)
	with open("client.zip", "wb") as f:
		while True:
			# read bytes from the socket (receive)
			bytes_read = client_socket.recv(BUFFER_SIZE)
			if not bytes_read:
				# nothing is received
				# file transmitting is done
				break
			# write to the file the bytes we just received
			f.write(bytes_read)
			# update the progress bar
			progress.update(len(bytes_read))

	unzip_file("./client.zip")
	put_to_irods(filename, patient_name)
	return f"[!] REQ_UPLOAD_FILE by {address} fulfilled"

def add_patient(args):
	patient_data = args[0]
	address = args[1]

	# TODO: validation (dir already exists), optional fields functionality
	data = json.loads(patient_data)
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
		os.system(cmd)

	return f"[!] REQ_PATIENT_ADD by {address} fulfilled"

def edit_patient(args):
	patient_data = args[0]
	address = args[1]

	data = json.loads(patient_data)
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
		os.system(cmd)

	return f"[!] REQ_PATIENT_EDIT by {address} fulfilled"

def fetch_patient_data(args):
	client_socket = args[0]
	address = args[1]

	data = []

	# fetch patient dir names
	cmd = "ils /tempZone/home/public | awk -F '/' '/_/ {print $5}'"
	dir_names = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8').splitlines()

	# fetch patient dir metadata
	for dir_name in dir_names:
		meta = {}
		cmd = f"imeta ls -C /tempZone/home/public/{dir_name} | awk '/^[av]/ {{print}}' | cut -f2 -d ' '"
		result = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8').splitlines()
		for i in range(0, len(result), 2):
			meta[result[i]] = result[i+1]
		data.append(meta)

	# send to client
	filename = "./temp/patient_json.json"
	filesize = os.path.getsize(filename)

	try:
		client_socket.send(json.dumps(data).encode())
	except OSError as e:
		return e

	return f"[!] REQ_FETCH by {address} fulfilled"

def process_request(client_socket, address):
	# receive request from client
	received = client_socket.recv(BUFFER_SIZE).decode()

	print(f"[!] Received {received} from {address}")

	request, data = received.split('!')

	switcher = {
		"REQ_UPLOAD_FILE": receive_file,
		"REQ_PATIENT_ADD": add_patient,
		"REQ_PATIENT_EDIT": edit_patient,
		"REQ_FETCH": fetch_patient_data
		}

	args = {
		"REQ_UPLOAD_FILE": [client_socket, address, data],
		"REQ_PATIENT_ADD": [data, address],
		"REQ_PATIENT_EDIT": [data, address],
		"REQ_FETCH": [client_socket, address]
		}
	message = switcher[request](args[request])
	print(message)

def download_meta_default(addr, patient_name):
    # supplies metadata on the most recently accessed or uploaded patient files
	# if date_create or date_modify are within the last (x amount of time)
	cmdstr = "imeta qu -d date_create like 12/__/2020 | awk '/dataObj:/ {print $2}'"
	# imeta qu -d date_create_month 'n<=' 12 | awk '/dataObj:/ {print $2}'
	print(cmdstr)

def unzip_file(path):
	with zipfile.ZipFile(path, 'r') as zip_ref:
		zip_ref.extractall("./temp")
	os.system("rm client.zip")

def put_to_irods(filename, patient_name):
	with open("./temp/meta.txt") as f:
		data = json.load(f)

	# build the string of the command that will iput the file with metadata attached
	cmdstr = f"iput ./temp/{filename} /tempZone/home/public/{patient_name}/{filename} --metadata=\"date_create;{data['date']};;title;{data['title']};;overseeing;{data['overseeing']};;notes;{data['notes']};;\""
	os.system("echo " + cmdstr)
	os.system(cmdstr)

setup_server()
#while True:
client_socket, address = handle_connection()
try:
	process_request(client_socket, address)
except Exception as e:
	print(f"something went wrong: {e}")
finally:
	# close the server socket
	s.close()



#def update_client_meta:
    # for each loop that goes through the client whitelist and sends each IP
    # a JSON file with updated patient metadata

#def download_meta_default(addr, patient):
    # supplies metadata on the most recently accessed or uploaded patient files

#def download_meta_search(addr, patient, terms):
    # supplies metadata on patient files that match the search terms provided

#def download_file(addr, patient, path):
    # send the full specified file to the client
	#file, meta = s.recvfrom(256)
	#command = iput file
