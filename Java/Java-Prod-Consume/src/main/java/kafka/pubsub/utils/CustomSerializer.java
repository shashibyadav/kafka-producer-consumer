package kafka.pubsub.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.pubsub.business.MessageObj;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<MessageObj> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, MessageObj messageObj) {
        byte[] response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, MessageObj data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
