# first line

import requests
import webbrowser
import subprocess
import speech_recognition as sr
import urllib.parse
import os
from tkinter import messagebox


def search_web(query):
    webbrowser.open(f"https://www.google.com/search?q={query}")

def get_weather(city):
    api_key = 'your_weatherapi_key'
    url = f'http://api.weatherapi.com/v1/current.json?key={api_key}&q={city}&aqi=no'
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        weather_desc = data['current']['condition']['text']
        temp_c = data['current']['temp_c']
        return f"Il tempo a {city} è {weather_desc} con {temp_c}°C."
    else:
        return "Errore meteo."

def get_news():
    api_key = 'your_newsapi_key'
    url = f'https://newsapi.org/v2/top-headlines?country=it&apiKey={api_key}'
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        articles = data['articles']
        news = "\n\n".join([
            f"{a['title']}\n{a['description']}" for a in articles[:5]
        ])
        return news
    else:
        return "Errore news."


# last line