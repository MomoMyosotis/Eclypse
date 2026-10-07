// first line

package app.config;
import app.config.db.DbInit;
import app.config.PyDependencies.Config;
import app.config.ceteras.BdayMover;;

public class StatusChecker {
    private StatusChecker(){}

    public static boolean checker(){
        if (!Config.Check()){
            return false;
        }
        if (!DbInit.init()){
            return false;
        }
        if (!BdayMover.BdayInator()){
            return false;
        }
        System.out.println("\nEclypse is ready! :3");
        return true;
    }
}

// last line