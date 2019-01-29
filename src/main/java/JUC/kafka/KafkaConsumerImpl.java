package JUC.kafka;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import org.apache.commons.lang.StringUtils;



public class KafkaConsumerImpl  {
	

	
	public void consume() {
		Properties props = new Properties();
           
		props.put("zookeeper.connect", "qc1:2181,qc1:2182,qc1:2183");
		props.put("group.id", "kafkagroup666");
		props.put("serializer.class","kafka.serializer.StringEncoder");
		props.put("zookeeper.session.timeout.ms", "5000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "smallest");
		props.put("rebalance.max.retries", "5");
		props.put("rebalance.backoff.ms", "1200");

/*		props.put("zookeeper.connect", Config.getString(Config.CONNECT_URL));
		props.put("group.id", Config.getString("group.id"));
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("zookeeper.session.timeout.ms", Config.getString("5000"));
		props.put("zookeeper.sync.time.ms", Config.getString("200"));
		props.put("auto.commit.interval.ms", Config.getString("1000"));
		props.put("auto.offset.reset", Config.getString("smallest"));//
		props.put("rebalance.max.retries", Config.getString("5"));
		props.put("rebalance.backoff.ms", Config.getString("1200"));*/
		ConsumerConfig config = new ConsumerConfig(props);
		ConsumerConnector	consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
	//	topicCountMap.put(Config.getString(Config.KAFKA_TOPIC), new Integer(1));// 订阅过程
		topicCountMap.put("agentProxy", new Integer(1));
		System.out.println(topicCountMap);
		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(
				new VerifiableProperties());
		Map<String, List<KafkaStream<String, String>>> consumerMap = consumer
				.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
		//
		// List<KafkaStream<String, String>> list= consumerMap.get(KAFKA_TOPIC);

		KafkaStream<String, String> stream = consumerMap.get("agentProxy")
				.get(0);
		ConsumerIterator<String, String> it = stream.iterator();
		while (it.hasNext()) {
			String msg = it.next().message();

			if (StringUtils.isNotBlank(msg)) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("data", msg);
				try {

					// String result = HttpPushDataUtils.httpPostRequest(URL,
					// params);

				} catch (Exception e) {
					System.out.println();
				}
			}

		}
	}
	
	/* public void subscribeForKafka( ) {
	    

	        Properties props = new Properties();
	        props.put("zookeeper.connect", Config.getString("zookeeper.connect"));
	        props.put("group.id", "12");
	        props.put("zookeeper.session.timeout.ms", Config.getString("zookeeper.session.timeout.ms"));
	        props.put("zookeeper.sync.time.ms", Config.getString("zookeeper.sync.time.ms"));
	        props.put("auto.commit.interval.ms", Config.getString("auto.commit.interval.ms"));
	        props.put("auto.offset.reset", Config.getString("auto.offset.reset"));
	        props.put("rebalance.max.retries", Config.getString("rebalance.max.retries"));
	        props.put("rebalance.backoff.ms", Config.getString("rebalance.backoff.ms"));
	        props.put("serializer.class",  Config.getString("serializer.class"));
	        // 创建配置对象
	        ConsumerConfig config = new ConsumerConfig(props);

	        // 创建kafka连接�?
	        ConsumerConnector conn = Consumer.createJavaConsumerConnector(config);
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        String topic = eventType;
	        map.put("test", new Integer(1));
	        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
	        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
	        Map<String, List<KafkaStream<String, String>>> msg = conn.createMessageStreams(map, keyDecoder, valueDecoder);
	        List<KafkaStream<String, String>> msgList = msg.get("test");
	        for (int i = 0; i < msgList.size(); i++) {
	            KafkaStream<String, String> kafkaStream = msgList.get(i);
	        }
	    }*/

	    public static void main(String[] args) {
	        new KafkaConsumerImpl().consume();
	    }
}
