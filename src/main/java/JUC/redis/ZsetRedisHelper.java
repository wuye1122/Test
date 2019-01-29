package JUC.redis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import org.junit.Assert;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ZsetRedisHelper {

	/**
	 * @author JUC
	 * void
	 */
	
	//上次联系人 根据企业ID 技能组ID 查询redis 获取坐席状态101
	public HashMap<String,Long> getAgentStatus(String ent,String skill){
		long start = System.currentTimeMillis();

		JedisPool jedisPool = new JedisPool("10.130.29.226", 6379); // 8989
																	// 1000006725
		Jedis jedis = null;
		jedis = jedisPool.getResource();
		jedis.select(5);
		// 开始时间
		long s = System.currentTimeMillis();
		String key = "getAgentStateList:zset:" + ent + ":" + skill + "";
		Set<String> set = jedis.zrange(key, 0, -1);
		Object[] o = set.toArray();

		HashMap<String, Long> map = new HashMap<String, Long>();
		for (int i = 0; i < o.length; i++) {
			// System.out.println(o[i]);
			String[] str = o[i].toString().split(";");
			// System.out.println(str[0]);
			if(str[3].equals("101")){
				map.put(str[0], Long.valueOf(str[3]));
			}
		}
	//	System.out.println("查询坐席状态map：" + map.toString());

		System.out.println("查询坐席状态时间:(" + (System.currentTimeMillis() - s)
				+ ")ms");
		return map;
	}
	
	//今日头条 限制呼叫
	public void limitCall(){
   
	/*	问题的剖析：Read timed out 
	 * 
	 *  解决办法设置redis：超时时长 默认的是2000ms  
         
		在jedis的默认构造方法中，超时的时间一般被默认设置为2000毫秒，也就是2秒。
		当然这个时间，对于Redis这种从内存中读取数据的数据库来说应该是相当大了，
		但是为什么还会超时？可能的原因是，当Redis的数据量很大时，可能在Redis server可能会出现超时。
		因为Redis在运行时，是单线程来处理所有的客户端的连接的。当连接数非常多或者数据量很大时，
		这也要遵循FIFO的调度策略。因此可能会出现超时的情况。
		
		*/
//		JedisPool jedisPool = new JedisPool("10.130.29.34", 6379); 
//		Jedis jedis = new Jedis("127.0.0.1", 8371,100000);//将超时时间设置为100000毫秒  

/*
		Jedis jedis = null;*/
		 try{
		      Jedis jedis = new Jedis("10.130.29.34", 6379,100000);
		     // jedis = jedisPool.getResource();
		      jedis.select(5);

	/*	String remoteUrl = "TEL:18514787534";
		String key="SCD_30D_CV_"+remoteUrl;
		System.out.println(key);*/
		
	

		
		//jedis.expireAt("CallDetail", 300);
		
		//System.out.println("expire:"+jedis.expire("CallDetail", 30));//秒1

		
			String s1 = jedis.get("CallDetail");
			if (null == s1) {
				jedis.set("CallDetail", "1");
			    jedis.expireAt("CallDetail", 1519380359);// //设置key值的生命周期
				System.out.println(jedis.get("CallDetail"));
				System.out.println(jedis.ttl("CallDetail"));

			} else {
				// 如果key值存在 没有设置失效时间 进行判断
				if (jedis.ttl("CallDetail") == -1) {
					jedis.expireAt("CallDetail", 1519380359);
				}
				jedis.incr("CallDetail");
				System.out.println(jedis.get("CallDetail"));
				System.out.println(jedis.ttl("CallDetail"));
			}

		/*long s = System.currentTimeMillis();
		System.out.println("时间戳："+s);
		System.out.println("时间戳："+s/1000);

		String date1=stampToDate(String.valueOf(s/1000)+"000");

		String date=stampToDate(String.valueOf(s));
		System.out.println("时间戳："+date1);
		System.out.println("时间戳："+date);*/
		//jedis.expireAt(key, unixTime)("CallDetail", 300);
/*		jedis.expireAt(key, unixTime);
*/	
		
		
		
		/*for(int i=0;i<100;i++){
			if(i%2==0){
				Thread.sleep(10000);//10秒
			}
			
			System.out.println(i);
			System.out.println("时间戳:" +System.currentTimeMillis());//毫秒
			System.out.println("经历了多久:(" + (System.currentTimeMillis() - s)//毫秒
					+ ")ms");
			System.out.println("还有多少秒："+jedis.ttl("CallDetail"));//秒
			System.out.println("当前key值："+jedis.get("CallDetail"));
			System.out.println(stampToDate(String.valueOf(System.currentTimeMillis())));
			System.out.println("===================");

		}
		*/
		
		/*if (null == s1) {
			jedis.set("CallDetail", "1");
			jedis.expire("CallDetail", 30);// //设置key值的生命周期

		} else {
			jedis.incr("CallDetail");
		}*/

	   } catch (Exception e) {  
           e.printStackTrace();  
       } finally {  
    	  
           /*if (jedis != null)  
               jedis.close();  */
       }  
     //  jedisPool.destroy();  
}
		
	//将时间戳转换时间
	public static String stampToDate(String s){
		String res;
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	
   public void testExistsExpireExpireat(){  
	        //Exists  Expire ExpiteAt  生存时间  
			JedisPool jedisPool = new JedisPool("10.130.29.34", 6379); 
			
			Jedis jedis = null;
			 try{
				 jedis = jedisPool.getResource();
			    jedis.select(5);
	        jedis.set("exist","exist");  
	        jedis.set("existAt","existAt");  
	        Assert.assertTrue(jedis.exists("exist"));  
	        Assert.assertTrue(jedis.expire("exist",1)==1);  
	        // 1.这里要考虑到服务器的时间和request的时间，2.而且是秒Unix timestamp，不是毫秒  
	        Assert.assertTrue(jedis.expireAt("existAt",System.currentTimeMillis()/1000+5)==1);  
	        System.out.println(System.currentTimeMillis()/1000+5);  
	        try {  
	            Thread.sleep(5050);  
	        } catch (InterruptedException e) {  
	            Assert.fail("线程休眠异常");  
	        }  
	        System.out.println(System.currentTimeMillis()/1000);  
	        Assert.assertTrue(!jedis.exists("exist"));  
	        Assert.assertTrue(!jedis.exists("existAt"));  

			   } catch (Exception e) {  
		           e.printStackTrace();  
		       } finally {  
		           if (jedis != null)  
		               jedis.close();  
		       }  
		       jedisPool.destroy();  
	    }  
	
	
	public static void main(String[] args) {
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
			jedis.select(5);
			
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
		     //获取set
		 /*    Set<String> s1 = jedis.smembers("getDpsPushConfigInfo:set:getEntIdList:slave-1");
		     Set<String> s2 = jedis.smembers("getDpsPushConfigInfo:set:getEntIdList:slave-2");
		     Set<String> s3 = jedis.smembers("getDpsPushConfigInfo:set:getEntIdList:slave-3");

		     System.out.println(s1);		     
		     System.out.println(s2);
		     System.out.println(s3);
		        Iterator<String> it1=s3.iterator() ;   */
		   /*     while(it1.hasNext()){   
		            String obj=it1.next();   
		            System.out.println(obj);   
		        }  */
		        
		      //获取zset 后续自己学习
		  /*     System.out.println(jedis.zcard("getAgentStateList:zset:4321")); 
		       System.out.println(jedis.zcard("getAgentStateList:zset:8989")); 
		       System.out.println( jedis.zrange("getAgentStateList:zset:4321", 0, jedis.zcard("getAgentStateList:zset:4321")));
		       System.out.println( jedis.zrange("getAgentStateList:zset:8989", 0, jedis.zcard("getAgentStateList:zset:8989")));

		       
		       System.out.println(jedis.zcard("getAgentStateList:zset:8989:1000001995")); 
		       System.out.println(jedis.zcard("getAgentStateList:zset:8989:1000002190")); 
*/
		       /*  System.out.println(jedis.zcard("getDpsPushConfigInfo:zset:getEntIdList:slave-1")); 
		       System.out.println(jedis.zcard("getDpsPushConfigInfo:zset:getEntIdList:slave-2")); 
		       System.out.println(jedis.zcard("getDpsPushConfigInfo:zset:getEntIdList:slave-3")); */
		    //   System.out.println( jedis.zrange("getDpsPushConfigInfo:zset:getEntIdList:slave-3", 1, 2));
		     
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
			for(int i=1000008288;i<=1000008357;i++){

				if(i!=1000008327&&i!=1000008328){
					if(i!=1000008327&&i!=1000008328&&i>1000008327){
						System.out.println(i);
						System.out.println(i-1000008288);
						System.out.println(3000+(i-1000008288)*10+1);
						String agentId2=String.valueOf(3000+(i-1000008288)*10+2);
						String agentId3=String.valueOf(3000+(i-1000008288)*10+3);
						String agentId4=String.valueOf(3000+(i-1000008288)*10+4);
						String agentId5=String.valueOf(3000+(i-1000008288)*10+5);
						String agentId6=String.valueOf(3000+(i-1000008288)*10+6);
						String agentId7=String.valueOf(3000+(i-1000008288)*10+7);
						String agentId8=String.valueOf(3000+(i-1000008288)*10+8);

						System.out.println("=====================");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId2+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId3+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId4+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId5+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId6+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId7+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId8+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");

						//3411
					}else{
						System.out.println(i);
						System.out.println(i-1000008288);
						String agentId2=String.valueOf(3000+(i-1000008288)*10+2);
						String agentId3=String.valueOf(3000+(i-1000008288)*10+3);
						String agentId4=String.valueOf(3000+(i-1000008288)*10+4);
						String agentId5=String.valueOf(3000+(i-1000008288)*10+5);
						String agentId6=String.valueOf(3000+(i-1000008288)*10+6);
						String agentId7=String.valueOf(3000+(i-1000008288)*10+7);
						String agentId8=String.valueOf(3000+(i-1000008288)*10+8);


						System.out.println("=====================");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId2+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId3+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId4+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId5+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId6+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId7+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId8+";赵六;SIP:1002;101;216;1538996135;218;;;0;-1");

					}
				}





				//	3021-3030
			//
			}
			Object[] o= set.toArray();
		    
		    //key在集合当中排序
		    //System.out.println(jedis.zrank("getAgentStateList:zset:8989:1000005030","88888"));

            HashMap<String,Long>map=new HashMap<String, Long>();
		 /*  for(int i=0;i<o.length;i++){
			   System.out.println(o[i]);
//			   String [] str= o[i].toString().split(";");
			  // System.out.println(str[0]);
//			   map.put(str[0], Long.valueOf(str[3]));
		   }*/
		   System.out.println("查询坐席状态map："+map.toString());
	   
		    System.out.println("查询坐席状态时间:("
					+ (System.currentTimeMillis() - s) + ")ms");

		    //System.out.println( jedis.zrange("getAgentStateList:zset:8989:1000002190", 0, jedis.zcard("getAgentStateList:zset:8989:1000002190")));

		       
		     
		       //获取hset
		   /*  List<String> l=  jedis.hvals("getDpsPushConfigInfo:hset:getConfigHash:120");
		     System.out.println(l.size());
		     for(String s:l){
		    	 System.out.println(s);
		     }*/
		     //jedis.hset("getDpsPushConfigInfo:hset:getConfigHash:slave-3:slave-3", "2", "1");
		     /* jedis.hvals("hashs")*/
		       
		       
			//获取list
			//存储数据到列表中
		    // 获取存储的数据并输出  第二个代表开始位置   第三个代表结束位置(-1代表全部)

/*		     List<String> list0 = jedis.lrange("pushTime:0", 0 ,-1);
		     List<String> list1 = jedis.lrange("pushTime:1", 0 ,-1);
		     List<String> list2 = jedis.lrange("pushTime:2", 0 ,-1);
		     List<String> list3 = jedis.lrange("pushTime:0", 0 ,-1);*/
		     
/*		     System.out.println(Runtime.getRuntime().availableProcessors());
*/
		    // jedis.del("pushTime:0");
		     //System.out.println(  jedis.lpush("pushTime:5", "stra"));
		                            
		/*     System.out.println(list0.toString()+" 长度："+list0.size());		     
		     System.out.println(list1.toString()+" 长度："+list1.size());
		     System.out.println(list2.toString()+" 长度："+list2.size());*/
			
		   /*  System.out.println(  jedis.lpush("pushTime:5", "A"));
		     System.out.println(  jedis.lpush("pushTime:5", "readFile"));*/
		
		  /*   System.out.println(list.toString());
		     System.out.println("打印长度: "+list.size());
		     for(int i=0; i<list.size(); i++) {        
		       System.out.println("列表项为: "+list.get(i));      
		     }   */

	          
	          
	
			try {
				
				//sismember 判断key里面是否包含  value 值不能重复 集合中的SISMEMBER和有序集合中的ZRANK、ZREVRANK、ZSCORE都可以判断成员在不在集合中
		          //System.out.println(jedis.sismember("pushTime:0", "829"));
			/*	for (int i = 0; i < 50000; i++) {
					String id = SessionUtil.generateSessionId();
					System.out.println("session id :" + id);
					String s = jedis.set(id, id);
					System.out.println(s);
				}*/

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("插入数据异常");
				System.out.println(e);
			}
			System.out.println("redis获取requestId,共耗时:("
					+ (System.currentTimeMillis() - start) + ")ms");

	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (jedis != null)  
	                jedis.close();  
	        }  
	        jedisPool.destroy();  
	}

}
