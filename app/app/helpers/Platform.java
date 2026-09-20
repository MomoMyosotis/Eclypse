// first line

package app.helpers;

public class Platform {
        private Platform(){}

        public enum OS {
            LINUX,
            WINDOWS,
            MACOS
        }

        public static final OS CURRENT_OS = ghettos();

        private static OS ghettos(){
            String os = System.getProperty("os.name").toLowerCase();
            if(os.contains("linux")){
                return OS.LINUX;
            }
            if (os.contains("windows")){
                return OS.WINDOWS;
            }
            if (os.contains("mac")){
                return OS.MACOS;
            }
            throw new UnsupportedOperationException("\nUnsupported OS: "+ os);
        }
}

// last line