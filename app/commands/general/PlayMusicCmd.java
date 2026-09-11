// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.PlayMusic;

public class PlayMusicCmd implements Command {

    @Override
    public void execute(){
        PlayMusic.play();
    }
}

// last line