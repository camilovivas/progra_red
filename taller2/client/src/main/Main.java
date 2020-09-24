package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginWindow log = new LoginWindow ();
        log.show ();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
