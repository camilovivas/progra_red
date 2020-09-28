package comm;

public interface OnMessageListenner {

    void onMessage(Session s, String msg);
}
