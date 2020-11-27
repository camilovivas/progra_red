package model.providers;

import db.MySQLConnection;
import entity.Task;
import model.dto.TaskDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TaskProvider {

    public ArrayList<TaskDTO> getAllTaskTODO ( ) {
        ArrayList<TaskDTO> toReturn = new ArrayList<> ( );
        MySQLConnection sql = new MySQLConnection ( );
        try {
            String src = "SELECT * FROM tasks where faseID = 1";
            ResultSet r = sql.query ( src );
            while ( r.next ( ) ) {
                TaskDTO taskDTO = new TaskDTO ( );
                int f = r.getInt ( 2 );
                String t = r.getString ( 3);
                long d = r.getLong ( 4 );
                taskDTO.setFase ( f );
                taskDTO.setTask ( t );
                taskDTO.setFecha ( converDate ( d ) );
                toReturn.add ( taskDTO );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        sql.offConnect ();
        return toReturn;
    }

    public ArrayList<TaskDTO> getAllTaskDOING ( ) {
        ArrayList<TaskDTO> toReturn = new ArrayList<> ( );
        MySQLConnection sql = new MySQLConnection ( );
        try {
            String src = "SELECT * FROM tasks where faseID = 2";
            ResultSet r = sql.query ( src );
            while ( r.next ( ) ) {
                TaskDTO taskDTO = new TaskDTO ( );
                int f = r.getInt ( 2 );
                String t = r.getString ( 3);
                long d = r.getLong ( 4 );
                taskDTO.setFase ( f );
                taskDTO.setTask ( t );
                taskDTO.setFecha ( converDate ( d ) );
                toReturn.add ( taskDTO );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        sql.offConnect ();
        return toReturn;
    }

    public ArrayList<TaskDTO> getAllTaskDone ( ) {
        ArrayList<TaskDTO> toReturn = new ArrayList<> ( );
        MySQLConnection sql = new MySQLConnection ( );
        try {
            String src = "SELECT * FROM tasks where faseID = 3";
            ResultSet r = sql.query ( src );
            while ( r.next ( ) ) {
                TaskDTO taskDTO = new TaskDTO ( );
                int f = r.getInt ( 2 );
                String t = r.getString ( 3);
                long d = r.getLong ( 4 );
                taskDTO.setFase ( f );
                taskDTO.setTask ( t );
                taskDTO.setFecha ( converDate ( d ) );
                toReturn.add ( taskDTO );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        sql.offConnect ();
        return toReturn;
    }

    public String converDate(long fecha){
        Date d = new Date ( fecha);
        String date = d.toString ();
        System.out.println (date );
        return date;
    }

    public void insertTask ( Task task ) {
        MySQLConnection sql = new MySQLConnection ( );
        String src = "INSERT INTO tasks(faseID, task, fecha) values (" + task.getFaseID ( ) + ", '$task'," + task.getFecha ( ) + ")";
        src = src.replace ( "$task", task.getTask ( ) );
        sql.executeSQL ( src );

    }

    public void deleteTask (String task ) {
        MySQLConnection sql = new MySQLConnection ( );
        sql.executeSQL ( "DELETE FROM tasks where task = '"+task+"'" );

    }

    public int  search(String task){
        int toReturn = 0;
        MySQLConnection sql = new MySQLConnection ( );
        try {
        ResultSet r = sql.query ( "SELECT * FROM tasks where task = '"+task+"'" );
            r.next ();
            toReturn = r.getInt ( 2 );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        sql.offConnect ();
        return toReturn;
    }

    public void avanzaTask ( String task ) {
        MySQLConnection sql = new MySQLConnection ( );
        int fase = search ( task );
        sql.executeSQL ( "update tasks SET faseID ="+(fase+1)+" where tasks.task = '"+task+"'" );
    }

    public void retrocedeTask(String task){
        MySQLConnection sql = new MySQLConnection ( );
        int fase = search ( task );
        sql.executeSQL ( "update tasks SET faseID ="+(fase-1)+" where tasks.task = '"+task+"'" );
    }

    public Task mapFromDTO ( TaskDTO taskDTO ) {
        Task toReturn = new Task ( );
        toReturn.setFaseID ( taskDTO.getFase ( ) );
        toReturn.setTask ( taskDTO.getTask ( ) );
        System.out.println (toReturn.getFecha () );
        return toReturn;
    }

}
