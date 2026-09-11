# first line
import tkinter as tk
import tkinter.scrolledtext as scrolledtext
import requests
import webbrowser
import subprocess
import speech_recognition as sr
import urllib.parse
import os
from tkinter import messagebox

# -------------------------
# UI
# -------------------------
class AssistantUI:
    def __init__(self, master):
        self.master = master
        master.title("Assistant")
        master.geometry("800x600")

        self.label = tk.Label(master, text="Inserisci comando:", font=('Arial', 16))
        self.label.pack(pady=10)

        self.entry = tk.Entry(master, width=60, font=('Arial', 14))
        self.entry.pack()

        self.execute_button = tk.Button(master, text="Esegui", command=self.execute_command)
        self.execute_button.pack(pady=10)

        self.text_output = scrolledtext.ScrolledText(master, wrap='word')
        self.text_output.pack(expand=True, fill='both')

        self.voice_button = tk.Button(master, text="Voce", command=self.execute_voice_command)
        self.voice_button.pack(pady=10)

    def execute_command(self):
        command = self.entry.get().lower()
        self.process_command(command)

    def execute_voice_command(self):
        command = recognize_speech()
        self.entry.delete(0, tk.END)
        self.entry.insert(tk.END, command)
        self.process_command(command)

    def process_command(self, command):
        if "search" in command:
            query = command.replace("search", "").strip()
            search_web(query)
            self.show_response(f"Cerco: {query}")

        elif "open" in command:
            app_name = command.replace("open", "").strip()
            open_application(app_name)
            self.show_response(f"Apro: {app_name}")

        elif "weather" in command:
            city = command.replace("weather", "").strip()
            self.show_response(get_weather(city))

        elif "news" in command:
            self.show_response(get_news())

        else:
            # 🔥 QUI ORA USA WIKIPEDIA
            risposta = ask_wikipedia(command)
            self.show_response(risposta)

    def show_response(self, response):
        self.text_output.config(state='normal')
        self.text_output.insert(tk.END, response + "\n\n")
        self.text_output.config(state='disabled')


# last line