package controller;

import com.google.gson.Gson;
import comm.OnMessageListenner;
import comm.TCPConnection;
import model.*;
import view.MultichatWindows;

import java.util.UUID;

public class MultiChatController implements OnMessageListenner {
    private MultichatWindows windows;
    private TCPConnection connection;


    public MultiChatController(MultichatWindows input){
        connection = TCPConnection.getInstance ();
        this.windows = input;
    }

    public void btSendAction(){
        windows.getSend ().setOnAction (
                (e)->{
                    String id = UUID.randomUUID ().toString ();
                    String message = windows.getMessage ().getText ();
                    Message msg = new Message ( id, message );
                    Gson gson = new Gson ();
                    String send = gson.toJson ( msg );

                }
        );
    }

    @Override
    public void onMessage ( String msg ) {

        Gson gson = new Gson();


    }
}
