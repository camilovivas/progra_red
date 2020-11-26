package db;

import java.sql.*;

public class MySQLConnection {

    private Connection connection;

    public MySQLConnection(){
        try {
            Class.forName ( "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    public void onConnect ( ) {
        try {
            connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/parcial2", "root", "" );
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

    public void executeSQL(String sql){
        onConnect ();
        try {
            Statement  statement = connection.createStatement ( );
            statement.execute ( sql );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        finally {
            offConnect ();
        }
    }

    public ResultSet query(String sql){
        ResultSet toReturn = null;
        onConnect ();
        try {
            Statement  statement = connection.createStatement ( );
            toReturn = statement.executeQuery ( sql );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        }
        return  toReturn;
    }
}
