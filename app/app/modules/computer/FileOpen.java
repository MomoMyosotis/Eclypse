// first line

package app.modules.computer;
import java.awt.Desktop;
import java.io.File;

public class FileOpen {
    private FileOpen(){}

    public static void sesamo(String tp){
        try{
            File file = new File(tp);
            Desktop bl = Desktop.getDesktop();
            if (!bl.isDesktopSupported()){
                System.out.println("is-desktop-supported failed.");
            }
            bl.open(file);
        } catch (Exception e){
            System.out.println("\nopenfile error.\n" +e);
        }
    }
}

// last line