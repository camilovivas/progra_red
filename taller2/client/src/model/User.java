package model;

public class User {
    private String type = "User";
    private String userName;

    public User ( String userName ) {
        this.userName = userName;
    }

    public User ( ) {
    }

    public String getUserName ( ) {
        return userName;
    }

    public String getType ( ) {
        return type;
    }
}
