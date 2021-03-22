import socket
import tqdm
import sys

REQUEST = "REQ_PATIENT_EDIT"
DATA = "{\"first_name\":\"james\",\"middle_name\":\"joe\",\"last_name\":\"jamison\",\"date_created\":\"01/02/2019\",\"date_modified\":\"03/16/2021\",\"height\":\"75\",\"weight\":\"230\",\"dob\":\"05/19/2000\",\"sex\":\"male\",\"ethnicity\":\"white\"}"
# DATA = sys.argv[1]

# transfer config
HOST = "54.227.89.39"
# HOST = "localhost"
PORT = 5001

s = socket.socket()

# connect to the server
print(f"[...] Connecting to {HOST}:{PORT}")
try:
	s.connect((HOST, PORT))
except Exception as e:
	print(f"[X] Connection failed: {e}")
    sys.exit(1)
else:
	print("[+] Connected")

# send the request and associated data
try:
    s.send(f"{REQUEST}!{DATA}".encode())
except Exception as e:
    print(f"[X] Send failed: {e}")
    sys.exit(1)
else:
    print(f"[>] {REQUEST} sent")
finally:
    s.close()
