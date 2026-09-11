# first line
import speech_recognition as sr


def recognize_speech():
    recognizer = sr.Recognizer()
    with sr.Microphone() as source:
        print("Ascoltando...")
        audio = recognizer.listen(source)
        try:
            return recognizer.recognize_google(audio, language="it-IT").lower()
        except:
            return "errore voce"

# last line