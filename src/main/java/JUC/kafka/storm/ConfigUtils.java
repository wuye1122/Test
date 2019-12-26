package JUC.kafka.storm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.EventType.None;

/**
 * <dl>
 * <dt>ConfigUtils</dt>
 * <dd>Description:��ȡ������������</dd>
 * <dd>CreateDate: 2016-3-16</dd>
 * </dl>
 *
 * @author
 */
public class ConfigUtils extends GenericObjectPoolConfig implements Watcher {

	private static Log logger = LogFactory.getLog(ConfigUtils.class);
	private static Properties prop = new Properties();
	// mongo��ַ
	public static String MONGO_HOST;
	// mongo��������
	public static int MONGO_POOLSIZE = 100;
	// mongo�ȴ����г���
	public static int MONGO_BLOCKSIZE = 100;
	// ����worker����
	public static int TOPO_WORK_NUM = 2;
	// ����spout�߳���
	public static int TOPO_SPOUT_NUM = 2;
	// �����м���ڵ���߳���
	public static int TOPO_COMPUBOLT_NUM = 3;
	// ���������ڵ���߳���
	public static int TOPO_MONGOBOLT_NUM = 3;
	// �����м���ڵ���߳���
	public static int RAGSE_TOPO_COMPUBOLT_NUM = 1;
	// ���������ڵ���߳���
	public static int RAGSE_TOPO_MONGOBOLT_NUM = 5;

	public static int MAX_SPOUT_PENDING = 500;
	// ��offset�㱨���ĸ�zk��Ⱥ,��Ӧ����
	public static String OFFSET_ZKPORT = "2181";
	// �㱨offset��Ϣ��root·��
	public static String OFFSET_ZKROOT = "/stormOffset";
	// �Ƿ���DTS,1��dts��0û��dts
	public static String OFF_ON_DTS = "0";
	// ������zookeeper�ڵ�·��

	/**
	 * �Ƿ��������ݵ�kafka
	 */
	private final static String isSendMessage = "/storm/config/SEND_MESSAGE";
	/**
	 *
	 */
	private final static String entIdMessage = "/storm/config/ENTID_MESSAGE_TEST";
	private final static String messageTopic = "/storm/config/MESSAGE_TOPIC";
	private final static String kafkaZookeeperConnect = "/storm/config/KAFKA_ZOOKEEPER_CONNECT";
	private final static String kafkaBrokerList = "/storm/config/KAFKA_BROKER_LIST";
	public static String ENTID_MESSAGE_TEST;
	public static String SEND_MESSAGE;
	public static String MESSAGE_TOPIC;
	public static String KAFKA_ZOOKEEPER_CONNECT;
	public static String KAFKA_BROKER_LIST;
	// mongo��ַ
//	/public static String REDIS_HOSTS1;





	private static final int SESSION_TIME = 2000;
	protected ZooKeeper zk;
	protected CountDownLatch countDownLatch = new CountDownLatch(1);

	private static Properties properties = null;
	/** redis������� **/
	//public final static String REDIS_HOSTS1 = "redis.hosts";
	public final static String REDIS_PASSWORDS1 = "redis.passwords";
	public final static String MASTER_NAME1 = "master_name";
	public final static String REDIS_MAX_ACTIVE1 = "redis.max_active";
	public final static String REDIS_MAX_IDLE1 = "redis.max_idle";
	public final static String REDIS_MAX_WAIT1 = "redis.max_wait";
	public final static String REDIS_TEST_ON_BORROW1 = "redis.test_on_borrow";
	public final static String REDIS_TEST_ON_RETURN1 = "redis.test_on_return";
	public final static String DB_NUMBER1 = "redis.db.number";

	public final static String CAHTLOG_TIME = "chatlog.timeInMillis";

	// ��ʼ������
	public void connect(String hosts) throws IOException, InterruptedException {
		logger.info("����zookeeper....");
		zk = new ZooKeeper(hosts, SESSION_TIME, this);
		countDownLatch.await();
	}

	// ������ÿ�θ��ĵ�ʱ�򱻵���
	public void process(WatchedEvent event) {
		System.out.println("��������" + event.getType());
		if (event.getState() == KeeperState.SyncConnected) {
			System.out.println("��ʼִ��process����-----event:"+event);
			if(event.getType() == None){
				//��ؽڵ�û�б仯
				countDownLatch.countDown();
			}/*else if(Event.EventType.NodeDataChanged == event.getType()&&event.getPath().equals("/storm/config/ENTID_MESSAGE_TEST")){ //����ظýڵ�����ݷ��������ʱ�򴥷�
				System.out.println("��ǰ�ڵ�·����"+event.getPath()+"�������¼����ͣ�"+event.getType());
				//ֻ�����ҵ�ڵ�
					try {
						ENTID_MESSAGE_TEST = new String(zk.getData(event.getPath(), true, null));
						System.out.println("����֮��ڵ���Ϣ��"+ENTID_MESSAGE_TEST);
					} catch (KeeperException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}*/

			}
	}

