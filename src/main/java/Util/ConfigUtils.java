package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
	private final static String mongoHostPath = "/storm/config/MONGO_HOST";
	private final static String mongoPoolSizePath = "/storm/config/MONGO_POOLSIZE";
	private final static String mongoBlockSizePath = "/storm/config/MONGO_BLOCKSIZE";
	private final static String topoWorkNumPath = "/storm/config/TOPO_WORK_NUM";
	private final static String topoSpoutNumPath = "/storm/config/TOPO_SPOUT_NUM";
	private final static String topoCompuBoltNumPath = "/storm/config/TOPO_COMPUBOLT_NUM";
	private final static String topoMongoBoltNumPath = "/storm/config/TOPO_MONGOBOLT_NUM";
	private final static String offsetZKPorkPath = "/zookeeper/OFFSET_ZKPORT";
	private final static String offsetZKRootPath = "/zookeeper/OFFSET_ZKROOT";
	private final static String offonDts = "/storm/config/OFF_ON_DTS";
	private final static String maxSpoutPending = "/storm/config/MAX_SPOUT_PENDING";
   // redis�Ľڵ�����
	private final static String redisHost = "/storm/config/REDIS_HOST"; 
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
		logger.info("��������" + event.getType());
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();
		}
	}

	// �ر�����
	public void close() throws InterruptedException {
		logger.info("�ر�zookeeper����!");
		zk.close();
	}

	public void loadPropertie() {
		InputStream istream = null;
		try {
			logger.info("����redis....");
			istream = ConfigUtils.class
					.getResourceAsStream("/config.properties");
			logger.info("����redis1....");
			properties = new Properties();
			logger.info("����redis2....");
			properties.load(istream);
			logger.info("����redis3....");
			//log.info("��ʼ��ʼ��redis...");

			// ��ʼ������
			initParam();
			// ��ʼ����Ⱥ
			initPool();
			// ���ڱ�������Ϣ
			initSentinelPub();
			
			logger.info("����redis4....");
		} catch (Exception e) {
			logger.error("��ȡ�����ļ�[config.properties]ʧ��.", e);

		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (Exception ignore) {
					logger.error("��ȡ�����ļ�[config.properties1]ʧ��.", ignore);

				}
			}
		}
	}

	// ���¼���������Ϣ
	public void loadProperties() {
		try {
			if (zk.exists(topoWorkNumPath, true) != null) {
				TOPO_WORK_NUM = Integer.parseInt(new String(zk.getData(
						topoWorkNumPath, true, null)));
			}
			if (zk.exists(topoSpoutNumPath, true) != null) {
				TOPO_SPOUT_NUM = Integer.parseInt(new String(zk.getData(
						topoSpoutNumPath, true, null)));
			}
			if (zk.exists(topoCompuBoltNumPath, true) != null) {
				TOPO_COMPUBOLT_NUM = Integer.parseInt(new String(zk.getData(
						topoCompuBoltNumPath, true, null)));
			}
			if (zk.exists(topoMongoBoltNumPath, true) != null) {
				TOPO_MONGOBOLT_NUM = Integer.parseInt(new String(zk.getData(
						topoMongoBoltNumPath, true, null)));
			}
			if (zk.exists(offsetZKPorkPath, true) != null) {
				OFFSET_ZKPORT = new String(zk.getData(offsetZKPorkPath, true,
						null));
			}
			if (zk.exists(offsetZKRootPath, true) != null) {
				OFFSET_ZKROOT = new String(zk.getData(offsetZKRootPath, true,
						null));
			}
			if (zk.exists(offonDts, true) != null) {
				OFF_ON_DTS = new String(zk.getData(offonDts, true, null));
			}
			if (zk.exists(maxSpoutPending, true) != null) {
				MAX_SPOUT_PENDING = Integer.parseInt(new String(zk.getData(
						maxSpoutPending, true, null)));
			}
			logger.info("�������ʼ�����");
			/*
			 * MongoUtil.init(); logger.info("mongo��ʼ�����");
			 */
		} catch (Exception e) {
			logger.warn("�������ʼ���쳣�������˳���", e);
			System.exit(0);
		}

	}

	/**
	 * ��ʼ��mongodb
	 */
	public void intMongo() {
		try {
			MONGO_HOST = new String(zk.getData(mongoHostPath, true, null));
			logger.info("��zookeeperȡ��mongo������ֵ��" + MONGO_HOST);
			if (zk.exists(mongoPoolSizePath, true) != null) {
				MONGO_POOLSIZE = Integer.parseInt(new String(zk.getData(
						mongoPoolSizePath, true, null)));
			}
			if (zk.exists(mongoBlockSizePath, true) != null) {
				MONGO_BLOCKSIZE = Integer.parseInt(new String(zk.getData(
						mongoBlockSizePath, true, null)));
			}
		} catch (Exception e) {
			logger.warn("��ʼ��mongo��Ϣ�쳣�������˳���", e);
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

	public String getCallDetailZk(String topoName) {
		try {
			return new String(zk.getData("/stormOffset/" + topoName
					+ "/partition_0", true, null));
		} catch (KeeperException e) {
			
			e.printStackTrace();
			return "";
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			return "";
		}
	}

	private static ConfigUtils config;
	private static List<JedisPool> jedisPools;
	private static List<Jedis> sentinelList;
	private static int currentSentinels;
	//private static Log log = LogFactory.getLog(ReadRedisFactory.class);

	// private static Properties properties = null;
	public static String REDIS_HOSTS;
	public static String REDIS_PASSWORDS;
	public static int MAX_ACTIVE;
	public static int MAX_IDLE;
	public static long MAX_WAIT;
	public static boolean TEST_ON_BORROW;
	public static boolean TEST_ON_RETURN;
	public static String MASTER_NAME;

	

	/**
	 * ��ʼ������
	 * 
	 * void �ų��� 2016��7��7��
	 */
	private void initParam() {
		try {
			logger.info("redis������ʼ������...");
			REDIS_HOSTS = new String(zk.getData(redisHost, true, null));
			logger.info("��zookeeperȡ��redis������ֵ��" + REDIS_HOSTS);		
			//REDIS_HOSTS = ConfigUtils.getString(ConfigUtils.REDIS_HOSTS1);
			//logger.info("REDIS_HOSTS" + REDIS_HOSTS);
			REDIS_PASSWORDS = ConfigUtils.getString(
					ConfigUtils.REDIS_PASSWORDS1, "");
			
			if (REDIS_PASSWORDS != null && REDIS_PASSWORDS.isEmpty()) {
				REDIS_PASSWORDS = null;
			}
			MAX_ACTIVE = ConfigUtils.getInt(ConfigUtils.REDIS_MAX_ACTIVE1, 400);
			MAX_IDLE = ConfigUtils.getInt(ConfigUtils.REDIS_MAX_IDLE1, 100);
			MAX_WAIT = ConfigUtils.getInt(ConfigUtils.REDIS_MAX_WAIT1, 1000);
			TEST_ON_BORROW = Boolean.valueOf(ConfigUtils.getString(
					ConfigUtils.REDIS_TEST_ON_BORROW1, "true"));
			TEST_ON_RETURN = Boolean.valueOf(ConfigUtils.getString(
					ConfigUtils.REDIS_TEST_ON_RETURN1, "true"));
			MASTER_NAME = ConfigUtils.getString(ConfigUtils.MASTER_NAME1);
			logger.info("redis ������ʼ�����...");
		} catch (Exception e) {
			logger.error("redis ������ʼ��ʧ��" + e.getMessage(), e);
		}
	}

	/**
	 * ���ڱ��������ӽڵ��ڵ������¼�
	 * 
	 * void �ų��� 2016��7��12��
	 */
	private void initSentinelPub() {
		logger.info("initSentinelPub().....");
	//	final SubscribeJedis subscribeJedis = new SubscribeJedis();
		for (final Jedis jedis : sentinelList) {
			new Thread() {
				public void run() {
		//			jedis.subscribe(subscribeJedis, "+slave", "+sdown",
		//					"-sdown");
				}
			}.start();
		}
	}

	/**
	 * ��ʼ����Ⱥ
	 * 
	 * void �ų��� 2016��7��7��
	 */
	private void initPool() {
		logger.info("initPool.....");
		config = new ConfigUtils();
		sentinelList = new ArrayList<Jedis>();
		jedisPools = new ArrayList<JedisPool>();
		currentSentinels = 0;

		Jedis nowJedis = null;
		for (String sentinel : REDIS_HOSTS.split(";")) {
			String[] host = sentinel.split(":");
			Jedis jedis = new Jedis(host[0], Integer.parseInt(host[1]));
			if (jedis.sentinelSlaves(MASTER_NAME).size() > currentSentinels) {
				currentSentinels = jedis.sentinelSlaves(MASTER_NAME).size();
				nowJedis = jedis;
			}
			sentinelList.add(jedis);
		}
		// ��ȡ��ǰ����salves
		List<Map<String, String>> slaves = nowJedis.sentinelMasters();
		String[] hosts = new String[slaves.size()];
		for (int i = 0; i < slaves.size(); i++) {
			hosts[i] = slaves.get(i).get("ip") + ":"
					+ slaves.get(i).get("port");
		}
		// ����redis��Ⱥ
		for (int i = 0; i < hosts.length; i++) {
			JedisPool jedisPool = null;
			String[] temp = hosts[i].split(":");
			if (REDIS_PASSWORDS == null) {
				// ��֤�Ƿ�������redis
				if (isUsable(temp[0], temp[1])) {
					jedisPool = new JedisPool(config, temp[0],
							Integer.parseInt(temp[1]), 10000, null);
					jedisPools.add(jedisPool);
				}
			} else {
				if (isUsable(temp[0], temp[1])) {
					String[] pwds = REDIS_PASSWORDS.split(",");
					jedisPool = new JedisPool(config, temp[0],
							Integer.parseInt(temp[1]), 10000, pwds[i]);
					jedisPools.add(jedisPool);
				}
			}
		}
		logger.info("redis��Ⱥpools��ʼ�����...��" + jedisPools.size() + "���ɶ���");
	}

	public synchronized void reloadPool() {
		initPool();
	}

	public static Jedis  getRandomJedis() {
		int random = new Random().nextInt(jedisPools.size());
		Jedis jedis = jedisPools.get(random).getResource();
		jedis.select(ConfigUtils.getInt(ConfigUtils.DB_NUMBER1, 8));
		return jedis;
	}

	private boolean isUsable(String host, String port) {
		try {
			Jedis jedis = new Jedis(host, Integer.parseInt(port));
			jedis.exists("this_is_not_a_useful_key");
			jedis.close();
		} catch (Exception e) {
			logger.info(host + ":" + port + " redis�������޷�����");
			return false;
		}
		logger.info(host + ":" + port + " redis���������ӳɹ�");
		return true;
	}

}
