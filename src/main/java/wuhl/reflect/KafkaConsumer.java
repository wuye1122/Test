package wuhl.reflect;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * kafka生产者demo
 *
 * @author hongyu 2017-12-5
 */
public class KafkaConsumer {
    private final ConsumerConnector consumer;
    public final static String KAFKA_TOPIC = "agentProxy";

    private KafkaConsumer() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "10.130.41.164:2181,10.130.41.170:2181,10.130.41.166:2181");
        props.put("group.id", "kafkagroup666");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("zookeeper.session.timeout.ms", "5000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
                props.put("auto.offset.reset", "smallest");
        props.put("rebalance.max.retries", "5");
        props.put("rebalance.backoff.ms", "1200");
                props.put("serializer.class", "kafka.serializer.StringEncoder");
        ConsumerConfig config = new ConsumerConfig(props);
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(KAFKA_TOPIC, new Integer(1));
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(KAFKA_TOPIC).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext())
            System.out.println(it.next().message());
    }

    public static void main(String[] args) {
        new KafkaConsumer().consume();
    }
}

