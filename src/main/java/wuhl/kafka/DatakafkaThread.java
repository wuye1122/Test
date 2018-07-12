package wuhl.kafka;

import com.alibaba.fastjson.JSONObject;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.log4j.Logger;

//���߳�����kafka���� һ���߳�һ��topic δ����
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
            logger.info(" ��ǰ��ҵ��" + entIdByMessage + "��topic:��" + topic + "��Kafka���յ����� " + message);

            JSONObject sessionObject = JSONObject.parseObject(message);
            String sessionId = sessionObject.getString("session_id") == null ? sessionObject.getString("sessionId") : sessionObject.getString("session_id");
            logger.debug("��������session_id:��" + sessionId + "��");
            //������ҵ����

            try {
                logger.info("��ʼ����Kafka���ݣ�" + message);
                //�������͵�ͬһ���̳߳�����


                //session_id/num ����̳߳�
            } catch (Exception e) {
                logger.debug("�����쳣...session_id:" + String.valueOf(sessionId));

                e.printStackTrace();
            }
        }
    }


    private String getEntIdByMessage(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        return jsonObject.getString("entId") == null ? jsonObject.getString("ent_id") : jsonObject.getString("entId");
    }

}