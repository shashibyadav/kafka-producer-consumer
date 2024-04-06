package kafka.pubsub.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class KafkaConstant {
    public static String KAFKA_BROKERS = "localhost:9092";

    public static Integer MESSAGE_COUNT=1000;

    public static String CLIENT_ID="client1";

    public static String TOPIC_NAME="first-topic";

    public static String GROUP_ID_CONFIG="consumerGroup1";

    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;

    public static String OFFSET_RESET_LATEST="latest";

    public static String OFFSET_RESET_EARLIER="earliest";

    public static Integer MAX_POLL_RECORDS=1;

    static {
        FileReader fileReader = null;
        JSONObject config = null;
        boolean configFound = false;
        try {
            fileReader = new FileReader("./config.json");
            config = (JSONObject) new JSONParser().parse(fileReader);
            configFound = true;
        } catch (IOException | ParseException e) {
//            throw new RuntimeException(e);
        }
        if (configFound) {
            if (config.containsKey("KAFKA_BROKERS")) {
                List<Object> addressList = (List<Object>) (config.get("KAFKA_BROKERS"));
                List<String> addresses = addressList.stream().map(o -> {
                    JSONObject temp = (JSONObject) o;
                    String host = (String) temp.get("host");
                    String port = (String) temp.get("port");
                    return host + ":" + port;
                }).collect(Collectors.toList());
                KafkaConstant.KAFKA_BROKERS = String.join(",", addresses);
            }
            if (config.containsKey("MAX_POLL_RECORDS")) {
                KafkaConstant.MAX_POLL_RECORDS = ((Long) (config.get("MAX_POLL_RECORDS"))).intValue();
            }
            if (config.containsKey("MESSAGE_COUNT")) {
                KafkaConstant.MESSAGE_COUNT = ((Long) (config.get("MESSAGE_COUNT"))).intValue();
            }
            if (config.containsKey("MAX_NO_MESSAGE_FOUND_COUNT")) {
                KafkaConstant.MAX_NO_MESSAGE_FOUND_COUNT = ((Long) (config.get("MAX_NO_MESSAGE_FOUND_COUNT"))).intValue();
            }
            if (config.containsKey("CLIENT_ID")) {
                KafkaConstant.CLIENT_ID = (String) (config.get("CLIENT_ID"));
            }
            if (config.containsKey("TOPIC_NAME")) {
                KafkaConstant.TOPIC_NAME = (String) (config.get("TOPIC_NAME"));
            }
            if (config.containsKey("GROUP_ID_CONFIG")) {
                KafkaConstant.GROUP_ID_CONFIG = (String) (config.get("GROUP_ID_CONFIG"));
            }
            if (config.containsKey("OFFSET_RESET_LATEST")) {
                KafkaConstant.OFFSET_RESET_LATEST = (String) (config.get("OFFSET_RESET_LATEST"));
            }
            if (config.containsKey("OFFSET_RESET_EARLIER")) {
                KafkaConstant.OFFSET_RESET_EARLIER = (String) (config.get("OFFSET_RESET_EARLIER"));
            }
        }
    }
}
