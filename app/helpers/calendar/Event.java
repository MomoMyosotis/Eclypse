// first line

package app.helpers.calendar;
import java.time.LocalDate;
import app.config.db.DbHandler;

public class Event{
    private Integer id;
    private Integer event;
    private String obj;
    private LocalDate when;
    private Boolean done;
    private LocalDate created;

    public Event(Integer id, Integer event, String obj, LocalDate when, Boolean done, LocalDate created){
        this.id = id;
        this.event = event;
        this.obj = obj;
        this.when = when;
        this.done = done;
        this.created = created;
    }

    public Integer GetId(){
        return id;
    }
    public Integer event(){
        return event;
    }
    public String GetObj(){
        return obj;
    }
    public LocalDate GetWhen(){
        return when;
    }
    public Boolean GetDone(){
        return done;
    }
    public LocalDate GetCreated(){
        return created;
    }

    public Boolean SetDone(Event e){
        Event quack = new Event(e.GetId(), e.event(), e.GetObj(), e.GetWhen(), true, e.GetCreated());
        try{
            DbHandler.Events.Update(quack);
            return true;
        } catch (Exception a){
            a.printStackTrace();
            return false;
        }
    }
}

// last line