	// �ر�����
	public void close() throws InterruptedException {
		logger.info("�ر�zookeeper����!");
		zk.close();
	}


	/**
	 * ��ʼ��mongodb
	 */
	public void initKafka() {
		try {
			System.out.println("�����ʼ��kafka...");
			byte[] bytes = zk.getData(entIdMessage, new Watcher() {
				public void process(WatchedEvent event) {
					System.out.println("����session_detaild���ӽڵ�ı�.."+event.getPath()+"�����¼�.."+event.getType());
					System.out.println("�ڵ�仯"+event.getType());
				}
			}, null);
			ENTID_MESSAGE_TEST = new String(bytes);
			SEND_MESSAGE = new String(zk.getData(isSendMessage, true, null));
			MESSAGE_TOPIC = new String(zk.getData(messageTopic, true, null));
			KAFKA_ZOOKEEPER_CONNECT =  new String(zk.getData(kafkaZookeeperConnect, true, null));
			KAFKA_BROKER_LIST =  new String(zk.getData(kafkaBrokerList, true, null));
			System.out.println("kafka��ؽڵ�:SEND_MESSAGE="+SEND_MESSAGE+";MESSAGE_TOPIC="+MESSAGE_TOPIC
					+";KAFKA_ZOOKEEPER_CONNECT="+KAFKA_ZOOKEEPER_CONNECT+";KAFKA_BROKER_LIST="
					+KAFKA_BROKER_LIST +";��ҵENTID_MESSAGE_TEST :"+ENTID_MESSAGE_TEST);
		} catch (Exception e) {
			logger.warn("��ʼ��kafka��Ϣ�쳣�������˳���", e);
			System.exit(0);
		}
	}


	public static String getString(String key) {
		return properties.getProperty(key);
	}

	public static String getString(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static int getInt(String key, int defaultValue) {
		return Integer.parseInt(getString(key, String.valueOf(defaultValue)));
	}

	public static long getLong(String key, long defaultValue) {
		return Long.parseLong(getString(key, String.valueOf(defaultValue)));
	}


	private static ConfigUtils config;
	private static List<JedisPool> jedisPools;
	private static List<Jedis> sentinelList;
	private static int currentSentinels;

	// private static Properties properties = null;
	public static String REDIS_HOSTS;
	public static String REDIS_PASSWORDS;
	public static int MAX_ACTIVE;
	public static int MAX_IDLE;
	public static long MAX_WAIT;
	public static boolean TEST_ON_BORROW;
	public static boolean TEST_ON_RETURN;
	public static String MASTER_NAME;


	public static void main(String[] args) {
		ConfigUtils zkConfig = new ConfigUtils();
		//����zooKeeper
		try {
			zkConfig.connect("kafka001:2181");

			Thread.sleep(1000);
			zkConfig.initKafka();

			zkConfig.zk.setData("/storm/config/ENTID_MESSAGE_TEST", new String("1231:31231").getBytes(),-1);
			Thread.sleep(10000);
			System.out.println(ENTID_MESSAGE_TEST);
			Thread.sleep(10000);
			zkConfig.initKafka();

			zkConfig.zk.setData("/storm/config/ENTID_MESSAGE_TEST", new String("8989:31231").getBytes(),-1);
			Thread.sleep(1000);
			System.out.println(ENTID_MESSAGE_TEST);
			zkConfig.initKafka();


			System.out.println("================");

			System.out.println(ENTID_MESSAGE_TEST);

			System.out.println("================");

			Thread.sleep(10000);

			System.out.println(ENTID_MESSAGE_TEST);
			Thread.sleep(10000);
			System.out.println("================");

			zkConfig.initKafka();

			System.out.println(ENTID_MESSAGE_TEST);
			Thread.sleep(10000);
			System.out.println("================");

			System.out.println(ENTID_MESSAGE_TEST);
			Thread.sleep(10000);
			System.out.println("================");

			System.out.println(ENTID_MESSAGE_TEST);


			//			watcher.delayMillis(2000);
			//�ر�����
			zkConfig.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
