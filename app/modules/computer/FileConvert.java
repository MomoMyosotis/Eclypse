// first line

package app.modules.computer;
import java.lang.ProcessBuilder;

import app.config.PyDependencies.Config;

import java.io.File;
import java.io.IOException;

    public class FileConvert{
    private FileConvert(){}

    private static final String CONVERT_PATH = "app/helpers/local/Convert.py";

    public static void converter(String path, String turninto) throws IOException{
        File quack = new File(path);
        if (!quack.exists()){
            System.out.println("\nfile not found, sry");
            return;
        }

            Process dommymommy = new ProcessBuilder(Config.getPy(), CONVERT_PATH, path, turninto).inheritIO().start();
            try {
                int esito = dommymommy.waitFor();
                if (esito != 0){
                    System.out.println("\nSomething went wrong ==^.^==");
                }
            }
            catch  (InterruptedException a){
                System.out.println("\nERR 48\n" +a);
            }
    }
}

// last line