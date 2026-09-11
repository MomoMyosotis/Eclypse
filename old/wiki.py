# first line

import requests
import urllib.parse


# -------------------------
# WIKIPEDIA API
# -------------------------
def ask_wikipedia(query):
    try:
        headers = {
            "User-Agent": "MyAssistant/1.0 (test@example.com)"
        }

        # STEP 1: search
        search_url = "https://it.wikipedia.org/w/api.php"
        params = {
            "action": "query",
            "list": "search",
            "srsearch": query,
            "format": "json"
        }

        res = requests.get(search_url, params=params, headers=headers)

        if res.status_code != 200:
            return f"Errore HTTP search: {res.status_code}"

        data = res.json()

        if not data.get("query", {}).get("search"):
            return "Nessun risultato trovato."

        title = data["query"]["search"][0]["title"]

        # STEP 2: summary
        encoded_title = urllib.parse.quote(title)
        summary_url = f"https://it.wikipedia.org/api/rest_v1/page/summary/{encoded_title}"

        summary_res = requests.get(summary_url, headers=headers)

        if summary_res.status_code != 200:
            return f"Errore HTTP summary: {summary_res.status_code}"

        summary_data = summary_res.json()

        return summary_data.get("extract", "Nessuna descrizione.")

    except Exception as e:
        return f"Errore Wikipedia: {e}"


# last line