# first line

import os
import sys
import subprocess

def _read_requirements():
    path = os.path.join(os.path.dirname(__file__), "requirements.txt")

    if not os.path.isfile(path):
        return None

    return path

def ensure_modules():
    print("Checking required modules...")

    requirements = _read_requirements()

    if requirements is None:
        print("requirements.txt not found.")
        return

    try:
        subprocess.check_call([
            sys.executable,
            "-m",
            "pip",
            "install",
            "-r",
            requirements
        ])

    except subprocess.CalledProcessError as e:
        sys.stderr.write(
            f"Failed to install required modules: {e}\n"
        )
        sys.exit(1)

if __name__ == "__main__":
    ensure_modules()

# last line