import socket
import tqdm
import sys
import os

REQUEST = "REQ_FETCH"
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

# receive and write
received = s.recv(BUFFER_SIZE).decode()
with open('patient_data.json', w+) as f:
    f.write(received)

# close the socket
s.close()
