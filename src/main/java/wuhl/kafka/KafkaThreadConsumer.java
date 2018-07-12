package wuhl.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.*;
import java.util.concurrent.*;

public class KafkaThreadConsumer {

    public static int MAX_BOLOCK_QUEUE =  2048;
    public static BlockingQueue blockQueue = new ArrayBlockingQueue<Runnable>(MAX_BOLOCK_QUEUE, true);
    private static int CORE_THREADS = 8;//核心执行线程数
    private static int MAX_THREADS = 16;//最大执行线程数
    public static ExecutorService executorService = new ThreadPoolExecutor(CORE_THREADS, MAX_THREADS, 1,
            TimeUnit.MINUTES, blockQueue);
    //使用ExecutorService来调度线程（针对Kafka）
    public static ExecutorService executorForKafka = Executors.newCachedThreadPool();

    private static ConsumerConfig initKafkaConfig() {
        System.out.println("进入DdsEventClientUtil.initKafkaConfig()方法，正在创建kafka连接器....");
        // 创建kafka连接器
        Properties props = new Properties();
        props.put("zookeeper.connect", "kafka001:2181,kafka002:2181,kafka003:2181");
        props.put("group.id", "kafkagroup666");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("zookeeper.session.timeout.ms", "5000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        props.put("rebalance.max.retries", "5");
        props.put("rebalance.backoff.ms", "1200");
        // 创建配置对象
        return new ConsumerConfig(props);
    }
    private static void createKafkaConnect(ConsumerConfig config,List<String> topics) {
        System.out.println("进入DdsEventClientUtil.createKafkaConnect()方法，正在创建kafka 主题【"+topics+"】连接....");
        List<KafkaStream<String,String>> rsList = new ArrayList<>();
        // 创建配置对象
        ConsumerConnector conn = Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String topic:topics){
            map.put(topic, new Integer(1));
        }
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String,String>>> msg = conn.createMessageStreams(map,keyDecoder,valueDecoder);

        for(String topic:topics){
            List<KafkaStream<String,String>> msgList =  msg.get(topic);
            for (int i = 0; i < msgList.size(); i++) {
                KafkaStream<String,String> kafkaStream = msgList.get(i);
                executorForKafka.submit(new DatakafkaThread(kafkaStream,topic));
            }
        }
    }
    public static void main(String[] args) {


    }
}
