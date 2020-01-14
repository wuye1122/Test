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
	         * 此处配置的是kafka的端口
	         */
	        props.put("metadata.broker.list", KAFKA_LIST);


	        /**
	         *  配置value的序列化类
	         */
	        props.put("serializer.class", "kafka.serializer.StringEncoder");

	        /**
	         * 配置key的序列化类
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
						//  FileReader reader = new FileReader("E:/JUC/桌面/其他桌面文件/JUC/800企业/2018五一之前/今日头条接口/session.json");
						  FileReader reader=null;
						  String topic="";
						  if(i%3==0){
							//  reader  = new FileReader("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\topic\\今日头条接口\\test.json");
							  reader  = new FileReader("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\test1.json");
							  topic = "test4";
						  }
						  if(i%3==1){
							  reader  = new FileReader("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\topic\\今日头条接口\\test1.json");
						      topic = "test1";
						  }
						  if(i%3==2){
							  reader  = new FileReader("E:\\JUC\\桌面\\其他桌面文件\\JUC\\800企业\\topic\\今日头条接口\\test2.json");
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
								  logger.debug("当前主题：session_detail数据："+str);
								  System.out.println("当前时间:"+new Date(System.currentTimeMillis())+"当前主题：session_detail数据："+str);
								  producer.send(new KeyedMessage<String, String>("session_detail", str));
							  }else{
								  logger.debug("当前时间:"+new Date(System.currentTimeMillis())+"当前主题：agentProxy数据："+str);
								  System.out.println("当前时间:"+new Date(System.currentTimeMillis())+"当前主题：agentProxy数据："+str);
								  Thread.sleep(1000);
								  producer.send(new KeyedMessage<String, String>("agentProxy", str));
							  }*/

							  //   自己推kafka不带topic的
							  Thread.sleep(5000);
							   producer.send(new KeyedMessage<String, String>("ent_record_fastdfs_url", str));
							  System.out.println("topic:["+topic+"] str:["+str+"]"); //ent_record_fastdfs_url  sd_call_result  session_detail

							  //   storm解析是带topic的

						  }
					  }

					  logger.debug("一共生产数据个数【"+a+"] 共耗时间 【"+String.valueOf(System.currentTimeMillis()-start)+"ms】");
					  System.out.println("一共生产数据个数【"+a+"] 共耗时间 【"+String.valueOf(System.currentTimeMillis()-start)+"ms】");
					  logger.debug(start);
					  logger.debug(System.currentTimeMillis());
					  System.out.println(start);
					  System.out.println(System.currentTimeMillis());
					  System.out.println(new Date(start));
					  System.out.println(new Date(System.currentTimeMillis()));
					  //持续推送15min
              /*        if(System.currentTimeMillis()-start==900000){
                      	//当前推送900000
                      	return;
					  }
				  }*/


			  }catch(Exception e){
	  		e.printStackTrace();
	  	}
	          

	     /*   for (int i = 0; i < MESSAGE_NUM; i++) {
	            String data = "新推送..hello kafka message " + i;
	            String dps=""
	        	System.out.println("开始推送最新消息:"+data);
                try {
					Thread.sleep(1000);
					if(i%2==0){
						System.out.println("dps----订阅的主题...:"+data);
						 producer.send(new KeyedMessage<String, String>("dps", data));

					}else{
						System.out.println("JUC----订阅的主题...:"+data);
						 producer.send(new KeyedMessage<String, String>("JUC", data));

					}
			         System.out.println("推送完成...");
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
