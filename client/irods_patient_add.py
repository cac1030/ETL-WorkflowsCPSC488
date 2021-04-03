import socket
import tqdm
import sys
import transaction

trans = transaction.Request()
trans.connect()
trans.send_req("REQ_PATIENT_ADD", sys.argv[1])
