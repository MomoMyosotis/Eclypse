// first line

package app.config.PyDependencies;
import java.io.IOException;

import app.helpers.general.Platform;

public class Config {
    private Config (){}
    private static final String PYTHON_PATH =  "app/config/PyDependencies/PyConfig.py";

    public static String getPy(){
        String cmd = ".venv/bin/python";
        String os = Platform.CURRENT_OS.toString().toLowerCase();
        if (os.equals("windows")){
            cmd = ".venv/Scripts/python.exe";
        }
        return cmd;
    }

    public static String getSysPy(){
        String cmd = "python3";
        String os = Platform.CURRENT_OS.toString().toLowerCase();
        if (os.equals("windows")){
            cmd = "python";
        }
        return cmd;
    }

    public static boolean Check(){
        try {
            Process dommymommy = new ProcessBuilder(getSysPy(), PYTHON_PATH).start();
            // for debug:
            // Process dommymommy = new ProcessBuilder(getSysPy(), PYTHON_PATH).inheritIO().start();

            int esito = dommymommy.waitFor();
            if (esito != 0){
                System.out.println("\nSomething went wrong ==^.^==");
                return false;
            }
        }
        catch  (InterruptedException a){
            System.out.println("\nERR 48\n" +a);
            return false;
        }
        catch (IOException a){
            System.out.println("err in configuration. ERR 49\n"+a);
            return false;
        }
        System.out.println("\nall python dependencies are present and working!");
        return true;
    }
}

// last line