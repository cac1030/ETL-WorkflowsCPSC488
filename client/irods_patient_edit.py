import socket
import tqdm
import sys

REQUEST = "REQ_PATIENT_EDIT"
DATA = "{\"first_name\":\"james\",\"last_name\":\"jamison\",\"date_created\":\"01/02/2019\",\"date_modified\":\"03/16/2021\",\"height\":\"75\",\"weight\":\"230\",\"dob\":\"05/19/2000\",\"sex\":\"male\",\"ethnicity\":\"white\"}"
# DATA = sys.argv[1]

# transfer config
HOST = "52.168.52.180"
# HOST = "localhost"
PORT = 5001
BUFFER_SIZE = 4096 # send 4096 bytes for each time step

s = socket.socket()

# connect to the server
print(f"[+] Connecting to {HOST}:{PORT}")
s.connect((HOST, PORT))
print("[+] Connected.")

# send the request type, filename, and filesize
s.send(f"{REQUEST}!{DATA}".encode())

# close the socket
s.close()
