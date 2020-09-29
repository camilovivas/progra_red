package model;

import java.util.ArrayList;

public class UserInside {

    private String type = "UserInside";
    private ArrayList<String> sessions;

    public UserInside ( ) {
    }

    public UserInside ( ArrayList<String> sessions ) {
        this.sessions = sessions;
    }

    public ArrayList<String> getSessions ( ) {
        return sessions;
    }
}
