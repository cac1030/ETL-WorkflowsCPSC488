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

	# receive the file infos
	patient_name, filename, filesize = data.split(SEPARATOR)
	# remove absolute path if there is
	filename = os.path.basename(filename)
	# convert to integer
	filesize = int(filesize)

	# start receiving the data file from the socket
	# and writing to the file stream
	progress = tqdm.tqdm(range(filesize), f"Receiving {filename}", unit="B", unit_scale=True, unit_divisor=1024)
	with open("client.zip", "wb") as f:
		while True:
			# read 1024 bytes from the socket (receive)
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
	return "\nREQ_UPLOAD_FILE by " + address[0] + " fulfilled"

def add_patient(patient_data):
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

	return"\nREQ_PATIENT_ADD by " + address[0] + " fulfilled"

def edit_patient(patient_data):
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
	cmdstrs.append(f"imeta add -C {dir_path} middle_name {data['middle_name']}")
	cmdstrs.append(f"imeta add -C {dir_path} ethnicity {data['ethnicity']}")

	for cmd in cmdstrs:
		os.system(cmd)

	return"\nREQ_PATIENT_EDIT by " + address[0] + " fulfilled"

def process_request(client_socket, address):
	# receive request from client
	received = client_socket.recv(BUFFER_SIZE).decode()

	print(f"received {received} from {address}")

	request, data = received.split('!')

	print(request)

	switcher = {
		"REQ_UPLOAD_FILE": receive_file,
		"REQ_PATIENT_ADD": add_patient,
		"REQ_PATIENT_EDIT": edit_patient
		}

	args = {
		"REQ_UPLOAD_FILE": [client_socket, address, data],
		"REQ_PATIENT_ADD": (data),
		"REQ_PATIENT_EDIT": (data)
		}
	message = switcher[request](args[request])
	print(message)

def fetch_patient_data():
	cmdstrs = []
	cmdoutputs = []

	cmdstrs.append("ils /tempZone/home/public | awk -F '/' '{print $5}'")

	for cmd in cmdstrs:
		result = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE)
		cmdoutputs.extend(result.stdout.decode('utf-8').split('\n'))

	for output in cmdoutputs:
		print(output)

	print(cmdoutputs[1])

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

# setup_server()
# #while True:
# client_socket, address = handle_connection()
# process_request(client_socket, address)

# close the server socket
# s.close()

fetch_patient_data()

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
