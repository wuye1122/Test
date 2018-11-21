package IO.SerializableTest;

import clojure.asm.commons.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import wuhl.redis.MongoUtil;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SessionDetailGroup implements Serializable {

    //mongodb 分组查询
   //db.getCollection('session_detail').aggregate([{ "$match" : { "session_id" : "7447691421496378534"}}, { "$group" : { "_id" : "$session_id" , "entId" : { "$max" : "$entId"} , "session_id" : { "$max" : "$session_id"} , "agent_id" : { "$max" : "$agent_id"} , "agent_name" : { "$max" : "$agent_name"} , "agent_dn" : { "$max" : "$agent_dn"} , "skill_id" : { "$max" : "$skill_id"} , "skill_name" : { "$max" : "$skill_name"} , "start_time" : { "$max" : "$start_time"} , "end_time" : { "$max" : "$end_time"} , "call_type" : { "$max" : "$call_type"} , "local_url" : { "$max" : "$local_url"} , "remote_url" : { "$max" : "$remote_url"} , "local_url_wx" : { "$max" : "$local_url_wx"} , "remote_url_wx" : { "$max" : "$remote_url_wx"} , "ivr_duration" : { "$max" : "$ivr_duration"} , "alert_duration" : { "$max" : "$alert_duration"} , "alert_duration_agent" : { "$max" : "$alert_duration_agent"} , "queue_duration" : { "$max" : "$queue_duration"} , "talk_duration" : { "$max" : "$talk_duration"} , "acw_duration" : { "$max" : "$acw_duration"} , "end_type" : { "$max" : "$end_type"} , "campaign_id" : { "$max" : "$campaign_id"} , "region_id" : { "$max" : "$region_id"} , "orig_skill_id" : { "$max" : "$orig_skill_id"} , "accept_skill_id" : { "$max" : "$accept_skill_id"} , "stateNum" : { "$max" : "$stateNum"} , "serial_num" : { "$max" : "$serial_num"} , "global_serial_num" : { "$max" : "$global_serial_num"} , "user_data" : { "$max" : "$user_data"} , "end_reason" : { "$max" : "$end_reason"} , "dest_ani" : { "$max" : "$dest_ani"} , "turn" : { "$max" : "$turn"} , "campaign_name" : { "$max" : "$campaign_name"} , "exdata1" : { "$max" : "$exdata1"} , "exdata2" : { "$max" : "$exdata2"} , "exdata3" : { "$max" : "$exdata3"} , "exdata4" : { "$max" : "$exdata4"} , "exdata5" : { "$max" : "$exdata5"} , "exdata6" : { "$max" : "$exdata6"} , "exdata7" : { "$max" : "$exdata7"} , "exdata8" : { "$max" : "$exdata8"} , "exdata9" : { "$max" : "$exdata9"} , "exdata10" : { "$max" : "$exdata10"} , "ivr_time" : { "$max" : "$ivr_time"} , "transfer_agent_time" : { "$max" : "$transfer_agent_time"} , "beginTime" : { "$max" : "$beginTime"} , "dial_times" : { "$max" : "$dial_times"} , "alter_time" : { "$max" : "$alter_time"} , "transfer_queue_time" : { "$max" : "$transfer_queue_time"} , "transfer_altering_time" : { "$max" : "$transfer_altering_time"} , "session_detail" : { "$max" : "$session_detail"}}}]
   //,{allowDiskUse:true})
   //group+match 分组查询（ selet a ,max(n) from Table group by a ）
    public void getMongoGroupBySession_id(String entId,String file){
        //开始时间
        long s = System.currentTimeMillis();
        MongoDatabase db = MongoUtil.getDB(entId);
        MongoCollection<Document> collection = db.getCollection("session_detail");
        try {

            //如果出现不一样的string
            Class sessionDetailPo = Class.forName("IO.SerializableTest.SessionDetail");
            Field[] fields = sessionDetailPo.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
            BasicDBObject groupFields1 = new BasicDBObject();
            for(Field f:fields){
                if("_id".equals(f.getName())){
                    groupFields1.append(f.getName(), new BasicDBObject("session_id","$session_id"));
                }else{
                    groupFields1.append(f.getName(), new BasicDBObject("$max", "$"+f.getName()));

                }
            }
            BasicDBObject matchFields1 = new BasicDBObject( "session_id","7447691421496378534");
            //$match
            BasicDBObject match = new BasicDBObject("$match", matchFields1);
            //$group
            BasicDBObject group = new BasicDBObject("$group", groupFields1);

            List<BasicDBObject> condList= new ArrayList<BasicDBObject>();
            condList.add(match);
            condList.add(group);

            System.out.println(condList.toString());
            //查询出当天企业的 成功数
            MongoCursor<Document> result= collection.aggregate(condList).allowDiskUse(true).iterator();

            while(result.hasNext()){
                Document ac = (Document) result.next();
                System.out.println(ac);
                String json = ac.toJson();
                System.out.println(json);
                SessionDetail paramPo = JSON.parseObject(json,SessionDetail.class);
                System.out.println(paramPo.toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }finally {
            try {
                System.out.println("正在执行所需要时间:【"+String.valueOf(System.currentTimeMillis()-s)+"】");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        //mongodb分组查询
        new SessionDetailGroup().getMongoGroupBySession_id("0101490462","");
    }
}
