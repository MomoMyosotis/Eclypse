// first line

package app.commands.ai;
import app.modules.ai.OCR;
import app.commands.Command;
import java.util.Scanner;

public class OcrCmd implements Command {

    @Override
    public void execute(Scanner miao){
        OCR.convert(miao);
    }
}

// last line