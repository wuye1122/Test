package wuhl.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class KafkaProducer {

	/**
	 * @author wuhl
	 * void
	 */
    private final Producer<String, String> producer;
	public final static String KAFKA_TOPIC = "test2";// test_topic
	public final static int MESSAGE_NUM = 100;
	

	/*10.130.29.55 qc1
	10.130.29.56 qc2
	10.130.29.57 qc3*/
	
	//public static final String KAFKA_LIST = "10.130.41.164:9092,10.130.41.166:9092,10.130.41.170:9092";
	public static final String KAFKA_LIST = "kafka001:9092,kafka002:9092,kafka003:9092";

	public static final String CONNECT_URL = "kafka001:2181,kafka002:2181,kafka003:2181";
	/*public static final String KAFKA_LIST = "10.130.41.89:9092";
	public static final String CONNECT_URL = "10.130.41.89:2181,10.130.41.89:2182,10.130.41.89:2183";*/
	    private KafkaProducer() {
	        Properties props = new Properties();
	        /**
	         * �˴����õ���kafka�Ķ˿�
	         */
	        props.put("metadata.broker.list", KAFKA_LIST);


	        /**
	         *  ����value�����л���
	         */
	        props.put("serializer.class", "kafka.serializer.StringEncoder");

	        /**
	         * ����key�����л���
	         */
	        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
	       // props.put("auto.create.topics.enable", "true");

	        producer = new Producer<String, String>(new ProducerConfig(props));
	    }

	    private void produce() {
	
          
	          
	          try {
	  			// read file content from file
	  			StringBuffer sb = new StringBuffer("");

	  			FileReader reader = new FileReader(
	  					"C:\\Users\\Administrator.USER-20161101FI\\Desktop\\800��ҵ\\����ͷ���ӿ�\\205.json");
	  			BufferedReader br = new BufferedReader(reader);

	  			String str = null;
	  			int a=0;
	  			while ((str = br.readLine()) != null) {
	  				sb.append(str + "/n");
	  		        a++;	  		          
	  		          //call_detail 
	  		          //new_r_ags_e
	  		        System.out.println("call_detail:"+str);
		  		//   �Լ���kafka����topic��    
	  			//   producer.send(new KeyedMessage<String, String>("call_detail", str));

	  		    //   storm�����Ǵ�topic��    
	  			  producer.send(new KeyedMessage<String, String>("call_detail", str));

	  				}
	  			System.out.println(a);

	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
	          

	     /*   for (int i = 0; i < MESSAGE_NUM; i++) {
	            String data = "������..hello kafka message " + i;
	            String dps=""
	        	System.out.println("��ʼ����������Ϣ:"+data);
                try {
					Thread.sleep(1000);
					if(i%2==0){
						System.out.println("dps----���ĵ�����...:"+data);
						 producer.send(new KeyedMessage<String, String>("dps", data));

					}else{
						System.out.println("wuhl----���ĵ�����...:"+data);
						 producer.send(new KeyedMessage<String, String>("wuhl", data));

					}
			         System.out.println("�������...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	        }*/
	    }

	    public static void main(String[] args) {
	        new KafkaProducer().produce();
	    }


}
