// first line

package app.commands.computer;
import app.modules.computer.FileOpen;
import app.commands.Command;
import app.helpers.Filepath;
import java.util.Scanner;

public class EditFileCmd implements Command {

    @Override
    public void execute(Scanner miao){

        String zighy = Filepath.FP(miao);
        try {
            FileOpen.sesamo(zighy);
        } catch (Exception e){
            System.out.println("EditFile failed.\nerror: " +e);
        }
    }
}

// last line