package entity;

import java.util.Date;

public class Task {

    private String id;
    private String task;
    private int faseID;
    private long fecha;

    public Task ( ) {
        this.fecha = DateToDay ();
    }

    public Task ( String id, String task, int faseID ) {
        this.id = id;
        this.task = task;
        this.faseID = faseID;
    }

    public String getId ( ) {
        return id;
    }

    public String getTask ( ) {
        return task;
    }

    public int getFaseID ( ) {
        return faseID;
    }

    public long getFecha ( ) {
        return fecha;
    }

    public void setTask ( String task ) {
        this.task = task;
    }

    public void setFaseID ( int faseID ) {
        this.faseID = faseID;
    }

    public void setFecha ( long fecha ) {
        this.fecha = fecha;
    }

    public long DateToDay(){
        Date d = new Date ( );
        System.out.println (d.getTime () );
        return d.getTime ();
    }

}
