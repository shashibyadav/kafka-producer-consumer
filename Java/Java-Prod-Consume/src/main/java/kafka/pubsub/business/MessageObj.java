package kafka.pubsub.business;

import java.io.Serializable;

public class MessageObj implements Serializable {
    private String data;

    public MessageObj () {}

    public MessageObj (String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
