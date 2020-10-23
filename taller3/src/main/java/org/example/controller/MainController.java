package org.example.controller;


import org.example.view.*;

public class MainController {

    private MainWindows window;

    public MainController(MainWindows window){
        this.window = window;
        acctionInsert ();
        acctionSearch ();
    }

    public void acctionInsert(){
        window.getAddMovie ().setOnAction (
                e->{
                    InsertWindow iw = new InsertWindow ();
                    iw.show();
                    window.close ();
                }
        );
    }

    public void acctionSearch(){
        window.getSearch ().setOnAction (
                e->{
                    SearchWindow sw = new SearchWindow ();
                    sw.show ();

                }
        );
    }

}
