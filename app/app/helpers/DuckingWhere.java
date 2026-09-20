// first line

// GEOCODER (input: city) output: Continent\timezonecity
        // I step: city -> coords
        // II step: coords -> timezone
        // III step: return timezone
package app.helpers;

public class DuckingWhere {
    private DuckingWhere() {}
    private static final String a = "https://nominatim.openstreetmap.org/search?q=";
    private static final String b = "&format=jsonv2&limit=1";
    private static final String fuso = "https://api.wheretheiss.at/v1/coordinates/";

    // input city output fuso
    public static String duckingwhere(String ts){
        String fuso = null;
        double[] temp = geocoder(ts);
        fuso = timezone(temp);
        if (fuso == null){
            return null;
        }
        return fuso;
    }

    // input city output coords
    public static double[] geocoder(String city){
        String query = a + city + b;
        String http_result = HttpHandler.hh(query);
        /*
        output:
        [{"place_id":75263871,"licence":"Data © OpenStreetMap contributors,
        ODbL 1.0. http://osm.org/copyright","osm_type":"relation","osm_id":45991,
        "lat":"45.7721876","lon":"8.5751222","category":"boundary","type":"administrative",
        "place_rank":16,"importance":0.5481309385484431,"addresstype":"town","name":"Angera",
        "display_name":"Angera, Varese, Lombardia, 21021, Italia",
        "boundingbox":["45.7491693","45.7939840","8.5522460","8.6452822"]}]
        */
        double lat = Double.parseDouble(http_result.split("lat\":\"")[1].split("\"")[0]);
        double lon = Double.parseDouble(http_result.split("lon\":\"")[1].split("\"")[0]);
        double[] coords = {lat, lon};
        return coords;
    }

    // takes coords and returns timezone
    static String timezone(double[] coords){
        String where = "boh";

        String query = fuso + String.valueOf(coords[0])+","+String.valueOf(coords[1]);
        where = HttpHandler.hh(query);
        /*
        es:
        {"latitude":"41.8827595","longitude":"16.1791791","timezone_id":"Europe\/Rome",
        "offset":2,"country_code":"IT","map_url":"https:\/\/maps.google.com\/maps?q=41.8827595,16.1791791&z=4"}
        */
        where = where.split("timezone_id\":\"")[1].split(",\"offset")[0];
        where = where.replace("\\", "").split("\"")[0];
        return where;
    }
}

// last line