package IO;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import wuhl.kafka.po.SessionDetailPo;
import wuhl.redis.MongoRecentAgentInfo;
import wuhl.redis.MongoUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Test1 {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {		
		int a;
//        showLog();
		// TODO Auto-generated method stub
		
		/*String id="1190379";
		if(!(id.startsWith("010"))){
			id="010"+id;
		}
		System.out.println(id);*/
		List<SessionDetailPo> list=	new Test1().getHuRunCon("1003", "session_detail");
		System.out.println(list.size());
		for(SessionDetailPo po:list){
			System.out.println(po);
		}

	}
	
	
	//华润session_detail接口
		public List<SessionDetailPo> getHuRunCon(String entid,String collec){
			  //开始时间
		    long s = System.currentTimeMillis();
			MongoDatabase db = MongoUtil.getDB(entid);
			MongoCollection<Document> collection = db.getCollection(collec);
			
			BasicDBObject queryCondition=new BasicDBObject();  
	     	BasicDBObject sortCondition=new BasicDBObject();  
	        sortCondition.put("start_time", -1);
		 FindIterable<Document> doc=collection.find(queryCondition).sort(sortCondition);
	      	
	      	MongoCursor<Document> cursor= 	doc.iterator();
	       List<SessionDetailPo> list=new ArrayList<SessionDetailPo>(); 	
	      	while(cursor.hasNext()){
	      	 	String ac=cursor.next().toJson();
	   
				SessionDetailPo sessionDetailPo = JSONObject.parseObject(ac,
						SessionDetailPo.class);
	            list.add(sessionDetailPo);           	
	      	}
	      	if(list.size()==0){
	      		System.out.println("该客户没有最近联系人");

	      	}else{
	      		System.out.println("session_detail个数："+list.size());
	      		System.out.println("session_detail ："+list.size());

	      	}		
			System.out.println("查询最近联系人时间:("
					+ (System.currentTimeMillis() - s) + ")ms");
			return list;
		}

	public static void showLog() {
		StringBuffer sb= new StringBuffer("");

		String total="2018-01-08 08:55:06,474 DEBUG - [pool-1-thread-26] [DataPushThread] [pool-1-thread-26]正在处理本次推送任务,参数为{";
		String total1="2018-01-08 10:55:03,273 INFO  - [pool-1-thread-5] [DataPushThread] [pool-1-thread-5]推送后接收的返回值，";
  
		
	
		String start=total.substring(20, 23);
		System.out.println(total.indexOf("DEBUG"));
		System.out.println(total.indexOf("["));
		System.out.println(total.indexOf("]"));
		String key=total.substring(total.indexOf("["),total.indexOf("]")+1);
		System.out.println("------------"+key);


		System.out.println(total1.indexOf("INFO"));
		System.out.println(total1.indexOf("["));
		System.out.println(total1.indexOf("]"));
		String key1=total1.substring(total1.indexOf("["),total.indexOf("]"));
		System.out.println("------------"+key1);




		System.out.println("开始ms:"+start);
	}

}
