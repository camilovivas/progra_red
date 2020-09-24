package comm;

import java.io.*;

public class Emisor {

    private OutputStream os;
    private BufferedWriter bwriter;

    public Emisor(OutputStream os){
        this.os = os;
        bwriter = new BufferedWriter ( new OutputStreamWriter ( os ) );
    }

    public void sendMessage(String msg){
        new Thread (
                () -> {
                    try {
                        bwriter.write ( msg + "\n" );
                        bwriter.flush ();
                    } catch (IOException e) {
                        e.printStackTrace ( );
                    }
                }
        ).start ();
    }
}
