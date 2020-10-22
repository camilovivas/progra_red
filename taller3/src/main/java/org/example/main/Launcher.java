package org.example.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.MainWindows;

public class Launcher extends Application {

    public static void main ( String[] args ) {
        launch ( args );
    }

    @Override
    public void start ( Stage primaryStage ) {
        MainWindows m =  new MainWindows ();
        m.show ();
    }
}
