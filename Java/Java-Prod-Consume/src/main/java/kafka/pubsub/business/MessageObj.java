package kafka.pubsub.business;

import java.io.Serializable;

public class MessageObj implements Serializable {
    private long key;
    private String data;

    public MessageObj () {}

    public MessageObj (long key, String data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ key : " + this.key + ", data : " + this.data + " }";
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
