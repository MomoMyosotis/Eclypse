// first line

package app.commands.computer;
import app.modules.computer.FileOpen;
import app.commands.Command;
import app.helpers.Filepath;


public class OpenFileCmd implements Command {

    @Override
    public void execute(){
        String zighy = Filepath.FP();
        try {
            FileOpen.sesamo(zighy);
        } catch (Exception e){
            System.out.println("OpenFIle failed.\nerror: " +e);
        }
    }
}

// last line