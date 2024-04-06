package kafka.pubsub.entrypoint;

import kafka.pubsub.business.MessageObj;
import kafka.pubsub.config.KafkaConstant;
import kafka.pubsub.factory.ProducerCreator;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class RunProducer {

    private static String uniqueId = UUID.randomUUID().toString();

    private static MessageObj getMessageObjectFromStd () {
        MessageObj messageObj = new MessageObj();
        System.out.println("Write data for message");
        Scanner scanner = new Scanner(System.in);
        messageObj.setData(scanner.nextLine());
        System.out.println("Message created");
        return messageObj;
    }
    public static void runProducer() {
        try {
            Producer<String, MessageObj> producer = ProducerCreator.createProducer();
            int counter = 0;
            while(true) {
                MessageObj message = RunProducer.getMessageObjectFromStd();
                String recordIndex = uniqueId + "_" + counter;
                message.setKey(recordIndex);
                ProducerRecord<String, MessageObj> record = new ProducerRecord<String, MessageObj>(KafkaConstant.TOPIC_NAME, recordIndex, message);
                try {
                    RecordMetadata metadata = producer.send(record).get();
                    System.out.println("Record sent with key " + uniqueId + " to partition " + metadata.partition()
                            + " with offset " + metadata.offset());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                counter++;
            }
        } catch (Exception ex) {
            System.out.println("Error in creating producer" + ex.getMessage());
        }
    }

}
