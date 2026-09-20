// first line

package app.commands.computer;
import app.commands.Command;
import java.util.Scanner;
import app.modules.computer.FileConvert;
import app.helpers.Filepath;
import app.helpers.Yes_no;

public class ConverterCmd implements Command{
    
    @Override
    public void execute(Scanner miao){
        String path = Filepath.FP(miao);
        String format = path.split("\\.")[1].toLowerCase();
        String turninto = format; // così di default non fa niente e ci risparmiamo rogne
        System.out.print("\nfile's extention is: "+ format + ".\nyou can turn it into a: ");
        if (format.equals("odt")){
            System.out.print(".pdf");
            turninto = "pdf";
        } else if (format.equals("pdf")){
            System.out.print(".jpeg");
            turninto = "jpeg";
        } else if (format.equals("jpeg") || format.equals("jpg") || format.equals("png")){
            System.out.print("pdf");
            turninto = "pdf";
        } else if (format.equals("mp4") ||
            format.equals("mkv") ||
            format.equals("avi") ||
            format.equals("mov") ||
            format.equals("webm") ||
            format.equals("wmv") ||
            format.equals("flv") ||
            format.equals("mpeg") ||
            format.equals("mpg") ||
            format.equals("3gp") ||
            format.equals("m4v")){
            System.out.print("mp3");
        } else {
            System.out.print("ehn... as it turns out, we still haven't prepared for this specific extension. feel free to contact  us so we can fix it.\n _Myosotis");
            return;
        }
        System.out.print("\nproceed?  choice: ");
        String choice = miao.nextLine();
        System.out.println("\n");
        if (!Yes_no.yes_no(choice)){;
            System.out.println("\nuser decided NOT to convert.\n");
            return;
        }
        FileConvert.converter(path, turninto);
        System.out.println("\nfile converted.\n");
    }
}
// test file path /home/myosotis/Desktop/TDS.odt
// last line