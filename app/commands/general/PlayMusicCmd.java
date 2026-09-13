// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.PlayMusic;
import java.util.Scanner;

public class PlayMusicCmd implements Command {

    @Override
    public void execute(Scanner miao){
        PlayMusic.play(miao);
    }
}

// last line