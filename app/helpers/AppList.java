// first line

package app.helpers;
import java.util.ArrayList;
import app.platform.App;
import app.platform.PH;

public class AppList {

    private AppList(){}

    private static ArrayList<App> ducks = PH.getApps();

    public static String getpath(int index){
        return ducks.get(index-1).getId();
    }
    
    public static App getApp(int index){
        return ducks.get(index -1);
    }

    // stampa la lista in ordine
    public static void lista(){
        Clear.clean();
        for (int i = 0; i < ducks.size(); i++){
            System.out.println((i+1) + ". " + ducks.get(i).getName());
        }
    }
}

// last line