import socket
import tqdm
import sys

REQUEST = "REQ_FETCH_PATIENT_DATA"
DATA = "null"

# transfer config
HOST = "52.168.52.180"
# HOST = "localhost"
PORT = 5001
SEPARATOR = "<SEPARATOR>"
BUFFER_SIZE = 4096
s = socket.socket()

# connect to the server
print(f"[+] Connecting to {HOST}:{PORT}")
s.connect((HOST, PORT))
print("[+] Connected.")

# send the request
s.send(f"{REQUEST}!{DATA}".encode())

# receive
received = s.recv(BUFFER_SIZE).decode()
print(f"received response from server: {received}")
filename, filesize = received.split(SEPARATOR)

#transform
filename = os.path.basename(filename)
filesize = int(filesize)

# receive file
progress = tqdm.tqdm(range(filesize), f"Receiving {filename}", unit="B", unit_scale=True, unit_divisor=1024)
with open(filename, "wb") as f:
    while True:
        # read bytes from the socket (receive)
        bytes_read = s.recv(BUFFER_SIZE)
        if not bytes_read:
            # nothing is received
            # file transmitting is done
            break
        # write to the file the bytes we just received
        f.write(bytes_read)
        # update the progress bar
        progress.update(len(bytes_read))

# close the socket
s.close()
