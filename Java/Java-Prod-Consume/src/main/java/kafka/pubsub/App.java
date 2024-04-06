package kafka.pubsub;

import kafka.pubsub.entrypoint.RunConsumer;
import kafka.pubsub.entrypoint.RunProducer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length > 0 && args[0].equals("PRODUCER")) {
            RunProducer.runProducer();
        } else {
            RunConsumer.runConsumer();
        }
    }
}
