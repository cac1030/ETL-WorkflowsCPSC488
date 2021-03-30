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
PATIENT_NAME = sys.argv[1]

# transfer config
HOST = "54.227.89.39"
# HOST = "localhost"
PORT = 5001
SEPARATOR = "[-]"
BUFFER_SIZE = 4096

filename = sys.argv[2]
filesize = zip_file(filename)

# create the client socket
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

# send the request, filename, and filesize
try:
    s.send(f"{REQUEST}!{PATIENT_NAME}{SEPARATOR}{filename}{SEPARATOR}{filesize}".encode())
except Exception as e:
    print(f"[X] Sending request failed: {e}")
    sys.exit(1)
else:
    print(f"[>] {REQUEST} sent")

# send the file
try:
	progress = tqdm.tqdm(range(filesize), f"Sending {filename}", unit="B", unit_scale=True, unit_divisor=1024)
	with open('client.zip', "rb") as f:
		while True:
			bytes_read = f.read(BUFFER_SIZE)
			if not bytes_read:
				break
			s.sendall(bytes_read)
			progress.update(len(bytes_read))
except Exception as e:
	print(f"[X] Sending file failed: {e}")
	sys.exit(1)
else:
	print(f"[>] File sent")
finally:
	s.close()
