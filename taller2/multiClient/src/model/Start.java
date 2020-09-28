package model;

import com.google.gson.Gson;
import comm.OnConnectionListenner;
import comm.OnMessageListenner;
import comm.Session;
import comm.TCPConnection;

public class Start implements OnConnectionListenner, OnMessageListenner {

    private TCPConnection connection;

    public Start(){
        connection = TCPConnection.getInstance ();
        connection.setPuerto ( 5000 );
        connection.start ();
        connection.setConnectionListenner ( this );
        connection.setMessageListenner ( this );

    }

    @Override
    public void onConnection ( String id) {

        System.out.println ("nuevo cliente conectado: " + id );
    }

    @Override
    public void onMessage (Session s, String msg ) {
        Gson gson = new Gson();
        Generic type = gson.fromJson(msg, Generic.class);

        switch(type.getType()) {
            case "Message":
                connection.sendBroadcast ( msg );
                break;
            case "DirectMessage":
                DirectMessage priv = gson.fromJson ( msg, DirectMessage.class );


                break;
            case "User":
                User user = gson.fromJson ( msg, User.class );
                if(!connection.searchClient ( user.getUserName () )){
                    connection.addClient ( s,user );
                }
                else{
                    //TODO AVISAR A AL CLIENTE QUE QUEDO MALA Y QUE REBOTE OTRA VEEZ LLA PANTALLA DE LOGIN Y DARLE .CLOSE A LA CONEXION
                }
                break;

        }
    }
}
