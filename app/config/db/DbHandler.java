// first line

package app.config.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import app.helpers.calendar.Event;

public class DbHandler {
    private DbHandler(){}

    private static Connection connection;

    private final static String CREA =  """
        INSERT INTO DATA (evento, obj, quando, done, creato)
        VALUES (?, ?, ?, ?, ?);
        """;
        private final static String READ = """
            SELECT id, evento, obj, quando, done, creato
            FROM DATA;
        """;
        private final static String UPDATE = """
            UPDATE DATA
            SET evento = ?, obj = ?, quando = ?, done = ?, creato = ?
            WHERE id = ?
        """;
        private final static String DELETE = """
            DELETE FROM DATA
            WHERE id = ?;
        """;
        private final static String FIND_SINGLE ="""
                SELECT id, evento, obj, quando, done, creato
                FROM data
                WHERE 
                """;

        private static final String FIND = """
                SELECT tipo
                FROM TYPE
                WHERE id = ?
                """;

        private final static String EVENTO = "evento = ?";
        private final static String OBJ = "obj = ?";
        private final static String QUANDO = "quando = ?";
        private static final String DONE = "done = ?";
        private static final String CREATO = "creato = ?";
        private static final String AND = "\nAND ";

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:sqlite:" + DbConfig.Get_Db_Path());
        }
        return connection;
    }

    public static String cAnd (int count, String q){
        if (count > 1){
            return q+AND;
        }
        return q;
    }

    public static boolean Create(Event gioie){
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(CREA);
            String a = null;
            if (gioie.GetWhen() != null){
                a = gioie.GetWhen().toString();
            }
            bs.setInt(1, gioie.event());
            bs.setString(2, gioie.GetObj());
            bs.setString(3, a);
            if(gioie.GetDone() == null){
            bs.setBoolean(4, false);
            } else {
                bs.setBoolean(4, gioie.GetDone());
            }
            bs.setString(5, gioie.GetCreated().toString());
            return bs.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Event> Read (){
        ArrayList<Event> evee = new ArrayList<>();
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(READ);
            ResultSet rawr = bs.executeQuery();
            while (rawr.next()){
                int id = rawr.getInt("id");
                int event = rawr.getInt("evento");
                String obj = rawr.getString("obj");
                String quando = rawr.getString("quando");
                LocalDate when = null;
                if (quando != null){
                    when = LocalDate.parse(quando);
                }
                boolean done = rawr.getBoolean("done");
                LocalDate created = LocalDate.parse(rawr.getString("creato"));
                Event gioia = new Event(id, event, obj, when, done, created);
                evee.add(gioia);
            }
        } catch (Exception a){
            a.printStackTrace();
        }
        return evee;
    }

    public static ArrayList<Event> Find(Event e){
        ArrayList<Event> res = new ArrayList<>();
        try {
            String query = FIND_SINGLE;
            ArrayList<Object> values = new ArrayList<>();
            Connection c = getConnection();
            int count = 0;
            if (e.event() != null && e.event() <4 && e.event() > 0){
            count +=1;
            query = cAnd(count, query) + EVENTO;
            values.add(e.event());
            }
            if(e.GetObj() != null){
                count +=1;
                query = cAnd(count, query) + OBJ;
                values.add(e.GetObj());
            }
            if(e.GetWhen() != null){
                count +=1;
                query = cAnd(count, query) + QUANDO;
                values.add(e.GetWhen().toString());
            }
            if(e.GetCreated() != null){
                count +=1;
                query = cAnd(count, query) + CREATO;
                values.add(e.GetCreated().toString());
            }
            if (e.GetDone() != null){
            count +=1;
            query = cAnd(count, query) + DONE;
            values.add(e.GetDone());
            }
            PreparedStatement bs = c.prepareStatement(query);
            for (int i = 0; i < values.size(); i++){
                bs.setObject(i+1, values.get(i));
            }
            ResultSet rawr = bs.executeQuery();
            while (rawr.next()){
                int id = rawr.getInt("id");
                int event = rawr.getInt("evento");
                String obj = rawr.getString("obj");
                String quando = rawr.getString("quando");
                LocalDate when = null;
                if (quando != null){
                    when = LocalDate.parse(quando);
                }
                boolean done = rawr.getBoolean("done");
                LocalDate created = LocalDate.parse(rawr.getString("creato"));
                Event gioia = new Event(id, event, obj, when, done, created);
                res.add(gioia);
            }
        } catch (SQLException a){
            a.printStackTrace();
        }
        return res;
    }

    public static boolean Update(Event gioie){
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(UPDATE);
            String a = null;
            if (gioie.GetWhen() != null){
                a = gioie.GetWhen().toString();
            }
            bs.setInt(1, gioie.event());
            bs.setString(2, gioie.GetObj());
            bs.setString(3, a);
            if(gioie.GetDone() == null){
            bs.setBoolean(4, false);
            } else {
                bs.setBoolean(4, gioie.GetDone());
            }
            bs.setString(5, gioie.GetCreated().toString());
            bs.setInt(6, gioie.GetId());
            return bs.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Kill(int id){
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(DELETE);
            bs.setInt(1, id);
            return bs.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static String Tipo(int id){
            String res = "";
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(FIND);
            bs.setInt(1, id);
            ResultSet rawr = bs.executeQuery();
            if(rawr.next()){
                return rawr.getString("tipo");
            }
            return res;
        } catch (SQLException e){
            e.printStackTrace();
            return res;
        }
    }
}

// last line