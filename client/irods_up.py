import socket
import tqdm
import os
import sys
import zipfile
import transaction

def zip_file(filename):
	with zipfile.ZipFile('client.zip', 'w') as zip:
		zip.write(filename)
		zip.write('meta.txt')
	return os.path.getsize('client.zip')

# build outgoing data string
SEPARATOR = "[-]"
patient_name = sys.argv[1]
filename = sys.argv[2]
filesize = zip_file(filename)
data = f"{patient_name}{SEPARATOR}{filename}{SEPARATOR}{filesize}"

# connect and send
trans = transaction.Request()
trans.connect()
trans.send_req("REQ_UPLOAD_FILE", data)
trans.send_file(filename, filesize)
