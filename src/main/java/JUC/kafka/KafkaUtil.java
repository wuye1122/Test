package JUC.kafka;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaUtil {
	 
	  /* public static final String KAFKA_LIST = "10.130.29.55:9092,10.130.29.56:9092,10.130.29.57:9092";
	 
	   public static final String CONNECT_URL = "10.130.29.55:2181,10.130.29.56:2181,10.130.29.57:2181";
	   */
	
	   public static final String KAFKA_LIST = "qc1:9092,qc2:9093,qc3:9094";
	 
	   public static final String CONNECT_URL = "qc1:2181,qc2:2182,qc3:2183";
	   
	   public static final String STRING_ENCODER = "kafka.serializer.StringEncoder";

	    // 10.130.29.55:2181,10.130.29.56:2181,10.130.29.57:2181  qc1 qc2 qc3
	   //kafka.broker.list=qc1:9092,qc2:9092,qc3:9092
	
	/**
	 * @author JUC
	 * void
	 */

	 private  static Producer kafkaProducer = null;

	    static {
	        initKafka();
	    }

	    public  static Producer getProducer() {
	        return kafkaProducer;
	    }


	    private static void initKafka() {
	        Properties properties = new Properties();
	        properties.put("metadata.broker.list", KAFKA_LIST);
	        properties.put("zookeeper.connect", CONNECT_URL);
	        properties.put("serializer.class", STRING_ENCODER);
	        kafkaProducer = new Producer<Integer, String>(new ProducerConfig(properties));
	    }
	    
	    public static boolean sendMessage(String topic,String message){
	    	try{
	    		kafkaProducer.send(new KeyedMessage<Integer, String>(topic, message));
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		return false;
	    	}
	    	return true;
	    }
	    
	    public static void main(String[] args) {
//			sendMessage("t_srvappraise", "{\"id\":\"123\"}");
	        sendMessage("ivr_message", "{\"entId\":\"123456\", \"agentId\":\"1001\",\" userField \":\"test\",\" sessionId \":\" 665928dec7000048:123456:1001 \", \" strAni \":\"10010\",\" strDnis \":\"10086\", \" event \":\"EVENT_OUTBOUND_ALERTING_TP\",\" timestamp\":\"2017-06-09 14:00:00\"}");
		}

}
