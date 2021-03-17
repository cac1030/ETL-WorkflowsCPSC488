import socket
import tqdm
import sys

REQUEST = "REQ_PATIENT_ADD"
# DATA = "{\"first_name\":\"james\",\"last_name\":\"jamison\",\"date_created\":\"01/02/2019\",\"date_modified\":\"03/18/2019\",\"height\":\"73\",\"weight\":\"230\",\"dob\":\"05/19/2000\",\"sex\":\"female\",\"ethnicity\":\"white\"}"
DATA = sys.argv[1]

# transfer config
HOST = "52.168.52.180"
# HOST = "localhost"
PORT = 5001

s = socket.socket()

# connect to the server
print(f"[+] Connecting to {HOST}:{PORT}")
s.connect((HOST, PORT))
print("[+] Connected.")

# send the request and associated data
s.send(f"{REQUEST}!{DATA}".encode())

# close the socket
s.close()
