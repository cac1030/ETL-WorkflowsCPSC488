import socket
import tqdm

REQUEST = "REQ_PATIENT_EDIT"
DATA = sys.argv[1]

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
s.send(f"{REQUEST}!{DATA}.encode())

# close the socket
s.close()