package IO.SerializableTest;

import IO.writeFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import wuhl.redis.MongoUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MongodbGetSessionId {


    public void getMongoRecentCon(String entId,String file){
        //��ʼʱ��
        long s = System.currentTimeMillis();
        MongoDatabase db = MongoUtil.getDB(entId);
        MongoCollection<Document> collection = db.getCollection("session_detail");
        BufferedWriter out = null;
        try {
            BasicDBObject queryBson = new BasicDBObject();
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));

    /*        '$group': {
                '_id': {
                    'session_id': '$session_id'
                },
                'uniqueIds': {
                    '$addToSet': '$_id'
                },
                'count': {
                    '$sum': 1
                }
            }
        }*/

            BasicDBObject groupFields1 = new BasicDBObject( "_id",new BasicDBObject("session_id","$session_id")).append("count", new BasicDBObject("$sum",1));

            //  {'$match':{'count':{'$gt':1} }}

            BasicDBObject matchFields1 = new BasicDBObject( "count",new BasicDBObject("$gt",1) );
            BasicDBObject group = new BasicDBObject("$group", groupFields1);
            BasicDBObject match = new BasicDBObject("$match", matchFields1);
            //group



            List<BasicDBObject> condList= new ArrayList<BasicDBObject>();
            condList.add(group);
            condList.add(match);

            System.out.println(condList.toString());
            //��ѯ��������ҵ�� �ɹ���
            MongoCursor<Document> result= collection.aggregate(condList).allowDiskUse(true).iterator();



            while(result.hasNext()){
                Document ac = (Document) result.next();
                System.out.println(ac);
                String json = ac.toJson();
                System.out.println(json);
                JSONObject  session_id =   JSONObject.parseObject(json);
                System.out.println(session_id.getString("_id"));
                out.write(session_id.getString("_id")+"\r\n");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }finally {
            try {
                System.out.println("����ִ������Ҫʱ��:��"+String.valueOf(System.currentTimeMillis()-s)+"��");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void showXiaoYing(String entArry){

        String [] num = entArry.split("\\n");
        MongodbGetSessionId id = new MongodbGetSessionId();

        Set<String> set = new HashSet<>();
        for(int i=0;i<num.length;i++){
            set.add(num[i]);
        }
        Object[] obj =set.toArray();
        System.out.println(set.size());
        for(int i=0;i<obj.length;i++){
            String file = obj[i]+"";
            String str = "E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201811\\СӮ���\\4.5sessionid��ͬ\\"+file+".txt";
            System.out.println("��ʼͳ�Ƶ���ҵ:��"+file+"���ļ����λ��:��"+str+"��");
            id.getMongoRecentCon(file,str);
        }



    }

    public void getMongoSession(String entId,String file,String target){
        //��ʼʱ��
        long s = System.currentTimeMillis();
        MongoDatabase db = MongoUtil.getDB(entId);
        MongoCollection<Document> collection = db.getCollection("session_detail");
        FileReader m= null;
        BufferedReader reader = null;
        BufferedWriter out = null;

        Map<String,String> map = new HashMap<String,String>();

        try {
            BasicDBObject queryBson = new BasicDBObject();
            m = new FileReader(file);
            reader=new BufferedReader(m);

            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            while(true) {
                String nextline=reader.readLine();
                if(nextline==null){

                    break;
                }
                String old = "";
                String  session_detail= "";

                String session = "";
                System.out.println(nextline);
                JSONObject obj = JSONObject.parseObject(nextline);
                BasicDBObject groupFields1 = new BasicDBObject(obj);
                //��ѯ��������ҵ�� �ɹ���
                MongoCursor<Document> result= collection.find(groupFields1).iterator();

                while(result.hasNext()){
                    Document ac = (Document) result.next();
                  //  System.out.println(ac);
                    String json = ac.toJson();
                    JSONObject  session_id =   JSONObject.parseObject(json);
                    session_detail = session_id.toJSONString();
                    System.out.println(session_detail);
                    SessionDetail paramPo = JSON.parseObject(session_id.toJSONString(),SessionDetail.class);
                   /* System.out.println(paramPo.toString());
                    System.out.println(paramPo.getKey());*/
                    old = old+paramPo.getKey()+"3";
                    session = session_id.getString("session_id");
                    //д���ļ�
                  //  out.write(session_id+"\r\n");
                }

                System.out.println(old.length());
                String[]  oldArry =old.split("3");
                String start = oldArry[0]+"3"+oldArry[1];
                String end = oldArry[1]+"3"+oldArry[0];
                System.out.println("session_id:"+session);
                if(map.keySet().contains(start)||map.keySet().contains(end)){
                    System.out.println("�������Ѿ��ظ�:"+start);
                    System.out.println("�������Ѿ��ظ�:"+session);
                }else{
                    map.put(start,session);
                }

            }



        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e);
        }finally {
            try {
                System.out.println("����ִ������Ҫʱ��:��"+String.valueOf(System.currentTimeMillis()-s)+"��");
                System.out.println("�ظ����ͣ�map"+map.size());
                System.out.println("�ظ����ͣ�map"+map.toString());
                for(String set: map.keySet()){
                    SessionDetail po = new SessionDetail();
                    String [] arr= set.split("3");
                    System.out.println("session_id:"+map.get(set));
                    po.show(arr[0],arr[1]);
                }
                if(null!=reader){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void test(String a) {
        FileReader m= null;
        BufferedReader reader = null;
        try {
            m = new FileReader(a);
            reader=new BufferedReader(m);
            while(true) {
                String nextline=reader.readLine();
                if(nextline==null){
                    break;
                }
                System.out.println("next:"+nextline);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!=reader){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String xiaoying="0101490464\n" +
                "0101490402\n" +
                "0101190416\n" +
                "0101290287\n" +
                "0101290511\n" +
                "0101290511\n" +
                "0101190415\n" +
                "0101490467\n" +
                "0101490466\n" +
                "0101190371\n" +
                "0101490403\n" +
                "0101490463\n" +
                "0101490410\n" +
                "0101190415\n" +
                "0101490401\n" +
                "0101490313\n" +
                "0101290221\n" +
                "0101490465\n" +
                "0101290280\n" +
                "0101490461\n" +
                "0101490401\n" +
                "0101190415\n" +
                "0101490410\n" +
                "0101290424";
        //  new MongodbGetSessionId().showXiaoYing(xiaoying);

        String file = "0101490464";
        String str = "E:\\wuhl\\����\\���������ļ�\\wuhl\\800��ҵ\\201811\\СӮ���\\4.5sessionid��ͬ\\"+file+".txt";
        new MongodbGetSessionId().getMongoSession("0101490464",str,str);
    //     MongodbGetSessionId.test(str);
    }
}
