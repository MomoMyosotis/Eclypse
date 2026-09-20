// first line

// PH -> platform handler

package app.platform;
import app.helpers.Platform;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Scanner;

public class PH {

    private PH(){}

    public static ArrayList<App> getApps() {
        ArrayList<App> apps = new ArrayList<>();

        switch (Platform.CURRENT_OS) {
            case LINUX:
                Path[] directories = {
                    Paths.get(System.getProperty("user.home"), ".local/share/applications"),
                    Paths.get("/usr/share/applications")
                };
                for (Path directory : directories){
                    if (!Files.exists(directory)){
                        continue;
                    }
                    try (Stream<Path> file = Files.list(directory)){
                        file
                            .filter(path -> path.toString().endsWith(".desktop"))
                            .forEach(path -> {
                                try{
                                    App app = bujibuja(path);
                                    if (app != null){
                                        apps.add(app);
                                    }
                                } catch (FileNotFoundException e){
                                    System.out.println("bujibuja non ha funzionato e PH sta dando l'errore.\n"+e);
                                }
                            });
                    } catch (IOException e){
                        System.out.println("\nLINUX PH failed. ERR 11.\n" +e);
                    }
                }
                break;

            case WINDOWS:
                Path[] pippo = {
                    Paths.get(System.getenv("APPDATA"),
                    "Microsoft/Windows/Start Menu/Programs"),
                    Paths.get(System.getenv("ProgramData"),
                    "Microsoft/Windows/Start Menu/Programs")
                };

                for (Path a: pippo){
                    if(!Files.exists(a)){
                        continue;
                    }
                    try (Stream<Path> f = Files.walk(a)){
                        f
                        .filter(path -> path.toString().endsWith(".lnk"))
                        .forEach(path -> {
                            try{
                            App app = bujibuja(path);
                            if (app != null){
                                apps.add(app);
                            }
                        } catch (FileNotFoundException e){
                            System.out.println(e);
                        }
                        });
                    } catch (IOException E){
                        System.out.println("\nWindows PH failed. ERR 11\n" +E);
                    }
                }
                break;

            case MACOS:
                Path[] d = {
                    Paths.get(System.getProperty("user.home"), "Applications"),
                    Paths.get("/Applications"),
                    Paths.get("/System/Applications")
                };

                for (Path m : d){
                    if(!Files.exists(m)){
                        continue;
                    }
                    try(Stream<Path> a = Files.list(m)){
                        a
                        .filter(path -> path.toString().endsWith(".app"))
                        .forEach(path -> {
                            try{
                            App app = bujibuja(path);
                            if (app != null){
                                apps.add(app);
                            }
                        } catch (FileNotFoundException e){
                            System.out.println(e);
                        }
                        });
                    } catch (IOException e){
                        System.out.println("\nMACOS PH failed. ERR 11\n"+e);
                    }
                }
                break;
        }
        return apps;
    }

    public static App bujibuja(Path p) throws FileNotFoundException{
        String path = p.toString();
        File baka = new File(path);
        if (!baka.exists()){
            return null;
        }
        switch (Platform.CURRENT_OS){
            case LINUX:
                Scanner scan = new Scanner(baka);
                String exec = null;
                String name = null;
                try {
                    while(scan.hasNext()){
                        String l = scan.nextLine();
                        if (l.startsWith("Name=")){
                            name = l.substring(5).trim();
                        }
                        if (l.startsWith("Exec=")){
                            exec = l.substring(5).split("%")[0].trim();
                        }
                    }
                    scan.close();
                    if (name != null && exec != null){
                        return new App(name, exec);
                    }
                } catch (Exception e){
                    System.out.println("bujibuja ERR 12.\n"+e);
                    scan.close();
                }
                break;
            
            case WINDOWS:
                return new App(p.getFileName().toString(), p.toString());
            
            case MACOS:
                return new App(
                    p.getFileName().toString().replace(".app", ""),
                    p.toString()
                );
        }
        return null;
    }
}

// last line