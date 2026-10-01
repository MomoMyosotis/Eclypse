// first line

package app.config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHandler {
    private DbHandler(){}

    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:sqlite:" + DbConfig.Get_Db_Path());
        }
        return connection;
    }

    static void read (){
    }

    static void update(){
    }

    static void kill(){
    }
}

// last line