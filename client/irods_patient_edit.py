import socket
import tqdm
import sys

trans = transaction.Request()
trans.connect()
trans.send_req("REQ_PATIENT_ADD", sys.argv[1])
