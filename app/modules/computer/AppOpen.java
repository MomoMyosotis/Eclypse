// first line

package app.modules.computer;
import java.lang.ProcessBuilder;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class AppOpen {
    private AppOpen(){}

    private static void pbuilder(String[] parts){
                try{
                new ProcessBuilder(parts).start();
                } catch (Exception e){
                    System.out.println("\nsesamoapp error.\n" +e);
                }
    }

    public static void sesamoapp(File baka) throws FileNotFoundException{
        Scanner scan = new Scanner (baka);
        while (scan.hasNext()){
            String line = scan.nextLine();
            if (line.startsWith("Exec=")){
                System.out.println("\nEXEC: " + line+ "\n");
                String exec = line.substring(5);

                // per whatsapp web
                if (exec.startsWith("sh -c")){
                    String command = exec.substring(5).trim();
                    // rimuove gli apici esterni
                    command = command.substring(1, command.length() - 1);
                    String[] parts = {"sh", "-c", command};
                    pbuilder(parts);
                    break;
                }

                exec = exec.split("%")[0].trim();
                String[] parts = exec.split(" ");
                pbuilder(parts);
                break;
            }
        }
        scan.close();
    }

    public static void open(String zighy){
        // per chrome e firefox incognito
        if (!zighy.endsWith(".desktop")){
            String[] parts = zighy.split(" ");
            pbuilder(parts);
            return;
        }
        String a = System.getProperty("user.home").concat("/.local/share/applications/").concat(zighy);
        String b = "/usr/share/applications/".concat(zighy);
        try{
            File miao = new File(a);
            File quack = new File(b);
            if(miao.exists()){
                sesamoapp(miao);
            }
            else if (quack.exists()){
                sesamoapp(quack);
            } else{
                System.out.println("\n"+ zighy + " not found");
                return;
            }
        }
        catch (Exception e){
            System.out.println("\nAppOpen error.\n" + e);
        }
    }
}

// last line