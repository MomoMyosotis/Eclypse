// first line

package app.modules.computer;
import java.io.File;

public class FileDelete {
    private FileDelete(){}

    public static void delete(String path){

        File miao = new File(path);
        try{
            if (miao.exists())
                if(miao.delete()){
                    System.out.println("\nfile deleted| ==^.^==");
                } else {
                    System.out.println("\nsurri, not deleted...");
                }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

// last line