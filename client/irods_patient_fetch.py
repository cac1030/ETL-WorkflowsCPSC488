import socket
import tqdm
import sys
import os
import transaction

trans = transaction.Request()
trans.connect()
trans.send_req("REQ_FETCH", "null")
