// first line

package app.commands.ai;
import app.modules.ai.OCR;
import app.commands.Command;

public class OcrCmd implements Command {

    @Override
    public void execute(){
        OCR.convert();
    }
}

// last line