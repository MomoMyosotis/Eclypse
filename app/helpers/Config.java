// first line

package app.helpers;
import java.io.IOException;
import java.lang.ProcessBuilder;

public class Config {
    private Config (){}

    public static boolean Check(){
        String cmd = "python3";
        String os = Platform.CURRENT_OS.toString().toLowerCase();
        if (os.equals("windows")){
            cmd = "python";
        }
        try{
            Process dommymommy = new ProcessBuilder(cmd, "app/helpers/PyConfig.py").start();
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