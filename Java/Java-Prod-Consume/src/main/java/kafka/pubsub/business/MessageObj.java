package kafka.pubsub.business;

import java.io.Serializable;

public class MessageObj implements Serializable {
    private String key;
    private String data;

    public MessageObj () {}

    public MessageObj (String key, String data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ key : " + this.key + ", data : " + this.data + " }";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
