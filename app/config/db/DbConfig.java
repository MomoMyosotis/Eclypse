// first line

package app.config.db;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DbConfig {
    private DbConfig(){}

    private static final String DB_NAME = "app/config/db/Eclypse.db";
    private static final String TYPE = """
            SELECT name
            FROM sqlite_master
            WHERE type='table'
            AND name='TYPE';
            """;
    private static final String DATA = """
            SELECT name
            FROM sqlite_master
            WHERE type='table'
            AND name='DATA'
            """;

    public static String Get_Db_Path(){
        return DB_NAME;
    }

    static boolean DbExists(){
        return Files.exists(Path.of(DB_NAME));
    }

    static boolean DbValid(){
        try {
            Connection c = DbHandler.getConnection();
            Statement st = c.createStatement();
            ResultSet type = st.executeQuery(TYPE);
            if (!type.next()){
                return false;
            }
            st.close();
            ResultSet data = st.executeQuery(DATA);
            if (!data.next()){
                return false;
            }
            st.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

// last line