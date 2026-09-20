// first line

package app.modules.general;
import java.util.Map;

import app.helpers.HttpHandler;

public class Weather {
    private Weather(){}
    private static final String a = "https://api.open-meteo.com/v1/forecast?latitude=";
    private static final String b = "&longitude=";
    private static final String c = "&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,wind_speed_10m&timezone=auto";
    // es:
    // curl "https://api.open-meteo.com/v1/forecast?latitude=45.4384&longitude=10.9916&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,wind_speed_10m&timezone=auto"


    public static void weather(double[] coords){
        String query = a + String.valueOf(coords[0]) + b + String.valueOf(coords[1]) + c;
        String res = HttpHandler.hh(query);
        prettify(res.split("\"current\":")[1]);
    }

    /* es:
    {"current":{"time":"2026-09-20T01:30","interval":900,
    "temperature_2m":15.7,"relative_humidity_2m":80,"apparent_temperature":15.6,
    "weather_code":1,"wind_speed_10m":5.8}}
    */
    static void prettify(String res){
        String t = res.split("temperature_2m\":")[1].split(",\"")[0];
        String t_percivied = res.split("apparent_temperature\":")[1].split(",\"")[0];
        String umidity = res.split("relative_humidity_2m\":")[1].split(",\"")[0];
        String condizione = res.split("weather_code\":")[1].split(",\"")[0];
        int status = -1;
        try{
            status = Integer.parseInt(condizione);
        } catch (Exception e){
            System.out.println("\nERR 581\n"+e);
            return;
        }
        String cond = WEATHER_CODES.get(status);
        String wind = res.split("wind_speed_10m\":")[1].split(",\"")[0].replace("}}", "");
        System.out.println("\nWeather: " + cond + "\nTemperature: "+ t + "°C\nPercived temperature: "+ t_percivied
                            + "°C\nUmidity percentage: " + umidity + "%\nWind speed: " + wind + "km/h"
        );
    }

    private static final Map<Integer, String> WEATHER_CODES = Map.ofEntries(
        Map.entry(0, "Sereno"),
        Map.entry(1, "Prevalentemente sereno"),
        Map.entry(2, "Parzialmente nuvoloso"),
        Map.entry(3, "Coperto"),

        Map.entry(45, "Nebbia"),
        Map.entry(48, "Nebbia con brina"),

        Map.entry(51, "Pioviggine debole"),
        Map.entry(53, "Pioviggine moderata"),
        Map.entry(55, "Pioviggine intensa"),

        Map.entry(56, "Pioviggine gelata debole"),
        Map.entry(57, "Pioviggine gelata intensa"),

        Map.entry(61, "Pioggia debole"),
        Map.entry(63, "Pioggia moderata"),
        Map.entry(65, "Pioggia intensa"),

        Map.entry(66, "Pioggia gelata debole"),
        Map.entry(67, "Pioggia gelata intensa"),

        Map.entry(71, "Neve debole"),
        Map.entry(73, "Neve moderata"),
        Map.entry(75, "Neve intensa"),
        Map.entry(77, "Granelli di neve"),

        Map.entry(80, "Rovesci deboli"),
        Map.entry(81, "Rovesci moderati"),
        Map.entry(82, "Rovesci violenti"),

        Map.entry(85, "Rovesci di neve deboli"),
        Map.entry(86, "Rovesci di neve intensi"),

        Map.entry(95, "Temporale"),
        Map.entry(96, "Temporale con grandine debole"),
        Map.entry(99, "Temporale con grandine intensa")
    );
}



// last line