package Util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
	private static Logger logger = Logger.getLogger(MongoUtil.class);
	private static MongoClient mongo = null;

	public static MongoDatabase getMongoClient(String databaseName) {
		logger.info("现在使用的mongodb地址:"+mongo.getAddress().getHost());
		return mongo.getDatabase(databaseName);
	}

	static {
		logger.info("进入mongo的init()方法");
		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		build.connectionsPerHost(ConfigUtils.MONGO_POOLSIZE);// 与目标数据库能够建立的最大connection数量
		build.threadsAllowedToBlockForConnectionMultiplier(ConfigUtils.MONGO_BLOCKSIZE);// 如果当前�?��的connection都在使用中，每个connection上线程排队等待数
		build.maxWaitTime(1000 * 60 * 2);// �?��等待时间
		build.connectTimeout(1000 * 60 * 1);// 与数据库建立连接的timeout
		MongoClientOptions mongoOptions = build.build();
		
		//应配置为ip1:port1;ip2:port2;ip3:port3这种格式
		String hosts = ConfigUtils.MONGO_HOST;
		List<ServerAddress> servers = new ArrayList<ServerAddress>();
		for (String host : hosts.split(";")) {
			servers.add(new ServerAddress(host));
		}
		// 数据库连接实�?
		mongo = new MongoClient(servers, mongoOptions);
	}
}
