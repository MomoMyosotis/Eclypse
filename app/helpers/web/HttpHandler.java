// first line

package app.helpers.web;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class HttpHandler {
    private HttpHandler(){}
    private static final HttpClient CLIENT = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();

    public static String hh(String query){
        try{
            URI uri = URI.create(query);

            String language = Locale.getDefault().toLanguageTag();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/124.0 Safari/537.36")
                    .header("Accept-Language", language)
                    .header("Cookie", "CONSENT=NO+1; SOCS=CAI")
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String LinkFixer (String tl){
        return URLEncoder.encode(tl, StandardCharsets.UTF_8).toString();
    }
}

// last line