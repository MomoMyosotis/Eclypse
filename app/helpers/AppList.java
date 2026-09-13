// first line

package app.helpers;
import java.util.HashMap;
import java.util.Map;

public class AppList {

    private AppList(){}

    // oggetto app (nome - id)
    public static class App{
        String name;
        String id;
    
        public App(String name, String id){
            this.name = name;
            this.id = id;
        }
        public String getName(){
            return name;
        }
        public String getId(){
            return id;
        }
    }

    private static final Map<Integer, App> ducks = new HashMap<>();

    public static String getpath(int index){
        return ducks.get(index).getId();
    }

    // lista con tutte le app
    static {

        // ============================================================
        // PRODUTTIVITÀ & OFFICE
        // ============================================================

        ducks.put(1, new App("LibreOffice Writer", "libreoffice-writer.desktop"));
        ducks.put(2, new App("LibreOffice Calc", "libreoffice-calc.desktop"));
        ducks.put(3, new App("LibreOffice Impress", "libreoffice-impress.desktop"));
        ducks.put(4, new App("LibreOffice Draw", "libreoffice-draw.desktop"));
        ducks.put(5, new App("LibreOffice Math", "libreoffice-math.desktop"));
        ducks.put(6, new App("Calendar", "org.gnome.Calendar.desktop"));
        ducks.put(7, new App("Calculator", "org.gnome.Calculator.desktop"));
        ducks.put(8, new App("Notes", "xpad.desktop"));
        ducks.put(9, new App("Thunderbird Mail", "thunderbird.desktop"));

        // ============================================================
        // BROWSER & COMUNICAZIONE
        // ============================================================

        ducks.put(10, new App("Firefox", "firefox.desktop"));
        ducks.put(11, new App("Firefox - Incognito", "firefox --private-window"));
        ducks.put(12, new App("Chrome", "com.google.Chrome.desktop"));
        ducks.put(13, new App("Chrome - Incognito", "google-chrome --incognito"));
        ducks.put(14, new App("Discord", "discord.desktop"));
        ducks.put(15, new App("Whatsapp", "WebApp-Whatsapp7099.desktop"));
        ducks.put(16, new App("Zoom", "Zoom.desktop"));

        // ============================================================
        // MULTIMEDIA
        // ============================================================

        ducks.put(17, new App("VLC", "vlc.desktop"));
        ducks.put(18, new App("Celluloid", "io.github.celluloid_player.Celluloid.desktop"));
        ducks.put(19, new App("Rhythmbox", "org.gnome.Rhythmbox3.desktop"));

        // ============================================================
        // GRAFICA & DOCUMENTI
        // ============================================================

        ducks.put(20, new App("Drawing", "com.github.maoschanz.drawing.desktop"));
        ducks.put(21, new App("Image Viewer", "xviewer.desktop"));
        ducks.put(22, new App("Document Viewer", "xreader.desktop"));
        ducks.put(23, new App("Xournal++", "com.github.xournalpp.xournalpp.desktop"));

        // ============================================================
        // SVILUPPO & PROGRAMMAZIONE
        // ============================================================

        ducks.put(24, new App("VisualStudio Code", "code.desktop"));
        ducks.put(25, new App("Github Desktop", "flatpak run io.github.shiftey.Desktop"));
        ducks.put(26, new App("PgAdmin 4", "pgadmin4.desktop"));
        ducks.put(27, new App("GeoGebra", "flatpak run org.geogebra.GeoGebra"));

        // ============================================================
        // SISTEMA & UTILITÀ
        // ============================================================

        ducks.put(28, new App("Files", "nemo.desktop"));
        ducks.put(29, new App("Terminal", "org.gnome.Terminal.desktop"));
        ducks.put(30, new App("System Monitor", "org.gnome.SystemMonitor.desktop"));
        ducks.put(31, new App("Bluetooth", "blueman-manager.desktop"));
        ducks.put(32, new App("Gestures", "cinnamon-settings-gestures.desktop"));
        ducks.put(33, new App("Virtual Keyboard", "cinnamon-onscreen-keyboard.desktop"));
        ducks.put(34, new App("XPad", "xpad.desktop"));

        // ============================================================
        // GAMING
        // ============================================================

        ducks.put(35, new App("Steam", "steam.desktop"));
        ducks.put(36, new App("Undertale", "Undertale.desktop"));
        //ducks.put(37, new App("Tlauncher", "tlauncher.desktop"));
        }

    // stampa la lista in ordine
    public static void lista(){
        Clear.clean();
        for (int i = 1; i < ducks.size()+1; i++){
            System.out.println(i + ". " + ducks.get(i).getName());
        }
    }
}

// last line