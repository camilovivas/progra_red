package org.example.controller;


import org.example.view.*;

public class MainController {

    private MainWindows window;

    public MainController ( MainWindows window ) {
        this.window = window;
        actionInsert ( );
        actionSearch ( );
        actionDelete ( );
    }

    public void actionDelete ( ) {
        window.getDeleteMovie ( ).setOnAction (
                e -> {
                    DeleteWindow dw = new DeleteWindow ( );
                    dw.show ( );
                    window.close ( );
                }
        );
    }

    public void actionInsert ( ) {
        window.getAddMovie ( ).setOnAction (
                e -> {
                    InsertWindow iw = new InsertWindow ( );
                    iw.show ( );
                    window.close ( );
                }
        );
    }

    public void actionSearch ( ) {
        window.getSearch ( ).setOnAction (
                e -> {
                    SearchWindow sw = new SearchWindow ( );
                    sw.show ( );
                    window.close ();
                }
        );
    }

}
