package kafka.pubsub.entrypoint;

import kafka.pubsub.business.MessageObj;
import kafka.pubsub.config.KafkaConstant;
import kafka.pubsub.factory.ConsumerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class RunConsumer {

    public static void runConsumer() {
        Consumer<Long, MessageObj> consumer = null;
        try {
            consumer = ConsumerCreator.createConsumer();
            int noMessageFound = 0;

            while (true) {
                ConsumerRecords<Long, MessageObj> consumerRecords = consumer.poll(1000);
                if (consumerRecords.count() == 0) {
                    noMessageFound++;
                    if (noMessageFound > KafkaConstant.MAX_NO_MESSAGE_FOUND_COUNT) {
                        // If no message found count is reached to threshold exit loop.
                        break;
                    }
                    else {
                        Thread.sleep(5000);
                        continue;
                    }
                }
                consumerRecords.forEach(record -> {
                    System.out.println("Record Key " + record.key());
                    System.out.println("Record value " + record.value());
                    System.out.println("Record partition " + record.partition());
                    System.out.println("Record offset " + record.offset());
                });

                consumer.commitAsync();
            }

        } catch (Exception ex) {
            System.out.println("Error in creating consumer");
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }

}
