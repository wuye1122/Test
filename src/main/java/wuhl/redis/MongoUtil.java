package wuhl.redis;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.UpdateResult;
public class MongoUtil {
	
		
	    private static MongoClient mongoClient = null;
	    
	    //storm  
	   // private static final String[] readableMongoHosts = "10.130.29.58:30000".split(";");
	    //dps
	    private static final String[] readableMongoHosts = "10.130.29.120:30000".split(";");

	    static {
	    	//Mongohost地址
	    	List<ServerAddress> readSeeds = new ArrayList<ServerAddress>();
	    	for(String host:readableMongoHosts) {
	    		String[] hosts = host.split(":");
	    		if(hosts.length == 2) {
	    			readSeeds.add(new ServerAddress(hosts[0],Integer.valueOf(hosts[1])));
	    		}
	    	}
	    	//mongo选项
	    	MongoClientOptions.Builder options = new MongoClientOptions.Builder();
	    	options.connectionsPerHost(300);// 连接池设置为150个连接,默认为100
	        options.connectTimeout(15000);// 连接超时，推荐>3000毫秒
	        options.maxWaitTime(5000); //
	        options.socketTimeout(0);// 套接字超时时间，0无限制
	        options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
	        options.writeConcern(WriteConcern.ACKNOWLEDGED);//
	        
	        mongoClient = new MongoClient(readSeeds,options.build());
	    }
	    
	    /* (非 Javadoc) 
	    * <p>Title: getDB</p> 
	    * <p>Description: 根据dbName得到bean</p> 
	    * @param dbName
	    * @return  
	    * @author: zc
	    */
	    public static MongoDatabase getDB(String dbName) {
	        if (dbName != null && !"".equals(dbName)) {
	            MongoDatabase database = mongoClient.getDatabase(dbName);
	            return database;
	        }
	        return null;
	    }
	    
	    /* (非 Javadoc) 
	    * <p>Title: getCollection</p> 
	    * <p>Description: 获取指定db下的collection对象</p> 
	    * @param dbName
	    * @param collName
	    * @return  
	    * @author: zc
	    */
	    private MongoCollection<Document> getCollection(String dbName, String collName) {
	        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
	        return collection;
	    }
	    
	    /* (非 Javadoc) 
	    * <p>Title: getAllCollections</p> 
	    * <p>Description: 根据dbName查询其下所有collection</p> 
	    * @param dbName
	    * @return  
	    * @author: zc
	    */
	    public List<String> getAllCollections(String dbName) {
	        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
	        List<String> _list = new ArrayList<String>();
	        for (String s : colls) {
	            _list.add(s);
	        }
	        return _list;
	    }
	    
	    /* (非 Javadoc) 
	    * <p>Title: find</p> 
	    * <p>Description: 无分页条件查询</p> 
	    * @param coll
	    * @param filter
	    * @return  
	    * @author: zc
	    */
	    public MongoCursor<Document> find(String dbName,String collName, Bson filter, Bson orderBy) {
	    	if(orderBy == null) {
	    		orderBy = new BasicDBObject("_id",1);
	    	}
	        return getCollection(dbName,collName).find(filter).sort(orderBy).iterator();
	    }

	    /* (非 Javadoc) 
	    * <p>Title: findByPage</p> 
	    * <p>Description: 分页条件查询</p> 
	    * @param coll
	    * @param filter
	    * @param pageNo
	    * @param pageSize
	    * @return  
	    * @author: zc
	    */
	    public MongoCursor<Document> findByPage(String dbName,String collName, Bson filter, Bson orderBy, int pageNo, int pageSize) {
	    	if(orderBy == null) {
	    		orderBy = new BasicDBObject("_id",1);
	    	}
	        return getCollection(dbName,collName).find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
	    }
	    
	    /* (非 Javadoc) 
	    * <p>Title: countByPage</p> 
	    * <p>Description: 查询符合条件的总数</p> 
	    * @param coll
	    * @param filter
	    * @return  
	    * @author: zc
	    */
	    public long count(String dbName,String collName, Bson filter) {
	    	return getCollection(dbName,collName).count(filter);
	    }
	    
	    /* (非 Javadoc) 
	    * <p>Title: updateOne</p> 
	    * <p>Description: 更新一条已存在的数据</p> 
	    * @param dbName
	    * @param collName
	    * @param filter
	    * @param update
	    * @return  
	    * @author: zc
	    */
	    public long updateOne(String dbName,String collName, Bson filter,Bson update) {
	    	UpdateResult result = getCollection(dbName,collName).updateOne(filter, update);
	    	return result.getModifiedCount();
	    }
	   
	    /* (非 Javadoc) 
	    * <p>Title: insertOne</p> 
	    * <p>Description: 插入一条数据</p> 
	    * @param dbName
	    * @param collName
	    * @param map  
	    * @author: zc
	    */
	    public void insertOne(String dbName, String collName, Map<String, Object> map) {
	    	Document document = new Document(map);
	    	getCollection(dbName, collName).insertOne(document);
	    	
	    }
	    /* (非 Javadoc) 
	     * <p>Title: close</p> 
	     * <p>Description: 特殊解决方法，本质上无须调用</p>   
	     * @author: zc
	     */
	    @Deprecated
	    public void close() {
	        if (mongoClient != null) {
	        	mongoClient.close();
	        	mongoClient = null;
	        }
	    }
}
