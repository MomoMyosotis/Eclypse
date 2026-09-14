// first line

package app.commands.computer;
import app.commands.Command;
import app.helpers.AppList;
import app.platform.App;
import app.modules.computer.AppOpen;
import java.util.Scanner;

public class OpenAppCmd implements Command {

    @Override
    public void execute(Scanner miao){
        AppList.lista();
        System.out.println("\nnumber app to start: ");
        int zighy = miao.nextInt();
        try {
            App app = AppList.getApp(zighy);
            AppOpen.openApp(app);
        } catch (Exception e){
            System.out.println("OpenFIle failed.\nerror: " +e);
        }
    }
}

// last line