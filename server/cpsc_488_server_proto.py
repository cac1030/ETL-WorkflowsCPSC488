import socket
import tqdm
import os

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
	
# files always come as a file-metadata pair
def receive_file(client_socket, address, data):
	# receive the file infos
	filename, filesize = data.split(SEPARATOR)
	# remove absolute path if there is
	filename = os.path.basename(filename)
	# convert to integer
	filesize = int(filesize)
	
	# start receiving the data file from the socket
	# and writing to the file stream
	progress = tqdm.tqdm(range(filesize), f"Receiving {filename}", unit="B", unit_scale=True, unit_divisor=1024)
	with open(filename, "wb") as f:
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
	
	#put_to_irods(filename)
	
def process_request(client_socket, address):
	# receive request from client
	received = client_socket.recv(BUFFER_SIZE).decode()
	
	print(f"received {received} from {address}")
	
	request, data = received.split('!')
	
	switcher = {
		"REQ_DOWNLOAD_META_SEARCH": "REQ_DOWNLOAD_META_SEARCH",
		"REQ_DOWNLOAD_META_DEFAULT": "REQ_DOWNLOAD_META_DEFAULT",
		"REQ_DOWNLOAD_FILE": "REQ_DOWNLOAD_FILE",
		"REQ_UPLOAD_FILE": receive_file(client_socket, address, data)
		}
	
	switcher.get(request, lambda: "Invalid request")

def put_to_irods(filename):
	os.system("iput ...")
	
setup_server()
while True:
	client_socket, address = handle_connection()
	process_request(client_socket, address)
	
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
	
	
#def upload(file, meta):
	# parse metadata and upload file to base
	# iput file|temporaryStorageDir
	
#def input_to_terminal(command):