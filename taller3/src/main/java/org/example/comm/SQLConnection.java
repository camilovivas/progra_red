package org.example.comm;

import org.example.model.*;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnection {

    private Connection connection;

    public SQLConnection ( ) {
        onConnect ( );
    }

    public void onConnect ( ) {
        try {
            connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/taller3", "root", "" );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
    }

    public void offConnect ( ) {
        try {
            connection.close ( );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
    }

    public void init ( ) {
        try {
            Statement statement = connection.createStatement ( );

            statement.execute ( "CREATE TABLE genero (id INT PRIMARY KEY AUTO_INCREMENT, tipo VARCHAR(100))" );
            statement.execute ( "CREATE TABLE peliculas (id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(100), year INT, generoID INT, FOREIGN KEY (generoID) REFERENCES genero(id))" );
            statement.execute ( "CREATE TABLE actores (id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(100), apellido VARCHAR(100))" );
            statement.execute ( "INSERT INTO genero (tipo) VALUES (\"comedia\"),(\"terror\"),(\"romance\"),(\"suspenso\")" );

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
    }

    public ArrayList<Genero> getAllGeneros ( ) {
        Statement statement = null;
        ArrayList<Genero> toReturn = new ArrayList<> ( );
        try {
            statement = connection.createStatement ( );
            ResultSet generos = statement.executeQuery ( "select * from genero" );
            while ( generos.next ( ) ) {
                int id = generos.getInt ( 1 );
                String tipo = generos.getString ( 2 );
                toReturn.add ( new Genero ( id, tipo ) );
            }

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }

    public ArrayList<Movie> getAllMovies ( ) {
        Statement statement = null;
        ArrayList<Movie> toReturn = new ArrayList<> ( );
        try {
            statement = connection.createStatement ( );
            ResultSet movies = statement.executeQuery ( "select * from peliculas" );
            while ( movies.next ( ) ) {
                int id = movies.getInt ( 1 );
                String nombre = movies.getString ( 2 );
                int year = movies.getInt ( 3 );
                int idgenero = movies.getInt ( 4 );
                toReturn.add ( new Movie ( id, nombre, year, idgenero ) );
            }

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }

    public ArrayList<Actor> getAllActores ( ) {
        Statement statement = null;
        ArrayList<Actor> toReturn = new ArrayList<> ( );
        try {
            statement = connection.createStatement ( );
            ResultSet actores = statement.executeQuery ( "select * from actores" );
            while ( actores.next ( ) ) {
                int id = actores.getInt ( 1 );
                String nombre = actores.getString ( 2 );
                String apellido = actores.getString ( 3 );
                toReturn.add ( new Actor ( id, nombre, apellido ) );
            }

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }


    public ArrayList<String> search ( String type, int index ) {
        Statement statement = null;
        String sql = "";
        ArrayList<String> toReturn = new ArrayList<> ( );
        try {
            statement = connection.createStatement ( );
            if ( index == 1 ) {
                sql = "SELECT peliculas.nombre, peliculas.year, actores.nombre, actores.apellido, genero.tipo FROM (peliculas INNER JOIN tabla_pivote ON peliculas.id = tabla_pivote.peliculaID) INNER JOIN actores ON tabla_pivote.actorID = actores.id INNER JOIN genero ON peliculas.generoID = genero.id WHERE genero.tipo = '" + type + "'";
            }
            else if(index == 2){
                sql = "SELECT peliculas.nombre, peliculas.year, actores.nombre, actores.apellido, genero.tipo FROM (peliculas INNER JOIN tabla_pivote ON peliculas.id = tabla_pivote.peliculaID) INNER JOIN actores ON tabla_pivote.actorID = actores.id INNER JOIN genero ON peliculas.generoID = genero.id WHERE actores.nombre = '" + type + "'";
            }
            else{
                sql = "SELECT peliculas.nombre, peliculas.year, actores.nombre, actores.apellido, genero.tipo FROM (peliculas INNER JOIN tabla_pivote ON peliculas.id = tabla_pivote.peliculaID) INNER JOIN actores ON tabla_pivote.actorID = actores.id INNER JOIN genero ON peliculas.generoID = genero.id WHERE peliculas.nombre = '" + type + "'";
            }
            ResultSet datos = statement.executeQuery ( sql );
            while ( datos.next ( ) ) {
                String nombre = datos.getString ( 1 );
                int year = datos.getInt ( 2 );
                String nombreActor = datos.getString ( 3 );
                String apellidoActor = datos.getString ( 4 );
                String tipoGenero = datos.getString ( 5 );
                toReturn.add ( "NOMBRE: " + nombre + " FECHA: " + year + " ACTOR: " + nombreActor + " " + apellidoActor + " GENERO: " + tipoGenero );
            }

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }

    public int searchIdGenero ( String genero ) {
        int toReturn = 0;
        try {
            Statement statement = connection.createStatement ( );
            String sql = "SELECT * FROM genero WHERE genero.tipo = '" + genero + "'";
            ResultSet generos = statement.executeQuery ( sql );
            generos.next ( );
            toReturn = generos.getInt ( 1 );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }

    public int searchIdActor ( String nameActor ) {
        int toReturn = 0;
        String[] split = nameActor.split ( " " );
        try {
            Statement statement = connection.createStatement ( );
            String sql = "SELECT * FROM actores WHERE actores.nombre = '" + split[0] + "'";
            ResultSet generos = statement.executeQuery ( sql );
            generos.next ( );
            toReturn = generos.getInt ( 1 );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }

    public int searchIdMovie ( String nameMovie ) {
        int toReturn = 0;
        try {
            Statement statement = connection.createStatement ( );
            String sql = "SELECT * FROM peliculas WHERE peliculas.nombre = '" + nameMovie + "'";
            ResultSet movies = statement.executeQuery ( sql );
            movies.next ( );
            toReturn = movies.getInt ( 1 );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }


    public void insertMovie ( Movie movie ) {
        try {
            Statement statement = connection.createStatement ( );
            String sql = ("INSERT INTO peliculas (nombre, year, generoID) VALUES ('$NOMBRE',$YEAR, $GENERO)")
                    .replace ( "$NOMBRE", movie.getNombre ( ) )
                    .replace ( "$YEAR", "" + movie.getYear ( ) )
                    .replace ( "$GENERO", "" + movie.getGeneroID ( ) );
            statement.execute ( sql );

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
    }

    public void joinMovieAndActor ( int movieID, int actorID ) {
        try {
            Statement statement = connection.createStatement ( );
            String sql = ("INSERT INTO tabla_pivote (peliculaID, actorID) VALUES ('$PELICULAID','$ACTORID')")
                    .replace ( "$PELICULAID", movieID + "" )
                    .replace ( "$ACTORID", actorID + "" );
            statement.execute ( sql );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }

    }

    public void insertActor ( Actor actor ) {
        try {
            Statement statement = connection.createStatement ( );
            String sql = ("INSERT INTO actores (nombre, apellido) values ('$NOMBRE' , '$APELLIDO')")
                    .replace ( "$NOMBRE", actor.getNombre ( ) )
                    .replace ( "$APELLIDO", actor.getApellido ( ) );
            statement.execute ( sql );

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
    }

    public void deleteMovie ( String nombre ) {
        try {
            Statement statement = connection.createStatement ( );
            int id = searchIdMovie ( nombre );
            String sql = "DELETE FROM tabla_pivote WHERE  tabla_pivote.peliculaID =" + id;
            String sql2 = "DELETE FROM peliculas WHERE peliculas.id =" + id;

            statement.execute ( sql );
            statement.execute ( sql2 );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
    }
}
