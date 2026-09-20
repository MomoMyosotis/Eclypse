import os
import sys
import subprocess


def main():
    print("Checking Python environment...")

    config_dir = os.path.dirname(os.path.abspath(__file__))
    project_root = os.path.dirname(os.path.dirname(config_dir))
    requirements = os.path.join(config_dir, "requirements.txt")
    venv_dir = os.path.join(project_root, ".venv")

    if not os.path.isfile(requirements):
        sys.stderr.write(f"requirements.txt not found: {requirements}\n")
        return 1

    if os.name == "nt":
        venv_python = os.path.join(venv_dir, "Scripts", "python.exe")
    else:
        venv_python = os.path.join(venv_dir, "bin", "python")

    if not os.path.isdir(venv_dir):
        print("Creating virtual environment...")
        try:
            subprocess.check_call([
                sys.executable,
                "-m",
                "venv",
                venv_dir
            ])
        except (OSError, subprocess.CalledProcessError) as error:
            sys.stderr.write(
                f"Failed to create virtual environment: {error}\n"
            )
            return 1

    if not os.path.isfile(venv_python):
        sys.stderr.write(
            f"Virtual environment Python interpreter not found: {venv_python}\n"
        )
        return 1

    print("Python environment ready.")
    print("Installing required modules...")

    try:
        subprocess.check_call([
            venv_python,
            "-m",
            "pip",
            "install",
            "-r",
            requirements
        ])
    except (OSError, subprocess.CalledProcessError) as error:
        sys.stderr.write(
            f"Failed to install required modules: {error}\n"
        )
        return 1

    return 0


if __name__ == "__main__":
    sys.exit(main())