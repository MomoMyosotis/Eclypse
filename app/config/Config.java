// first line

package app.config;
import java.io.IOException;
import app.helpers.Platform;

public class Config {
    private Config (){}

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

        try{
            Process dommymommy = new ProcessBuilder(getSysPy(), "app/config/PyConfig.py").start();
            // for debug:
            // Process dommymommy = new ProcessBuilder(getSysPy(), "app/config/PyConfig.py").inheritIO().start();

            try {
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
        } catch (IOException a){
            System.out.println("err in configuration.\n"+a);
            return false;
    }
    return true;
    }
}

// last line