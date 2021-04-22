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
    run_cmd("icd " + dir_name)
    files = run_cmd("ils | fgrep . | cut -f3 -d ' '").splitlines()
    print(files)
    for file in files:
        run_cmd(f"imeta set -d {file} file_name {file}")
    run_cmd("icd ..")
    break
