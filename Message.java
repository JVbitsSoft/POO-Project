import java.io.Serializable;

public class Message implements Serializable {
    public String username;
    public String text;

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
    }
}
