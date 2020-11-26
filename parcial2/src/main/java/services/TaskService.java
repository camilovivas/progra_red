package services;

import jdk.nashorn.internal.objects.annotations.Getter;
import model.dto.TaskDTO;
import model.providers.TaskProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Path ( "task" )
@Stateless
public class TaskService {

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createTask ( TaskDTO taskDTO ) {
        TaskProvider taskProvider = new TaskProvider ( );
        taskProvider.insertTask ( taskProvider.mapFromDTO ( taskDTO ) );
    }


    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path ( "allTODO" )
    public ArrayList<TaskDTO> getAllTasksTODO(){
        TaskProvider taskProvider = new TaskProvider ();
        return taskProvider.getAllTaskTODO ();
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path ( "allDOING" )
    public ArrayList<TaskDTO> getAllTasksDOING(){
        TaskProvider taskProvider = new TaskProvider ();
        return taskProvider.getAllTaskDOING ();
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path ( "allDONE" )
    public ArrayList<TaskDTO> getAllTasks(){
        TaskProvider taskProvider = new TaskProvider ();
        return taskProvider.getAllTaskDone ();
    }

    @DELETE
    @Produces("application/json")
    @Path ( "delete/{task}" )
    public void deleteTask(@PathParam ("task") String task ){
        TaskProvider taskProvider = new TaskProvider ();
        taskProvider.deleteTask (task);
    }

    @PUT
    @Path ( "subir/{task}" )
    public void subirFase(@PathParam  ("task") String task ){
        TaskProvider taskProvider = new TaskProvider ();
        taskProvider.avanzaTask ( task );
    }

    @PUT
    @Path ( "baja/{task}" )
    public void bajaFase(@PathParam  ("task") String task ){
        TaskProvider taskProvider = new TaskProvider ();
        taskProvider.retrocedeTask ( task );
    }

}
