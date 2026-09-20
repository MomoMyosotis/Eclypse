// first line

package app.modules.computer;
import java.lang.ProcessBuilder;
import app.platform.App;
import app.helpers.Platform;

public class AppOpen{
    private AppOpen(){}

    public static void openApp(App app){
        switch (Platform.CURRENT_OS){

            case LINUX:
                String[] linux = app.getId().split(" ");
                try{
                    new ProcessBuilder(linux).start();
                } catch (Exception e){
                    System.out.println("Linux app opening failed.\n" + e);
                }
                break;

            case WINDOWS:
                try{
                    new ProcessBuilder("cmd", "/c", "start", "", app.getId()).start();
                } catch (Exception e){
                    System.out.println("Windows app opening failed.\n" + e);
                }
                break;

            case MACOS:
                try{
                    new ProcessBuilder("open", app.getId()).start();
                } catch (Exception e){
                    System.out.println("MacOS app opening failed.\n" + e);
                }
                break;
        }
    }
}
// last line