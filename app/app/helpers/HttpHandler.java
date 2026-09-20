// first line

package app.helpers;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpHandler {
    private HttpHandler(){}
    
    public static String hh(String query){
        try{
            URI uri = URI.create(query);
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.body());
            return response.body();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

// last line