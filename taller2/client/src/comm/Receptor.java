package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Receptor extends Thread {

    private InputStream is;
    private OnMessageListenner listener;

    public Receptor(InputStream is){
        this.is = is;
    }

    @Override
    public void run () {
        try {
            BufferedReader br = new BufferedReader ( new InputStreamReader ( is ) );

            while(true){
                String msg = br.readLine ();
                if(listener != null)listener.onMessage ( msg );
            }

        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public void setListenner(OnMessageListenner listener){
        this.listener = listener;
    }
}
