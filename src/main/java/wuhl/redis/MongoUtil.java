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
	    	//Mongohost��ַ
	    	List<ServerAddress> readSeeds = new ArrayList<ServerAddress>();
	    	for(String host:readableMongoHosts) {
	    		String[] hosts = host.split(":");
	    		if(hosts.length == 2) {
	    			readSeeds.add(new ServerAddress(hosts[0],Integer.valueOf(hosts[1])));
	    		}
	    	}
	    	//mongoѡ��
	    	MongoClientOptions.Builder options = new MongoClientOptions.Builder();
	    	options.connectionsPerHost(300);// ���ӳ�����Ϊ150������,Ĭ��Ϊ100
	        options.connectTimeout(15000);// ���ӳ�ʱ���Ƽ�>3000����
	        options.maxWaitTime(5000); //
	        options.socketTimeout(0);// �׽��ֳ�ʱʱ�䣬0������
	        options.threadsAllowedToBlockForConnectionMultiplier(5000);// �̶߳���������������߳������˶��оͻ��׳���Out of semaphores to get db������
	        options.writeConcern(WriteConcern.ACKNOWLEDGED);//
	        
	        mongoClient = new MongoClient(readSeeds,options.build());
	    }
	    
	    /* (�� Javadoc) 
	    * <p>Title: getDB</p> 
	    * <p>Description: ����dbName�õ�bean</p> 
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
	    
	    /* (�� Javadoc) 
	    * <p>Title: getCollection</p> 
	    * <p>Description: ��ȡָ��db�µ�collection����</p> 
	    * @param dbName
	    * @param collName
	    * @return  
	    * @author: zc
	    */
	    private MongoCollection<Document> getCollection(String dbName, String collName) {
	        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
	        return collection;
	    }
	    
	    /* (�� Javadoc) 
	    * <p>Title: getAllCollections</p> 
	    * <p>Description: ����dbName��ѯ��������collection</p> 
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
	    
	    /* (�� Javadoc) 
	    * <p>Title: find</p> 
	    * <p>Description: �޷�ҳ������ѯ</p> 
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

	    /* (�� Javadoc) 
	    * <p>Title: findByPage</p> 
	    * <p>Description: ��ҳ������ѯ</p> 
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
	    
	    /* (�� Javadoc) 
	    * <p>Title: countByPage</p> 
	    * <p>Description: ��ѯ��������������</p> 
	    * @param coll
	    * @param filter
	    * @return  
	    * @author: zc
	    */
	    public long count(String dbName,String collName, Bson filter) {
	    	return getCollection(dbName,collName).count(filter);
	    }
	    
	    /* (�� Javadoc) 
	    * <p>Title: updateOne</p> 
	    * <p>Description: ����һ���Ѵ��ڵ�����</p> 
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
	   
	    /* (�� Javadoc) 
	    * <p>Title: insertOne</p> 
	    * <p>Description: ����һ������</p> 
	    * @param dbName
	    * @param collName
	    * @param map  
	    * @author: zc
	    */
	    public void insertOne(String dbName, String collName, Map<String, Object> map) {
	    	Document document = new Document(map);
	    	getCollection(dbName, collName).insertOne(document);
	    	
	    }
	    /* (�� Javadoc) 
	     * <p>Title: close</p> 
	     * <p>Description: �������������������������</p>   
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
