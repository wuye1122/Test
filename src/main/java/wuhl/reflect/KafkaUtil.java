package wuhl.reflect;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;


/**
 * Created by yaoml on 16/3/15.
 */
public final class KafkaUtil {

    private  static Producer kafkaProducer = null;

    static {
        initKafka();
    }

    public  static Producer getProducer() {
        return kafkaProducer;
    }


    private static void initKafka() {
        Properties properties = new Properties();
       /* properties.put("metadata.broker.list", Config.getString("kafka.broker.list"));
        properties.put("zookeeper.connect", Config.getString("kafka.zookeeper.connect"));*/
        properties.put("metadata.broker.list", "kafka.broker.list");
        properties.put("zookeeper.connect", "kafka.zookeeper.connect");
        properties.put("serializer.class", StringEncoder.class.getName());
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
//		sendMessage("t_srvappraise", "{\"id\":\"123\"}");
        sendMessage("agentProxy", "{\"entId\":\"123456\", \"agentId\":\"1001\",\" userField \":\"test\",\" sessionId \":\" 665928dec7000048:123456:1001 \", \" strAni \":\"10010\",\" strDnis \":\"10086\", \" event \":\"EVENT_OUTBOUND_ALERTING_TP\",\" timestamp\":\"2017-06-09 14:00:00\"}");
	}

}
