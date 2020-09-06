package model;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commandos {

    public String mensajes (String input) {
        String toReturn = "";

        if ( input.equalsIgnoreCase ( "remoteIpconfig" )) {
            toReturn = remoteIpconfig ( );
        }
        if ( input.equalsIgnoreCase ( "interface" )) {
            toReturn = myInterface ( );
        }
        if ( input.equalsIgnoreCase ( "whatTimeisit")) {
            toReturn = time ( );
        }
        if ( input.getBytes ( ).length == 1024 ) {
            System.out.println ("si");
            toReturn = input;
        }
        if ( input.getBytes ( ).length == 8192 ) {
            toReturn = input;
        }
        return toReturn;
    }

    public String myInterface(){
        String toReturn = "";
        try {
            InetAddress adress = InetAddress.getLocalHost();
            NetworkInterface net = NetworkInterface.getByInetAddress(adress);
            toReturn = net.getName();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public String remoteIpconfig(){
        String toReturn ="";
        try {
            InetAddress adress = InetAddress.getLocalHost();
            toReturn = adress.getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public String time(){
        DateFormat df = new SimpleDateFormat ("HH:MM:SS");
        Date d = new Date ();
        return df.format (d);
    }
//1024

//8192

}
