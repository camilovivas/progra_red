package org.example.controller;

import org.example.view.Result;
import org.example.view.SearchWindow;

public class SearchController {

    private SearchWindow window;

    public SearchController ( SearchWindow window ) {
        this.window = window;
        moviesByGenero ( );
        moviesByActor ();
        actoresByMovie ();
    }

    public void moviesByGenero ( ) {
        window.getB1 ( ).setOnAction (
                e -> {
                    Result r = new Result ( 1 );
                    r.show ( );
                    window.close ();
                }
        );

    }

    public void moviesByActor ( ) {
        window.getB3 ( ).setOnAction (
                e -> {
                    Result r = new Result ( 3 );
                    r.show ( );
                    window.close ();
                }
        );

    }

    public void actoresByMovie ( ) {
        window.getB2 ( ).setOnAction (
                e -> {
                    Result r = new Result ( 2 );
                    r.show ( );
                    window.close ();
                }
        );
    }
}
