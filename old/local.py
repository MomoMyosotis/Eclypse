# first line
import os
import subprocess
import webbrowser
import requests
from tkinter import messagebox

def open_application(app_name):
    try:
        if os.name == 'nt':  # Windows
            os.startfile(app_name)
        else:  # Linux
            subprocess.call(['xdg-open', app_name])
    except Exception as e:
        messagebox.showerror("Errore", f"Errore apertura app: {e}")


# last line