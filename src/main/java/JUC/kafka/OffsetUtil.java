package JUC.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import com.alibaba.fastjson.JSONObject;

public class OffsetUtil {

	/**
	 * @author JUC
	 * void
	 */
	
	public static final String KAFKA_LIST = "kafka001:9092,kafka002:9092,kafka003:9092";

	public static final String CONNECT_URL = "kafka001:2181,kafka002:2181,kafka003:2181";//rollback.zookeeper=10.130.29.55:2181

	public static final String IP="qc1,qc2,qc3";//域名解析还是IP
	
	public static final String TOPICS="call_detail,agentProxy";//topic 获取zookeeper下面所有topic

	
	private static Map<String,SimpleConsumer>  consumerList;
	private static List<String> seeds;
	private static ZooKeeper zk;
	private static final int SESSION_TIME = 2000;
	protected static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	private static Logger logger=Logger.getLogger(OffsetUtil.class);
	private static final int KAFKA_PORT=9092;
//	private static final int ZOOKEEPER_PORT=2181;
	private static final int KAFKA_PARTITION=0; // 要查找的分区  ,现有的kafka只有一个分区0
	
	
	public static void init(){
		try{
			if(zk==null||consumerList==null){
				//创建zookeeper连接
				zk = new ZooKeeper(CONNECT_URL, SESSION_TIME, new Watcher() { 
		            // 监控所有被触发的事件
		            public void process(WatchedEvent event) { 
		            	logger.info("更改配置" + event.getType());
		        		if (event.getState() == KeeperState.SyncConnected) {
		        			countDownLatch.countDown();
		        		}
		            } 
		        });
				//创建消费者
				seeds=Arrays.asList(IP.split(","));
				
				List<String> topicList=Arrays.asList(TOPICS.split(","));
				consumerList=new HashMap<String,SimpleConsumer>();
				for(String topic:topicList){
					SimpleConsumer consumer=getConsumer(topic);
				    consumerList.put(topic, consumer);
				}
			}
		}catch(Exception e){
			logger.error("OffsetUtil初始化数据失败：",e);
		}
	}

	public static long[] getOffset(String topic,String topoName){
		logger.info("进入OffsetUtil.getOffset()方法,开始获取["+topic+":"+topoName+"]偏移量……");
		long[] offset=new long[]{0L,0L};
		try{
			SimpleConsumer consumer =consumerList.get(topic);
			if(consumer==null){
				consumer=getConsumer(topic);
				if(consumer==null){
					return offset;
				}
				consumerList.put(topic, consumer);
			}
			 
		      //kafka最新消息偏移量
		      long lastOffset = getLastOffset(consumer,topic, KAFKA_PARTITION, kafka.api.OffsetRequest.LatestTime(), "hehe");  
		      offset[0]=lastOffset;
		      logger.debug("获取到["+topic+":"+topoName+"]的当前最大偏移量："+offset[0]);
		      
		      String stormOffsetStr =new String(zk.getData("/stormOffset/" + topoName
						+ "/partition_0", true, null));
		      JSONObject obj = JSONObject.parseObject(stormOffsetStr);
		      
		      long stormOffset=obj.getLongValue("offset");
		      offset[1]=stormOffset;
		      
		      logger.info("获取到["+topic+":"+topoName+"]的当前最大偏移量："+offset[0]+",storm消费偏移量："+offset[1]);
		      return offset;
		}catch(Exception e){
			logger.error("获取["+topic+":"+topoName+"]偏移量报错：",e);
		}
		return offset;
	}
	
	public static long getKafkaOffset(String topic){
		logger.info("进入OffsetUtil.getKafkaOffset()方法,开始获取["+topic+"]偏移量……");
		try{
			SimpleConsumer consumer =consumerList.get(topic);
			if(consumer==null){
				consumer=getConsumer(topic);
				if(consumer==null){
					return 0L;
				}
				consumerList.put(topic, consumer);
			}
			 
		      //kafka最新消息偏移量
		      long lastOffset = getLastOffset(consumer,topic, KAFKA_PARTITION, kafka.api.OffsetRequest.LatestTime(), "hehe");  
		      logger.debug("获取到["+topic+"]的kafka当前最大偏移量："+lastOffset);
		      
		      
		      return lastOffset;
		}catch(Exception e){
			logger.error("获取["+topic+"]kafka偏移量报错：",e);
		}
		return 0L;
	}
	
	private static SimpleConsumer getConsumer(String topic){
		PartitionMetadata metadata = findLeader(seeds, KAFKA_PORT, topic, KAFKA_PARTITION);
		 if (metadata == null) {  
	          logger.error("获取topic："+topic+"分区0的元数据失败！");  
	          return null;
		 }  
	     if (metadata.leader() == null) {  
	          logger.error("获取topic："+topic+"分区0的leader失败！");  
	          return null;
	     }  
	      //找到leader broker  
	      String leadBroker = metadata.leader().host(); 
//	      int p=9092;
//	      if(leadBroker.equals("iZ252xfruaxZ")){
//	    	  p=9991;
//	      }else if(leadBroker.equals("iZ256t6sywkZ")){
//	    	  p=9992;
//	      }else if(leadBroker.equals("iZ25a89t14wZ")){
//	    	  p=9993;
//	      }
//	      SimpleConsumer consumer = new SimpleConsumer("127.0.0.1",p , 100000, 64 * 1024, topic); 
	      SimpleConsumer consumer = new SimpleConsumer(leadBroker, KAFKA_PORT, 100000, 64 * 1024, topic); 
	      return consumer;
	}
	/**
	 * 
	 * @param a_seedBrokers   broker节点的ip
	 * @param a_port
	 * @param a_topic   要订阅的topic
	 * @param a_partition 要查找的分区
	 * @return
	 */
	private static PartitionMetadata findLeader(List<String> a_seedBrokers, int a_port, String a_topic, int a_partition) {  
        PartitionMetadata returnMetaData = null;  
        for (String seed : a_seedBrokers) {  
            SimpleConsumer consumer = null;  
            try {  
                consumer = new SimpleConsumer(seed, a_port, 100000, 64 * 1024, "leaderLookup");  
//                consumer = new SimpleConsumer("127.0.0.1", Integer.parseInt(seed), 100000, 64 * 1024, "leaderLookup");  
                List<String> topics = Collections.singletonList(a_topic);  
                TopicMetadataRequest req = new TopicMetadataRequest(topics);  
                kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);  
                List<TopicMetadata> metaData = resp.topicsMetadata();  
                for (TopicMetadata item : metaData) {  
                    for (PartitionMetadata part : item.partitionsMetadata()) {  
                        if (part.partitionId() == a_partition) {  
                            returnMetaData = part;  
                            break;  
                        }  
                    }  
                }  
            } catch (Exception e) {  
                logger.error("连接 Broker [" + seed + "] to find Leader for [" + a_topic + ", " + a_partition + "]报错: " + e);  
            } finally {  
                if (consumer != null)  
                    consumer.close();  
            }  
        }  
        return returnMetaData;  
    }  
	
	private static long getLastOffset(SimpleConsumer consumer, String topic, int partition, long whichTime, String clientName) {  
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);  
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();  
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));  
        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientName);  
        OffsetResponse response = consumer.getOffsetsBefore(request);  
  
        if (response.hasError()) {  
            logger.error("获取偏移量失败: " + response.errorCode(topic, partition));  
            return 0;  
        }  
        long[] offsets = response.offsets(topic, partition);  
        return offsets[0];  
    }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
