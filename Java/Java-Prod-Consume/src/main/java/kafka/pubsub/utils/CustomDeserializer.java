package kafka.pubsub.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.pubsub.business.MessageObj;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class CustomDeserializer implements Deserializer<MessageObj> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public MessageObj deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageObj obj = null;
        try {
            obj = objectMapper.readValue(bytes, MessageObj.class);
        } catch (Exception ex) {
            System.out.println("Error in deserializing data" + ex.getMessage());
        }
        return obj;
    }

    @Override
    public MessageObj deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public MessageObj deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
