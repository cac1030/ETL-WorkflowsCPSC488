import socket
import tqdm
import sys
import transaction

parser = argparse.ArgumentParser()
group = parser.add_mutually_exclusive_group(required=True)
group.add_argument("-a", "--add", action="store_true", help="add new patient")
group.add_argument("-e", "--edit", action="store_true", help="edit existing patient")
group.add_argument("-f", "--fetch", action="store_true", help="fetch data on all patients")
parser.add_argument("json", help="the json string containing new patient data")
args = parser.parse_args()

trans = transaction.Request()
trans.connect()
if args.add:
    trans.send_req("REQ_PATIENT_ADD", sys.argv[1].upper())
elif args.edit:
    trans.send_req("REQ_PATIENT_EDIT", sys.argv[1].upper())
elif args.fetch:
    trans.send_req("REQ_PATIENT_FETCH", "null")

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
