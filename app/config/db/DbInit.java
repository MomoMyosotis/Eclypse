// first line

package app.config.db;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.*;

public class DbInit {
    private DbInit() {}
    
    private static final String FILE_NAME = "app/config/db/database.sql";
    
    public static boolean init(){
        try {
            Connection c = DbHandler.getConnection();
            if (!DbConfig.DbValid() || !DbConfig.DbExists()){
                crea_db(c);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static void exec(Connection c, String query){
        try {
        Statement st = c.createStatement();
        st.executeUpdate(query);
        } catch (SQLException e){
            System.out.println("\nerr 382");
            e.printStackTrace();
        }
    }

    static void crea_db(Connection c){
        File f = new File(FILE_NAME);
        StringBuilder query = new StringBuilder();
        try (Scanner s = new Scanner(f)){
            while (s.hasNextLine()){
                query.append(s.nextLine());
                query.append("\n");
            }
            String[] pieces = query.toString().split(";");
            for (String p : pieces){
                if (!p.trim().isEmpty()){
                    exec(c, p);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("\nerr 381\n");
            e.printStackTrace();
        }
    }
}

// last line