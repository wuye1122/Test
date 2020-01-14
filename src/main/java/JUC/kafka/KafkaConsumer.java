package JUC.kafka;
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

import org.apache.commons.lang.StringUtils;


public class KafkaConsumer {
	         
  //bin/kafka-topics.sh --create --zookeeper qc1:2181,qc2:2181,qc3:2181 --replication-factor 3 --partitions 1 –topic ent_record_fastdfs_url

	private final ConsumerConnector consumer;
	public final static String KAFKA_TOPIC = "agentProxy";// agentProxy
	public final static String URL = "http://localhost:8087/dps2/initPush/add.do";
	public static final String KAFKA_LIST = "qc1:9092,qc1:9093,qc1:9094";
	public static final String CONNECT_URL = "qc1:2181,qc1:2182,qc1:2183";

	private KafkaConsumer() {
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
	//	props.put("serializer.class", "kafka.serializer.StringEncoder");
		ConsumerConfig config = new ConsumerConfig(props);
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
	}

	void consume() {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(KAFKA_TOPIC, new Integer(1));// 订阅过程
		System.out.println(topicCountMap);
		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(
				new VerifiableProperties());
		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer
				.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
		//
		// List<KafkaStream<String, String>> list= consumerMap.get(KAFKA_TOPIC);

		KafkaStream<String, String> stream = consumerMap.get(KAFKA_TOPIC)
				.get(0);
		ConsumerIterator<String, String> it = stream.iterator();
		while (it.hasNext()) {
			String msg = it.next().message();
			System.out.println("推送！" + msg);

			if (StringUtils.isNotBlank(msg)) {

				System.out.println("接收参数："+msg);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("data", msg);
				// 开始进行数据推送
				try {

					// String result = HttpPushDataUtils.httpPostRequest(URL,
					// params);

				} catch (Exception e) {
					System.out.println("推送失败！" + e);

					// e.printStackTrace();
				}
			}

		}
	}

	    public static void main(String[] args) {
	        new KafkaConsumer().consume();
	    }
}
