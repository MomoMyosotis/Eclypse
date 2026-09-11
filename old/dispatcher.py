# frist line

from audio import recognize_speech
from UI import AssistantUI
from online import search_web, get_weather, get_news
from wiki import ask_wikipedia
from local import open_application

def dispatch_command(command):
    if "search" in command:
        query = command.replace("search", "").strip()
        search_web(query)
        return f"Cerco: {query}"

    elif "open" in command:
        app_name = command.replace("open", "").strip()
        open_application(app_name)
        return f"Apro: {app_name}"

    elif "weather" in command:
        city = command.replace("weather", "").strip()
        return get_weather(city)

    elif "news" in command:
        return get_news()

    else:
        risposta = ask_wikipedia(command)
        return risposta

# last line