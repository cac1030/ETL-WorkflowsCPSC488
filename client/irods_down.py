import argparse
import os
import socket
import transaction
import zipfile

# parse arguments
parser = argparse.ArgumentParser()
parser.add_argument("patient_name",
                    help="the patient whose record is the destination")
parser.add_argument("filename",
                    help="the name of the file being uploaded")
args = parser.parse_args()

# build outgoing data string
SEPARATOR = "[-]"
data = f"{args.patient_name}{SEPARATOR}{args.filename}"

# connect and send request
trans = transaction.Request()
trans.connect()
trans.send_req("REQ_DOWNLOAD_FILE", data)

# receive and write file
try:
    with open(filename, "wb") as f:
        f.write(trans.recv_response())
except OSError as e:
    print(f"[X] Writing to file failed: {e}")
    s.shutdown(socket.SHUT_RDWR)
    s.close()
    sys.exit(1)
else:
    size = os.path.getsize(filename)
    print(f"[<] Received data from server | {size} bytes")
