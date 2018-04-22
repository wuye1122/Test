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
 * <dd>Description:读取配置项数据类</dd>
 * <dd>CreateDate: 2016-3-16</dd>
 * </dl>
 * 
 * @author
 */
public class ConfigUtils extends GenericObjectPoolConfig implements Watcher {

	private static Log logger = LogFactory.getLog(ConfigUtils.class);
	private static Properties prop = new Properties();
	// mongo地址
	public static String MONGO_HOST;
	// mongo连接数量
	public static int MONGO_POOLSIZE = 100;
	// mongo等待队列长度
	public static int MONGO_BLOCKSIZE = 100;
	// 拓扑worker个数
	public static int TOPO_WORK_NUM = 2;
	// 拓扑spout线程数
	public static int TOPO_SPOUT_NUM = 2;
	// 拓扑中计算节点的线程数
	public static int TOPO_COMPUBOLT_NUM = 3;
	// 拓扑中入库节点的线程数
	public static int TOPO_MONGOBOLT_NUM = 3;
	// 拓扑中计算节点的线程数
	public static int RAGSE_TOPO_COMPUBOLT_NUM = 1;
	// 拓扑中入库节点的线程数
	public static int RAGSE_TOPO_MONGOBOLT_NUM = 5;

	public static int MAX_SPOUT_PENDING = 500;
	// 将offset汇报到哪个zk集群,相应配置
	public static String OFFSET_ZKPORT = "2181";
	// 汇报offset信息的root路径
	public static String OFFSET_ZKROOT = "/stormOffset";
	// 是否开启DTS,1有dts，0没有dts
	public static String OFF_ON_DTS = "0";
	// 配置项zookeeper节点路径
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
   // redis的节点配置
	private final static String redisHost = "/storm/config/REDIS_HOST"; 
	// mongo地址
//	/public static String REDIS_HOSTS1;
	
	
	
	private static final int SESSION_TIME = 2000;
	protected ZooKeeper zk;
	protected CountDownLatch countDownLatch = new CountDownLatch(1);

	private static Properties properties = null;
	/** redis相关配置 **/
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

	// 初始化连接
	public void connect(String hosts) throws IOException, InterruptedException {
		logger.info("连接zookeeper....");
		zk = new ZooKeeper(hosts, SESSION_TIME, this);
		countDownLatch.await();
	}

	// 配置项每次更改的时候被调用
	public void process(WatchedEvent event) {
		logger.info("更改配置" + event.getType());
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();
		}
	}

	// 关闭连接
	public void close() throws InterruptedException {
		logger.info("关闭zookeeper连接!");
		zk.close();
	}

	public void loadPropertie() {
		InputStream istream = null;
		try {
			logger.info("连接redis....");
			istream = ConfigUtils.class
					.getResourceAsStream("/config.properties");
			logger.info("连接redis1....");
			properties = new Properties();
			logger.info("连接redis2....");
			properties.load(istream);
			logger.info("连接redis3....");
			//log.info("开始初始化redis...");

			// 初始化参数
			initParam();
			// 初始化集群
			initPool();
			// 从哨兵订阅消息
			initSentinelPub();
			
			logger.info("连接redis4....");
		} catch (Exception e) {
			logger.error("读取属性文件[config.properties]失败.", e);

		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (Exception ignore) {
					logger.error("读取属性文件[config.properties1]失败.", ignore);

				}
			}
		}
	}

	// 重新加载配置信息
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
			logger.info("配置项初始化完成");
			/*
			 * MongoUtil.init(); logger.info("mongo初始化完成");
			 */
		} catch (Exception e) {
			logger.warn("配置项初始化异常，程序退出！", e);
			System.exit(0);
		}

	}

	/**
	 * 初始化mongodb
	 */
	public void intMongo() {
		try {
			MONGO_HOST = new String(zk.getData(mongoHostPath, true, null));
			logger.info("从zookeeper取的mongo配置项值：" + MONGO_HOST);
			if (zk.exists(mongoPoolSizePath, true) != null) {
				MONGO_POOLSIZE = Integer.parseInt(new String(zk.getData(
						mongoPoolSizePath, true, null)));
			}
			if (zk.exists(mongoBlockSizePath, true) != null) {
				MONGO_BLOCKSIZE = Integer.parseInt(new String(zk.getData(
						mongoBlockSizePath, true, null)));
			}
		} catch (Exception e) {
			logger.warn("初始化mongo信息异常，程序退出！", e);
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
	 * 初始化参数
	 * 
	 * void 张辰熇 2016年7月7日
	 */
	private void initParam() {
		try {
			logger.info("redis参数初始化配置...");
			REDIS_HOSTS = new String(zk.getData(redisHost, true, null));
			logger.info("从zookeeper取的redis配置项值：" + REDIS_HOSTS);		
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
			logger.info("redis 参数初始化完毕...");
		} catch (Exception e) {
			logger.error("redis 参数初始化失败" + e.getMessage(), e);
		}
	}

	/**
	 * 从哨兵订阅增加节点或节点下线事件
	 * 
	 * void 张辰熇 2016年7月12日
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
	 * 初始化集群
	 * 
	 * void 张辰熇 2016年7月7日
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
		// 获取当前可用salves
		List<Map<String, String>> slaves = nowJedis.sentinelMasters();
		String[] hosts = new String[slaves.size()];
		for (int i = 0; i < slaves.size(); i++) {
			hosts[i] = slaves.get(i).get("ip") + ":"
					+ slaves.get(i).get("port");
		}
		// 遍历redis集群
		for (int i = 0; i < hosts.length; i++) {
			JedisPool jedisPool = null;
			String[] temp = hosts[i].split(":");
			if (REDIS_PASSWORDS == null) {
				// 验证是否能连上redis
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
		logger.info("redis集群pools初始化完毕...共" + jedisPools.size() + "个可读池");
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
			logger.info(host + ":" + port + " redis服务器无法连接");
			return false;
		}
		logger.info(host + ":" + port + " redis服务器连接成功");
		return true;
	}

}
