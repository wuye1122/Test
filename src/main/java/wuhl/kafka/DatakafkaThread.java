package wuhl.kafka;

import com.alibaba.fastjson.JSONObject;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.log4j.Logger;

//单线程消费kafka数据 一个线程一个topic 未分区
public class DatakafkaThread implements Runnable {
    protected static Logger logger = Logger.getLogger(DatakafkaThread.class);
    KafkaStream<String, String> kafkaStream = null;
    public String topic;

    public DatakafkaThread(KafkaStream<String, String> kafkaStream, String topic) {
        super();
        this.kafkaStream = kafkaStream;
        this.topic = topic;
    }

    @Override
    public void run() {
        ConsumerIterator<String, String> iterator = kafkaStream.iterator();
        while (iterator.hasNext()) {
            long start = System.currentTimeMillis();
            String message = new String(iterator.next().message());

            String entIdByMessage = getEntIdByMessage(message);
            logger.info(" 当前企业【" + entIdByMessage + "】topic:【" + topic + "】Kafka接收的数据 " + message);

            JSONObject sessionObject = JSONObject.parseObject(message);
            String sessionId = sessionObject.getString("session_id") == null ? sessionObject.getString("sessionId") : sessionObject.getString("session_id");
            logger.debug("正在推送session_id:【" + sessionId + "】");
            //缓存企业集合

            try {
                logger.info("开始推送Kafka数据：" + message);
                //数据推送到同一个线程池里面


                //session_id/num 多个线程池
            } catch (Exception e) {
                logger.debug("推送异常...session_id:" + String.valueOf(sessionId));

                e.printStackTrace();
            }
        }
    }


    private String getEntIdByMessage(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        return jsonObject.getString("entId") == null ? jsonObject.getString("ent_id") : jsonObject.getString("entId");
    }

}