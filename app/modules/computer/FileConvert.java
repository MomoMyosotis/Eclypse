// first line

package app.modules.computer;
import java.lang.ProcessBuilder;
import java.io.File;
import java.io.IOException;
import app.helpers.Platform;

    public class FileConvert{
    private FileConvert(){}

    public static void converter(String path, String turninto){
        File quack = new File(path);
        if (!quack.exists()){
            System.out.println("\nfile not found, sry");
            return;
        }
        try{
            String cmd = "python3";
            String os = Platform.CURRENT_OS.toString().toLowerCase();
            if (os.equals("windows")){
                cmd = "python";
            }
            Process dommymommy = new ProcessBuilder(cmd, "app/helpers/Convert.py", path, turninto).start();
            try {
                int esito = dommymommy.waitFor();
                if (esito != 0){
                    System.out.println("\nSomething went wrong ==^.^==");
                }
            }
            catch  (InterruptedException a){
                System.out.println("\nERR 48\n" +a);
            }
        } catch (IOException e){
            System.out.println("\nERR 49\n" + e);
        }
    }
}

// last line