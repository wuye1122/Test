package wuhl.reflect;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * kafka
 *
 * @author hongyu 2017-12-5
 */
public class KafkaProducer {
    private final Producer<String, String> producer;
    public final static String KAFKA_TOPIC = "test_topic";
    public final static int MESSAGE_NUM = 100;

    private KafkaProducer() {
        Properties props = new Properties();
        /**
         * 此处配置的是kafka的端口
         */
        props.put("metadata.broker.list", "node1:9092,node2:9092,node3:9092");

        /**
         *  配置value的序列化类
         */
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        /**
         * 配置key的序列化类
         */
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    private void produce() {
        for (int i = 0; i < MESSAGE_NUM; i++) {
            String data = "hello kafka message " + i;
            producer.send(new KeyedMessage<String, String>(KAFKA_TOPIC, data));
            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        new KafkaProducer().produce();
    }
}
