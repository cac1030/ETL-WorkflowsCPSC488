import socket
import tqdm
import sys
import os
import transaction

trans = transaction.Request()
trans.connect()
trans.send_req("REQ_FETCH", "null")

# receive and write
try:
    with open("patient_data.json", "wb") as f:
        f.write(trans.recv_response())
except OSError as e:
    print(f"[X] Writing to file failed: {e}")
    sys.exit(1)
else:
    size = os.path.getsize("patient_data.json")
    if size <= 2:
        print(f"[!<] Received empty file")
    else:
        print(f"[<] Received data from server | {size} bytes")
