package model;

public class Message {

    private String id;
    private String body;
    private String type;

    public Message () {
    }

    public Message ( String id, String body) {
        this.id = id;
        this.body = body;
        this.type = type;
    }

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getBody ( ) {
        return body;
    }

    public void setBody ( String body ) {
        this.body = body;
    }

    public String getType ( ) {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }
}
