import socket
import tqdm
import os
import sys
import zipfile

def zip_file(filename):
	with zipfile.ZipFile('client.zip', 'w') as zip:
		zip.write(filename)
		zip.write('meta.txt')
	return os.path.getsize('client.zip')

REQUEST = "REQ_UPLOAD_FILE"
PATIENT_NAME = "DOE_JANE"
SEPARATOR = "<SEPARATOR>"
BUFFER_SIZE = 4096 #send 4096 bytes for each time step

# addressing
#host = "52.168.52.180"
host = "localhost"
port = 5001

filename = sys.argv[1]
filesize = zip_file(filename)

# create the client socket
s = socket.socket()

# connect to the server
print(f"[+] Connecting to {host}:{port}")
s.connect((host, port))
print("[+] Connected.")

# send the request type, filename, and filesize
s.send(f"{REQUEST}!{PATIENT_NAME}{SEPARATOR}{filename}{SEPARATOR}{filesize}".encode())

# start sending the file
progress = tqdm.tqdm(range(filesize), f"Sending {filename}", unit="B", unit_scale=True, unit_divisor=1024)
with open('client.zip', "rb") as f:
    while True:
        # read the bytes from the file
        bytes_read = f.read(BUFFER_SIZE)
        if not bytes_read:
            # file transmitting is done
            break
        # we use sendall to assure transimission in 
        # busy networks
        s.sendall(bytes_read)
        # update the progress bar
        progress.update(len(bytes_read))
# close the socket
s.close()