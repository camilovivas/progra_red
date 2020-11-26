package config;

import services.TaskService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath ( "api" )
public class ApplicationConfig extends Application {


    @Override
    public Set<Class<?>> getClasses ( ) {
        Set<Class<?>> resourses = new HashSet<> (  );

        resourses.add ( TaskService.class );
        return resourses;
    }
}
