package JUC.redis;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class Test {

	/**
	 * @author JUC
	 * void
	 */
	//上次联系人 获取客户的最近联系人
	public List<MongoRecentAgentInfo> getMongoRecentCon(String entid,String collec,String url,String skill){
		  //开始时间
	    long s = System.currentTimeMillis();
		MongoDatabase db = MongoUtil.getDB(entid);
		MongoCollection<Document> collection = db.getCollection(collec);
		
		BasicDBObject queryCondition=new BasicDBObject();  
      	queryCondition.put("remote_url", url);
      	queryCondition.put("skill_name", skill);
     	BasicDBObject sortCondition=new BasicDBObject();  
      	sortCondition.put("score", 1);
        sortCondition.put("start_time", -1);
	FindIterable<Document> doc=	collection.find(queryCondition).sort(sortCondition);
      	
      	MongoCursor<Document> cursor= 	doc.iterator();
       List<MongoRecentAgentInfo> list=new ArrayList<MongoRecentAgentInfo>(); 	
      	while(cursor.hasNext()){
      	 	String ac=cursor.next().toJson();
          	JSONObject param = JSON.parseObject(ac);
         // 	System.out.println(param);
          	MongoRecentAgentInfo po=new MongoRecentAgentInfo();          	
            String agent_id = param.getString("agent_id");
            String remote_url=param.getString("remote_url");
            String start_time=param.getString("start_time");
            String skill_id=param.getString("skill_id");
            String skill_name=param.getString("skill_name");
            String agent_name=param.getString("agent_name");
          	po.setAgentId(agent_id);
            po.setRemoteUrl(remote_url);
            po.setStartTime(start_time);
            po.setSkillId(skill_id);
            po.setSkillName(skill_name);
            po.setAgentName(agent_name);
            po.setScore(param.getString("score"));  
            list.add(po);           	
      	}
      	if(list.size()==0){
      		System.out.println("该客户没有最近联系人");

      	}else{
      		System.out.println("该用户联系人："+list.size());
      	}		
		System.out.println("查询最近联系人时间:("
				+ (System.currentTimeMillis() - s) + ")ms");
		return list;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 /*Jedis jedis = ReadRedisFactory.getRandomJedis();
        String requestId = jedis.get("6654687641553863033");
        System.out.println(requestId);*/
		
		
		//连接
		MongoDatabase db = MongoUtil.getDB("8989");
		MongoCollection<Document> collection = db.getCollection("recent_contacts");
  /*    
      	BasicDBObject updateCondition=new BasicDBObject();  
      	
  //    	call_detail:{"session_id":"7375238138177782581","agent_id":"5656","agent_name":"liuqin","area_id":"","call_type":0,"skill_id":"1000001999","skill_name":"8989支持组","agent_dn":"BTL:45019764","local_url":"TEL:2491","remote_url":"TEL:18514787534","start_time":"1511785179","end_time":"1511785203","status":205,"duration":24,"start_type":1,"end_type":255,"record_addr":"NoRecordAddr","orig_skill_id":"","accept_skill_id":"","end_time_halfhour":"1511784900","campaign_id":"","skill_type":"-1","region_id":"010","ent_id":"8989","_id":"DCS-236:8989:5656:TEL:2491:TEL:18514787534:1511785179:1511785203:205:1","serial_num":3,"state_num":1,"is_handled":0,"is_release":1,"global_serial_num":5,"main_serviceid":"2","sub_serviceid":"","has_alerting_event":0,"local_url_wx":"","remote_url_wx":""}
        //查询一条件 客户号码  坐席id  技能组id
 		updateCondition.put("agent_id", "5656");
 		updateCondition.put("remote_url", "TEL:18514787235");
 		updateCondition.put("skill_id", "1000001929");
 		long count=collection.count(updateCondition);
 		System.out.println(count);

 		updateCondition.put("start_time",new BasicDBObject(QueryOperators.LTE,"1511785120"));

 		BasicDBObject allCondition=new BasicDBObject();  
 		BasicDBObject setObject=new BasicDBObject();  
 		setObject.put("start_time", "1511785120");
 		setObject.put("score", "0");
 		//技能组名称和坐席名称
 		setObject.put("agent_name", "坐席名字");
 		setObject.put("skill_name", "技能组名字");

 		allCondition.put("$set", setObject);
 		FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();

        if(count!=0){//
          options.upsert(false);
        }else{
          options.upsert(true);

        }  
 		long time=System.currentTimeMillis();
 		collection.findOneAndUpdate(updateCondition, allCondition, options);
   
 		*/
 		//根据技能组名称和电话号码 db.getCollection('recent_contacts').find({ "remote_url":"TEL:18514787534","skill_name":"故障报修" }).sort( { start_time: -1}).limit(1)
       //db.getCollection('recent_contacts')
 	   //.find({ "remote_url":"BTL:45020066","skill_name":"故障报修", "score": { "$lte" : 2 } })
 	   //.sort( { start_time: -1})
 	   //.limit(1)

      	BasicDBObject queryCondition=new BasicDBObject();  
      	queryCondition.put("remote_url", "BTL:45020066");
      	queryCondition.put("skill_name", "故障报修");
   //   	queryCondition.put("score", new BasicDBObject(QueryOperators.LTE,2));
      //  String param4="entId=8989&skillGroupName=R_DETAIL&remoteUrl=3123";
       // String param6="entId=8989&skillGroupName=故障报修&remoteUrl=BTL:45020066";


      	BasicDBObject sortCondition=new BasicDBObject();  
      	sortCondition.put("score", 1);
        sortCondition.put("start_time", -1);


      //    查询第一个	
      //	FindIterable<Document> doc=	collection.find(queryCondition).sort(sortCondition).limit(1);
      	FindIterable<Document> doc=	collection.find(queryCondition).sort(sortCondition);
      	
      	MongoCursor<Document> cursor= 	doc.iterator();
       List<MongoRecentAgentInfo> list=new ArrayList<MongoRecentAgentInfo>(); 	
      	while(cursor.hasNext()){
      	 	String ac=cursor.next().toJson();
          	JSONObject param = JSON.parseObject(ac);
          	System.out.println(param);
          	MongoRecentAgentInfo po=new MongoRecentAgentInfo();          	
            String agent_id = param.getString("agent_id");
            String remote_url=param.getString("remote_url");
            String start_time=param.getString("start_time");
            String skill_id=param.getString("skill_id");
            String skill_name=param.getString("skill_name");
            String agent_name=param.getString("agent_name");
          	po.setAgentId(agent_id);
            po.setRemoteUrl(remote_url);
            po.setStartTime(start_time);
            po.setSkillId(skill_id);
            po.setSkillName(skill_name);
            po.setAgentName(agent_name);
            po.setScore(param.getString("score"));  
            list.add(po);           	
      	}
      	if(list.size()==0){
      		System.out.println("该客户没有最近联系人");

      	}else{
      		System.out.println("该用户联系人："+list.size());
      		System.out.println("该用户联系人："+list.toString());
      	}
      	
      /*	Document docc= doc.first();
      	if(null==docc){
      		System.out.println("该客户没有最近联系人");
      	}else{
      		System.out.println("docc:"+docc);
          	String ac=docc.toJson();
          	System.out.println("ac:"+ac);
          	 
    	//	JSONObject param = JSON.parseObject(ac.substring(ac.indexOf(":")+1));
          	JSONObject param = JSON.parseObject(ac);
          	System.out.println(param);
            String agent_id = param.getString("agent_id");
            String remote_url=param.getString("remote_url");
            String start_time=param.getString("start_time");
            String skill_id=param.getString("skill_id");
            String skill_name=param.getString("skill_name");
            String agent_name=param.getString("agent_name");
            System.out.println("agent_id:"+agent_id+"remote_url: "+remote_url+"start_time:"+start_time+"skill_id:"+skill_id+"skill_name:"+skill_name+"agent_name:"+agent_name);
          	
      	}*/

	}

}
