import subprocess
import sys

def run_cmd(cmd):
    try:
        output = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8')
    except subprocess.CalledProcessError as e:
        print(f"[X] Error executing cmd: \"{cmd}:\"\n\t{e}")
        sys.exit(1)
    else:
        print(f"[ ] Executed cmd: \"{cmd}\"")
        return output

# fetch patient dir names
dir_names = run_cmd("ils /tempZone/home/public | awk -F '/' '/_/ {print $5}'").splitlines()

# fetch list of file names, then add file name as metadata onto file
for dir_name in dir_names:
    files = run_cmd(f"ils /tempZone/home/public/{dir_name}")
    for file in files:
        run_cmd(f"imeta set -d {file} file_name {file}")
