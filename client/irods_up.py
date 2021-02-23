import socket
import tqdm
import os
import sys

REQUEST = "REQ_UPLOAD_FILE"
PATIENT_NAME = "DOE_JANE"
SEPARATOR = "<SEPARATOR>"
BUFFER_SIZE = 4096 #send 4096 bytes for each time step

# the ip address or hostname of the server, the receiver
#host = "52.168.52.180"
host = "localhost"
# the port, let's use 5001
port = 5001
# the name of file we want to send, make sure it exists
filename = sys.argv[1]
# get the file size
filesize = os.path.getsize('client.zip')

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