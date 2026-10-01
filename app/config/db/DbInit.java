// first line

package app.config.db;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.*;
import java.sql.*;

public class DbInit {
    private DbInit() {}
    
    private static final String FILE_NAME = "database.sql";
    
    public static void init(){
        /*
        1. gets connection
        2. create "TYPE" table (if not exists)
        3. create "DATA" table (if not exists)
        4. inserisce elementi (B-Day || appointment || remind)
        5. legge elementi
        6. modifica elementi
        7. elimina elementi
        */

        String path = DbConfig.Get_Db_Path();

        try {
            Connection c = DbHandler.getConnection();
            if (!DbConfig.DbValid()){
                crea_db(c);
            }
        } catch (Exception e){
            e.printStackTrace();;
        }
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