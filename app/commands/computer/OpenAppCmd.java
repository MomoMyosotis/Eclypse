// first line

package app.commands.computer;
import app.modules.computer.AppOpen;
import app.commands.Command;
import app.helpers.AppList;
import java.util.Scanner;

public class OpenAppCmd implements Command {

    @Override
    public void execute(Scanner miao){
        AppList.lista();
        System.out.println("\nname app to start: ");
        int zighy = miao.nextInt();
        String tp = AppList.getpath(zighy);
        try {
            AppOpen.open(tp);
        } catch (Exception e){
            System.out.println("OpenFIle failed.\nerror: " +e);
        }

/*
    // test
        for (int i = 1; i < 36; i++){
        String tp = AppList.getpath(i);
        try {
            AppOpen.open(tp);
        } catch (Exception e){
            System.out.println("OpenFIle failed.\nerror: " +e);
        }
    }
*/

    }
}

// last line