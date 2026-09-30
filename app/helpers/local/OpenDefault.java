// first line

package app.helpers.local;
import java.awt.Desktop;
import java.net.URI;

import app.helpers.web.HttpHandler;

public class OpenDefault {
    private  OpenDefault(){}

    // ODB = open default browser
    public static String ODB( String default_browser, String name){
        String gioie  = default_browser + HttpHandler.LinkFixer(name);

        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                Desktop.getDesktop().browse(new URI(gioie));
            }
        } catch (Exception e){
            System.out.println("\nErr 48\n"+e);
            return e.toString();
        }
        return gioie;
    }

    public static String ODBRaw(String url){
    try{
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            Desktop.getDesktop().browse(new URI(url));
        }
    } catch (Exception e){
        System.out.println("\nErr 48\n"+e);
        return e.toString();
    }
    return url;
}
}

// last line