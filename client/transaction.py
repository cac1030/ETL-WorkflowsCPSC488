import socket
import sys

class Request:
    def __init__(self):
        self.SERVER_HOST = "54.227.89.39"
        self.SERVER_PORT = 5001
        self.SEPARATOR = "[-]"
        self.BUFFER_SIZE = 4096
        self.SOCKET = socket.socket()

    def connect(self):
        print(f"[...] Connecting to {self.SERVER_HOST}:{self.SERVER_PORT}")
        try:
        	self.SOCKET.connect((self.SERVER_HOST, self.SERVER_PORT))
        except OSError as e:
            print(f"[X] Connection failed: {e}")
            sys.exit(1)
        else:
            print("[+] Connected")

    def send_req(self, request, data):
        try:
            self.SOCKET.send(f"{request}!{data}".encode())
        except OSError as e:
            print(f"[X] Sending request failed: {e}")
            sys.exit(1)
        else:
            print(f"[>] {request} sent")

    def recv_response(self):
        try:
            message = self.SOCKET.recv(self.BUFFER_SIZE)
            while True:
                bytes_read = self.SOCKET.recv(self.BUFFER_SIZE)
                if not bytes_read:
                	break
                message = message + bytes_read
        except OSError as e:
            print(f"[X] Receiving data failed: {e}")
            sys.exit(1)
        else:
            return message
        finally:
            self.SOCKET.close()

class In:
    def __init__(self):
        self.SERVER_HOST = "54.227.89.39"
        self.SERVER_PORT = 5001
        self.SEPARATOR = "[-]"
        self.BUFFER_SIZE = 4096
        self.SOCKET = socket.socket()
