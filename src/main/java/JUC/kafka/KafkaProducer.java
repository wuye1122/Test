package JUC.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Properties;

public class KafkaProducer {

	/**
	 * @author JUC
	 * void
	 */
	private static Logger logger = Logger.getLogger(KafkaProducer.class);

	private final Producer<String, String> producer;
	public final static String KAFKA_TOPIC = "agent";// test_topic
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
	    	  long start = System.currentTimeMillis();






			try {
	  			// read file content from file
	  			StringBuffer sb = new StringBuffer("");
				  int a=0;
				/*  while(true){*/
					  for(int i=0;i<1;i++){                              // 204.json session.json  call_detail.json  sd_call_result
						//  FileReader reader = new FileReader("E:/JUC/����/���������ļ�/JUC/800��ҵ/2018��һ֮ǰ/����ͷ���ӿ�/session.json");
						  FileReader reader=null;
						  String topic="";
						  if(i%3==0){
							//  reader  = new FileReader("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\topic\\����ͷ���ӿ�\\test.json");
							  reader  = new FileReader("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\test1.json");
							  topic = "test4";
						  }
						  if(i%3==1){
							  reader  = new FileReader("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\topic\\����ͷ���ӿ�\\test1.json");
						      topic = "test1";
						  }
						  if(i%3==2){
							  reader  = new FileReader("E:\\JUC\\����\\���������ļ�\\JUC\\800��ҵ\\topic\\����ͷ���ӿ�\\test2.json");
						      topic = "test2";
						  }
						  System.out.println("i:"+i);
						  BufferedReader br = new BufferedReader(reader);

						  String str = null;

						  while ((str = br.readLine()) != null) {
							  sb.append(str + "/n");
							  a++;
							  //call_detail
							  //new_r_ags_e
							/*  if(a%10==0){
								  logger.debug("��ǰ���⣺session_detail���ݣ�"+str);
								  System.out.println("��ǰʱ��:"+new Date(System.currentTimeMillis())+"��ǰ���⣺session_detail���ݣ�"+str);
								  producer.send(new KeyedMessage<String, String>("session_detail", str));
							  }else{
								  logger.debug("��ǰʱ��:"+new Date(System.currentTimeMillis())+"��ǰ���⣺agentProxy���ݣ�"+str);
								  System.out.println("��ǰʱ��:"+new Date(System.currentTimeMillis())+"��ǰ���⣺agentProxy���ݣ�"+str);
								  Thread.sleep(1000);
								  producer.send(new KeyedMessage<String, String>("agentProxy", str));
							  }*/

							  //   �Լ���kafka����topic��
							  Thread.sleep(5000);
							   producer.send(new KeyedMessage<String, String>("ent_record_fastdfs_url", str));
							  System.out.println("topic:["+topic+"] str:["+str+"]"); //ent_record_fastdfs_url  sd_call_result  session_detail

							  //   storm�����Ǵ�topic��

						  }
					  }

					  logger.debug("һ���������ݸ�����"+a+"] ����ʱ�� ��"+String.valueOf(System.currentTimeMillis()-start)+"ms��");
					  System.out.println("һ���������ݸ�����"+a+"] ����ʱ�� ��"+String.valueOf(System.currentTimeMillis()-start)+"ms��");
					  logger.debug(start);
					  logger.debug(System.currentTimeMillis());
					  System.out.println(start);
					  System.out.println(System.currentTimeMillis());
					  System.out.println(new Date(start));
					  System.out.println(new Date(System.currentTimeMillis()));
					  //��������15min
              /*        if(System.currentTimeMillis()-start==900000){
                      	//��ǰ����900000
                      	return;
					  }
				  }*/


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
						System.out.println("JUC----���ĵ�����...:"+data);
						 producer.send(new KeyedMessage<String, String>("JUC", data));

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
