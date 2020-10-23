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
        ArrayList<Genero> toReturn = null;
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

    public ArrayList<Movie> searchMovieByGenero( int genero){
        Statement statement = null;
        ArrayList<Movie> toReturn = null;
        try {
            statement = connection.createStatement ( );
            ResultSet movies = statement.executeQuery ( "select * from peliculas where peliculas.generoID ="+ genero );
            //TODO PROBAR LINEA EN MYADMIN
            while ( movies.next ( ) ) {
                int id = movies.getInt ( 1 );
                String nombre = movies.getString ( 2 );
                int year = movies.getInt ( 3 );
                int generoID = movies.getInt ( 4 );
                toReturn.add ( new Movie ( id, nombre, year, generoID ) );
            }

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return toReturn;
    }
}
