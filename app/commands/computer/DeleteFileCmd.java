// first line

package app.commands.computer;
import app.modules.computer.FileDelete;
import app.commands.Command;
import app.helpers.Filepath;
import java.util.Scanner;

public class DeleteFileCmd implements Command {

    @Override
    public void execute(Scanner miao){
        String zighy = Filepath.FP(miao);
        try {
            FileDelete.delete(zighy);
        } catch (Exception e){
            System.out.println("DeleteFileCmd filedelete failed.\nerror: " +e);
        }
    }
}

// last line