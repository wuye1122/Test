package JUC.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Set;

public class ShangHai {

    public static void main(String[] args) {
        ZsetRedisHelper zsetRedisHelper = new ZsetRedisHelper();
        HashMap<String,Long> map= zsetRedisHelper.getAgentStatus("20190221","1000010132");
        System.out.println("map:"+map.toString());


        // TODO Auto-generated method stub
        //JedisPool jedisPool = new JedisPool("10.130.29.226", 6381);
        JedisPool jedisPool = new JedisPool("10.130.29.226", 6379);
        Jedis jedis = null;
        try {
            long start = System.currentTimeMillis();

            jedis = jedisPool.getResource();
            //jedis.select(4);

		    /*
		     * 插入list
		     * jedis.lpush("pushTime1:1", "Redis");
		      jedis.lpush("pushTime1:1", "jedis");*/
              jedis.select(6);

              String key = "session_id";
              String value = "{\"clresutid\":\"561E1FDB-中文乱码么-4256-9AF6-74CE31D95136\",\"sessionid\":\"7375893226051338246\",\"entid\":\"1003\",\"campaiginid\":\"U20190222184134CC76D\",\"campaigntype\":1,\"custid\":\"100m\",\"custphone\":\"sip:1000000929\",\"callresult\":4,\"callresultdesc\":\"InvalidNUM\",\"begintime\":\"2019-02-22 18:41:43\",\"dialtimes\":238,\"ivrtimes\":0,\"altertime\":238,\"transferagenttime\":0,\"transferqueuetime\":0,\"transferalteringtime\":0,\"dialnumber\":1,\"surveyresult\":\"\",\"isfinish\":1,\"agentid\":\"\",\"exdata1\":\"\",\"exdata2\":\"\",\"exdata3\":\"\",\"exdata4\":\"\",\"exdata5\":\"\",\"exdata6\":\"\",\"exdata7\":\"\",\"exdata8\":\"\",\"exdata9\":\"\",\"exdata10\":\"\"}";
              System.out.println(value);
            JSONObject jsonObject = JSONObject.parseObject(value);
            System.out.println(jsonObject.keySet());
          /*  for(String string:jsonObject.keySet()){
                System.out.println("key【"+string+"】 value 【"+jsonObject.get(string)+"】");
            }*/
           String kk= jedis.set(key,value);

           jedis.expire(key,30*24*60*60);
            System.out.println(jedis.ttl(key));
            System.out.println(jedis.ttl(key));

            System.out.println("kk:"+kk);
            System.out.println("OK".equals(kk));

            // 获取数据并输出
            //jedis.del("pushTime:0");
		  /*   Set<String> keys= jedis.keys("*");
		     System.out.println("总共有的key数："+keys.size());*/
            //  Iterator<String> it=keys.iterator();
		  /*   while(it.hasNext()) {
		       String key=it.next();
		       if(!(key.startsWith("get")||key.startsWith("push")||key.startsWith("sess"))){
		    	//  System.out.println(jedis.del(key));
		       }
		     } */


            //获取zset 后续自己学习
		  /*     System.out.println(jedis.zcard("getAgentStateList:zset:4321"));
		       System.out.println(jedis.zcard("getAgentStateList:zset:8989"));
		       System.out.println( jedis.zrange("getAgentStateList:zset:4321", 0, jedis.zcard("getAgentStateList:zset:4321")));
		       System.out.println( jedis.zrange("getAgentStateList:zset:8989", 0, jedis.zcard("getAgentStateList:zset:8989")));


		       System.out.println(jedis.zcard("getAgentStateList:zset:8989:1000001995"));
		       System.out.println(jedis.zcard("getAgentStateList:zset:8989:1000002190"));
*/

		      String getValue =jedis.get("session_id");
              System.out.println("获取的值:"+getValue);
		      //开始时间
            long s = System.currentTimeMillis();

            //集合中个数
            //System.out.println(jedis.zcard("getAgentStateList:zset:8989:1000006725"));

            Set<String> set= jedis.zrange("getAgentStateList:zset:01020180413", 0, -1);
            //string jedis.set("getAgentStateList:zset:01020180413:1000008282", "3001;赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
            //list jedis.lpush("getAgentStateList:zset:01020180413:1000008282", "3001;赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
			/*jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;赵六;SIP:1002;101;216;1538996135;218;;;0;-1");

			jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
			jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
			jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
*/
            //key在集合当中排序
            //System.out.println(jedis.zrank("getAgentStateList:zset:8989:1000005030","88888"));


            System.out.println("查询坐席状态时间:("
                    + (System.currentTimeMillis() - s) + ")ms");
            JSONObject valueObject1 = new JSONObject();
            System.out.println(valueObject1);
            System.out.println(valueObject1==null);
            System.out.println(valueObject1.isEmpty());
            JSONObject valueObject2 = new JSONObject();
            System.out.println(valueObject2);




        } catch (Exception e) {
                // TODO: handle exception
                System.out.println("插入数据异常");
                e.printStackTrace();
            }finally {
            if (jedis != null)
                jedis.close();
        }
        jedisPool.destroy();



    }
}
