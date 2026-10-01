// first line

package app.modules.computer;
import app.helpers.general.Levenshit;
import app.helpers.general.SelectionSort;

import java.util.ArrayList;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSearch {

    private FileSearch(){}

    // oggetto FileInfo (nome - path - score)
    public static class FileInfo{
        String name;
        String path;
        int score;

        public FileInfo(String name, String path, int score){
            this.name = name;
            this.path = path;
            this.score = score;
        }
        public String getName(){
            return name;
        }
        public String getPath(){
            return path;
        }
        public int getScore(){
            return score;
        }
        public void setScore(int score){
            this.score = score;
        }
    }

    public static void search(String filename, int mr){

        ArrayList<FileInfo> gioie = new ArrayList<>();
        // chiamo la funzione che cerca i risultati
        gioie = searchinator(filename);

        stampagioie(gioie, mr);
    }

    // funzione che si occupa della ricerca
    static ArrayList<FileInfo> searchinator (String ts){

        System.out.println("\nsearching...");
        // trovati i risultati vanno messi in una ArrayList
        ArrayList<FileInfo> quacked = new ArrayList<>();

        // da dove inizia a cercare
        Path directory = Paths.get(System.getProperty("user.home"));

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs){
                    String name = path.getFileName().toString();
                    String percorso = path.toString();
                    int score = valid(name, ts);

                    if (score != -1){
                        FileInfo miao = new FileInfo(name, percorso, score);
                        quacked.add(miao);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException e){
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });
        } catch( IOException e){
            e.printStackTrace();
        }
        // ritorna la lista ordinata e completa
        return quacked;
    }

    // decide se un nome è close enough o no && già che ci siamo assegna score
    static int valid(String found, String given){
        // exact match
        if (found.equals(given)){
            return 100;
        }

        // remove formati
        String[] temp = found.split("\\.");
        found = temp[0];
        temp = given.split("\\.");
        given = temp[0];
        // if they match with different formats
        if (found.equals(given)){
            return 99;
        }

        // li metto entrambi in minuscolo
        given = given.toLowerCase();
        found = found.toLowerCase();
        if (found.equals(given)){
            return 98;
        }

        // se ha una fitness maggiore del 45%
        int pt = Levenshit.levenstein(found, given);
        if(pt > 45){
            return pt;
        }

        return -1;
    }

    // stampa il tutto
    static void stampagioie(ArrayList<FileInfo> gioie, int mr){
        // ordino l'ArrayList
        SelectionSort.selectionsort(gioie, 0);

        if (mr > gioie.size()){
            mr = gioie.size();
        }

        for (int i = 0; i < mr; i++){
            System.out.println("\"" + gioie.get(i).getName() + "\" - \"" +
            gioie.get(i).getPath() + "\" - " + gioie.get(i).getScore());
        }
        
    }
}

// last line