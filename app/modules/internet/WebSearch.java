// first line

package app.modules.internet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.helpers.events.InputMaker;
import app.helpers.events.InputReader;
import app.helpers.local.OpenDefault;
import app.helpers.web.HttpHandler;

public class WebSearch {
    private WebSearch(){}

    public static void DispatchSearch(String type){

        final String duck = "https://duckduckgo.com/?q=";
        final String you = "https://www.youtube.com/results?search_query=";
        final String you_play = "https://www.youtube.com/watch?v=";
        final String wiki = "https://en.wikipedia.org/w/index.php?search=";
        switch (type){
            case "youtube":
                Tubo(InputReader.WhatTs(), you, you_play);
                break;
            case "wiki":
                Search(InputReader.WhatTs(), wiki);
                break;
            default:
                Naviga(InputReader.WhatTs(), duck);
                break;
        }
    }

    public static void Search(String ts, String wiki){
        String res = OpenDefault.ODB(wiki, ts);
        System.out.println(res);
    }

    public static void Naviga(String ts, String duck){
        String res = OpenDefault.ODB(duck, ts);
        System.out.println(res);
    }

    public static void Tubo(String ts, String you, String you_play) {
        String searchURL = you + HttpHandler.LinkFixer(ts);
        String body = HttpHandler.hh(searchURL);

        String id = (body == null) ? null : firstVideoId(body);

        String res;
        if (id != null){
            res = OpenDefault.ODBRaw(you_play + id + "&autoplay=1");
            InputMaker.pressPlay(5000);
        } else {
            System.out.println("\nid not found, opening results");
            res = OpenDefault.ODB(you, ts);
        }
        System.out.println(res);
    }

    public static String firstVideoUrl(String body, String you_play) {
        String id = firstVideoId(body);
        return id == null ? null : you_play + id;
    }

    public static String firstVideoId(String body) {
        Matcher m = Pattern
            .compile("\"videoRenderer\":\\{\"videoId\":\"([A-Za-z0-9_-]{11})\"")
            .matcher(body);
        return m.find() ? m.group(1) : null;
    }
}

// last line