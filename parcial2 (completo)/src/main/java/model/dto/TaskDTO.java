package model.dto;

public class TaskDTO {

    private int fase;
    private String task;
    private String fecha;

    public TaskDTO ( ) {
    }

    public TaskDTO ( int fase, String task,String fecha  ) {
        this.fase = fase;
        this.task = task;
        this.fecha = fecha;
    }

    public String getTask ( ) {
        return task;
    }

    public int getFase ( ) {
        return fase;
    }

    public void setFase ( int fase ) {
        this.fase = fase;
    }

    public void setTask ( String task ) {
        this.task = task;
    }

    public String getFecha ( ) {
        return fecha;
    }

    public void setFecha ( String fecha ) {
        this.fecha = fecha;
    }
}
