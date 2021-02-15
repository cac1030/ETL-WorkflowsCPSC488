import socket

# Create a UDP socket and bind it to a port
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(('localhost', 19191))
print('UDP socket created and bound successfully')

while True:
    line, addr = s.recvfrom(256)
    line = line.decode()
    print("Received: \"" + line + "\" from: " + addr[0])

def switch_broad(argument):
    switcher = {
        1: "REQ_DOWNLOAD_META_SEARCH"
        2: "REQ_DOWNLOAD_META_DEFAULT"
        3: "REQ_DOWNLOAD_FILE"
    }

def update_client_meta:
    # for each loop that goes through the client whitelist and sends each IP
    # a JSON file with updated patient metadata

def download_meta_default(addr, patient):
    # supplies metadata on the most recently accessed or uploaded patient files

def download_meta_search(addr, patient, terms):
    # supplies metadata on patient files that match the search terms provided

def download_file(addr, patient, path):
    # send the full specified file to the client
