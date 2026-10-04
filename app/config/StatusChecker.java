// first line

package app.config;

import app.config.dependencies.Config;
import app.config.db.DbInit;

public class StatusChecker {
    private StatusChecker(){}

    public static boolean checker(){
        if (!Config.Check()){
            return false;
        }
        if (!DbInit.init()){
            return false;
        }
        return true;
    }
}

// last line