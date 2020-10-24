package org.example.controller;

import org.example.view.Result;
import org.example.view.SearchWindow;

public class SearchController {

    private SearchWindow window;

    public SearchController ( SearchWindow window ) {
        this.window = window;
        moviesByGenero ();
    }

    public void moviesByGenero(){
        window.getB1 ().setOnAction (
                e->{
                    Result r = new Result ( 1 );
                    r.show ();
                }
        );

    }

    public void moviesByActor(){

    }

    public void actoresByMovie(){

    }
}
