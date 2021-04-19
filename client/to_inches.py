import subprocess

cmd = "ils | cut -f4 -d ' '"
all_patients = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE).stdout.decode('utf-8')
print(all_patients)

for patient in all_patients:
    cmd = f"imeta set -C {patient} height 68"
    subprocess.run(cmd, shell=True)
