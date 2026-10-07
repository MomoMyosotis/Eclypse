// first line

package app.modules.computer;
import app.helpers.general.Levenshit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSearch {

    private FileSearch(){}

    private static final Comparator<FileInfo> BEST_MATCH_FIRST =
        Comparator.comparingInt(FileInfo::getScore).reversed()
            .thenComparing(FileInfo::getName, String.CASE_INSENSITIVE_ORDER)
            .thenComparing(FileInfo::getPath);

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
        if (mr <= 0){
            return;
        }
        stampagioie(findMatches(filename, mr), mr);
    }

    // funzione che si occupa della ricerca
    public static ArrayList<FileInfo> searchinator (String ts){
        return findMatches(ts, 0);
    }

    private static ArrayList<FileInfo> findMatches(String query, int maxResults){
        System.out.println("\nsearching...");
        ArrayList<FileInfo> matches = new ArrayList<>();
        PriorityQueue<FileInfo> bestMatches = maxResults > 0
            ? new PriorityQueue<>(Math.min(maxResults, 64), BEST_MATCH_FIRST.reversed())
            : null;

        // da dove inizia a cercare
        Path directory = Paths.get(System.getProperty("user.home"));

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs){
                    String name = path.getFileName().toString();
                    String percorso = path.toString();
                    int score = valid(name, query);

                    if (score != -1){
                        FileInfo match = new FileInfo(name, percorso, score);
                        if (bestMatches == null){
                            matches.add(match);
                        } else if (bestMatches.size() < maxResults){
                            bestMatches.add(match);
                        } else if (BEST_MATCH_FIRST.compare(match, bestMatches.peek()) < 0){
                            bestMatches.poll();
                            bestMatches.add(match);
                        }
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

        if (bestMatches != null){
            matches.addAll(bestMatches);
            matches.sort(BEST_MATCH_FIRST);
        }
        return matches;
    }

    // decide se un nome è close enough o no && già che ci siamo assegna score
    static int valid(String found, String given){
        // exact match
        if (found.equals(given)){
            return 100;
        }

        // remove formati
        int extension = found.indexOf('.');
        if (extension >= 0){
            found = found.substring(0, extension);
        }
        extension = given.indexOf('.');
        if (extension >= 0){
            given = given.substring(0, extension);
        }
        // if they match with different formats
        if (found.equals(given)){
            return 99;
        }

        // li metto entrambi in minuscolo
        if (found.equalsIgnoreCase(given)){
            return 98;
        }

        // se ha una fitness maggiore del 45%
        return Levenshit.scoreAboveThreshold(
            found.toLowerCase(Locale.ROOT), given.toLowerCase(Locale.ROOT), 45);
    }

    // stampa il tutto
    static void stampagioie(ArrayList<FileInfo> gioie, int mr){
        gioie.sort(BEST_MATCH_FIRST);

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