import socket
import tqdm
import sys
import os

REQUEST = "REQ_FETCH"
DATA = "null"

# transfer config
HOST = "54.227.89.39"
# HOST = "localhost"
PORT = 5001
SEPARATOR = "[-]"
BUFFER_SIZE = 4096
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

# send the request
try:
    s.send(f"{REQUEST}!{DATA}".encode())
except Exception as e:
    print(f"[X] Sending request failed: {e}")
    sys.exit(1)
else:
    print(f"[>] {REQUEST} sent")

# receive and write
try:
    with open("patient_data.json", "wb") as f:
        while True:
            bytes_read = s.recv(BUFFER_SIZE)
            if not bytes_read:
            	break
            f.write(bytes_read)
except Exception as e:
    print(f"[X] Receiving file failed: {e}")
    sys.exit(1)
else:
	size = os.path.getsize("patient_data.json")
	if size <= 2:
		print(f"[!<] Received empty file")
	else:
		print(f"[<] Received data from server | {size} bytes")
finally:
    s.close()
