package model;

import comm.Session;

import java.util.ArrayList;

public class UserInside {

    private String type = "UserInside";
    private ArrayList<Session> sessions;

    public UserInside ( ) {
    }

    public UserInside ( ArrayList<Session> sessions ) {
        this.sessions = sessions;
    }

    public ArrayList<Session> getSessions ( ) {
        return sessions;
    }
}
