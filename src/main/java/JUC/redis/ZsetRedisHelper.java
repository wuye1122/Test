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
	
	//�ϴ���ϵ�� ������ҵID ������ID ��ѯredis ��ȡ��ϯ״̬101
	public HashMap<String,Long> getAgentStatus(String ent,String skill){
		long start = System.currentTimeMillis();

		JedisPool jedisPool = new JedisPool("10.130.29.226", 6379); // 8989
																	// 1000006725
		Jedis jedis = null;
		jedis = jedisPool.getResource();
		jedis.select(5);
		// ��ʼʱ��
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
	//	System.out.println("��ѯ��ϯ״̬map��" + map.toString());

		System.out.println("��ѯ��ϯ״̬ʱ��:(" + (System.currentTimeMillis() - s)
				+ ")ms");
		return map;
	}
	
	//����ͷ�� ���ƺ���
	public void limitCall(){
   
	/*	�����������Read timed out 
	 * 
	 *  ����취����redis����ʱʱ�� Ĭ�ϵ���2000ms  
         
		��jedis��Ĭ�Ϲ��췽���У���ʱ��ʱ��һ�㱻Ĭ������Ϊ2000���룬Ҳ����2�롣
		��Ȼ���ʱ�䣬����Redis���ִ��ڴ��ж�ȡ���ݵ����ݿ���˵Ӧ�����൱���ˣ�
		����Ϊʲô���ᳬʱ�����ܵ�ԭ���ǣ���Redis���������ܴ�ʱ��������Redis server���ܻ���ֳ�ʱ��
		��ΪRedis������ʱ���ǵ��߳����������еĿͻ��˵����ӵġ����������ǳ�������������ܴ�ʱ��
		��ҲҪ��ѭFIFO�ĵ��Ȳ��ԡ���˿��ܻ���ֳ�ʱ�������
		
		*/
//		JedisPool jedisPool = new JedisPool("10.130.29.34", 6379); 
//		Jedis jedis = new Jedis("127.0.0.1", 8371,100000);//����ʱʱ������Ϊ100000����  

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
		
		//System.out.println("expire:"+jedis.expire("CallDetail", 30));//��1

		
			String s1 = jedis.get("CallDetail");
			if (null == s1) {
				jedis.set("CallDetail", "1");
			    jedis.expireAt("CallDetail", 1519380359);// //����keyֵ����������
				System.out.println(jedis.get("CallDetail"));
				System.out.println(jedis.ttl("CallDetail"));

			} else {
				// ���keyֵ���� û������ʧЧʱ�� �����ж�
				if (jedis.ttl("CallDetail") == -1) {
					jedis.expireAt("CallDetail", 1519380359);
				}
				jedis.incr("CallDetail");
				System.out.println(jedis.get("CallDetail"));
				System.out.println(jedis.ttl("CallDetail"));
			}

		/*long s = System.currentTimeMillis();
		System.out.println("ʱ�����"+s);
		System.out.println("ʱ�����"+s/1000);

		String date1=stampToDate(String.valueOf(s/1000)+"000");

		String date=stampToDate(String.valueOf(s));
		System.out.println("ʱ�����"+date1);
		System.out.println("ʱ�����"+date);*/
		//jedis.expireAt(key, unixTime)("CallDetail", 300);
/*		jedis.expireAt(key, unixTime);
*/	
		
		
		
		/*for(int i=0;i<100;i++){
			if(i%2==0){
				Thread.sleep(10000);//10��
			}
			
			System.out.println(i);
			System.out.println("ʱ���:" +System.currentTimeMillis());//����
			System.out.println("�����˶��:(" + (System.currentTimeMillis() - s)//����
					+ ")ms");
			System.out.println("���ж����룺"+jedis.ttl("CallDetail"));//��
			System.out.println("��ǰkeyֵ��"+jedis.get("CallDetail"));
			System.out.println(stampToDate(String.valueOf(System.currentTimeMillis())));
			System.out.println("===================");

		}
		*/
		
		/*if (null == s1) {
			jedis.set("CallDetail", "1");
			jedis.expire("CallDetail", 30);// //����keyֵ����������

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
		
	//��ʱ���ת��ʱ��
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
	        //Exists  Expire ExpiteAt  ����ʱ��  
			JedisPool jedisPool = new JedisPool("10.130.29.34", 6379); 
			
			Jedis jedis = null;
			 try{
				 jedis = jedisPool.getResource();
			    jedis.select(5);
	        jedis.set("exist","exist");  
	        jedis.set("existAt","existAt");  
	        Assert.assertTrue(jedis.exists("exist"));  
	        Assert.assertTrue(jedis.expire("exist",1)==1);  
	        // 1.����Ҫ���ǵ���������ʱ���request��ʱ�䣬2.��������Unix timestamp�����Ǻ���  
	        Assert.assertTrue(jedis.expireAt("existAt",System.currentTimeMillis()/1000+5)==1);  
	        System.out.println(System.currentTimeMillis()/1000+5);  
	        try {  
	            Thread.sleep(5050);  
	        } catch (InterruptedException e) {  
	            Assert.fail("�߳������쳣");  
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
		     * ����list
		     * jedis.lpush("pushTime1:1", "Redis");
		      jedis.lpush("pushTime1:1", "jedis");*/
			jedis.select(5);
			
		     // ��ȡ���ݲ����  
			//jedis.del("pushTime:0");
		  /*   Set<String> keys= jedis.keys("*");
		     System.out.println("�ܹ��е�key����"+keys.size());*/
		   //  Iterator<String> it=keys.iterator();
		  /*   while(it.hasNext()) {   
		       String key=it.next();  
		       if(!(key.startsWith("get")||key.startsWith("push")||key.startsWith("sess"))){
		    	//  System.out.println(jedis.del(key));
		       }
		     } */
		     //��ȡset
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
		        
		      //��ȡzset �����Լ�ѧϰ
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
		     
             //��ʼʱ��
		     long s = System.currentTimeMillis();

		     //�����и���
             //System.out.println(jedis.zcard("getAgentStateList:zset:8989:1000006725")); 
		       
		    Set<String> set= jedis.zrange("getAgentStateList:zset:01020180413", 0, -1);
			//string jedis.set("getAgentStateList:zset:01020180413:1000008282", "3001;����;SIP:1002;101;216;1538996135;218;;;0;-1");
			//list jedis.lpush("getAgentStateList:zset:01020180413:1000008282", "3001;����;SIP:1002;101;216;1538996135;218;;;0;-1");
			/*jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;����;SIP:1002;101;216;1538996135;218;;;0;-1");

			jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;����;SIP:1002;101;216;1538996135;218;;;0;-1");
			jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;����;SIP:1002;101;216;1538996135;218;;;0;-1");
			jedis.zadd("getAgentStateList:zset:01020180413:1000008282",1.0,"3001;����;SIP:1002;101;216;1538996135;218;;;0;-1");
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
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId2+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId3+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId4+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId5+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId6+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId7+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId8+";����;SIP:1002;101;216;1538996135;218;;;0;-1");

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
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId2+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId3+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId4+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId5+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId6+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId7+";����;SIP:1002;101;216;1538996135;218;;;0;-1");
						jedis.zadd("getAgentStateList:zset:01020180413:"+i,1.0,agentId8+";����;SIP:1002;101;216;1538996135;218;;;0;-1");

					}
				}





				//	3021-3030
			//
			}
			Object[] o= set.toArray();
		    
		    //key�ڼ��ϵ�������
		    //System.out.println(jedis.zrank("getAgentStateList:zset:8989:1000005030","88888"));

            HashMap<String,Long>map=new HashMap<String, Long>();
		 /*  for(int i=0;i<o.length;i++){
			   System.out.println(o[i]);
//			   String [] str= o[i].toString().split(";");
			  // System.out.println(str[0]);
//			   map.put(str[0], Long.valueOf(str[3]));
		   }*/
		   System.out.println("��ѯ��ϯ״̬map��"+map.toString());
	   
		    System.out.println("��ѯ��ϯ״̬ʱ��:("
					+ (System.currentTimeMillis() - s) + ")ms");

		    //System.out.println( jedis.zrange("getAgentStateList:zset:8989:1000002190", 0, jedis.zcard("getAgentStateList:zset:8989:1000002190")));

		       
		     
		       //��ȡhset
		   /*  List<String> l=  jedis.hvals("getDpsPushConfigInfo:hset:getConfigHash:120");
		     System.out.println(l.size());
		     for(String s:l){
		    	 System.out.println(s);
		     }*/
		     //jedis.hset("getDpsPushConfigInfo:hset:getConfigHash:slave-3:slave-3", "2", "1");
		     /* jedis.hvals("hashs")*/
		       
		       
			//��ȡlist
			//�洢���ݵ��б���
		    // ��ȡ�洢�����ݲ����  �ڶ�������ʼλ��   �������������λ��(-1����ȫ��)

/*		     List<String> list0 = jedis.lrange("pushTime:0", 0 ,-1);
		     List<String> list1 = jedis.lrange("pushTime:1", 0 ,-1);
		     List<String> list2 = jedis.lrange("pushTime:2", 0 ,-1);
		     List<String> list3 = jedis.lrange("pushTime:0", 0 ,-1);*/
		     
/*		     System.out.println(Runtime.getRuntime().availableProcessors());
*/
		    // jedis.del("pushTime:0");
		     //System.out.println(  jedis.lpush("pushTime:5", "stra"));
		                            
		/*     System.out.println(list0.toString()+" ���ȣ�"+list0.size());		     
		     System.out.println(list1.toString()+" ���ȣ�"+list1.size());
		     System.out.println(list2.toString()+" ���ȣ�"+list2.size());*/
			
		   /*  System.out.println(  jedis.lpush("pushTime:5", "A"));
		     System.out.println(  jedis.lpush("pushTime:5", "readFile"));*/
		
		  /*   System.out.println(list.toString());
		     System.out.println("��ӡ����: "+list.size());
		     for(int i=0; i<list.size(); i++) {        
		       System.out.println("�б���Ϊ: "+list.get(i));      
		     }   */

	          
	          
	
			try {
				
				//sismember �ж�key�����Ƿ����  value ֵ�����ظ� �����е�SISMEMBER�����򼯺��е�ZRANK��ZREVRANK��ZSCORE�������жϳ�Ա�ڲ��ڼ�����
		          //System.out.println(jedis.sismember("pushTime:0", "829"));
			/*	for (int i = 0; i < 50000; i++) {
					String id = SessionUtil.generateSessionId();
					System.out.println("session id :" + id);
					String s = jedis.set(id, id);
					System.out.println(s);
				}*/

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���������쳣");
				System.out.println(e);
			}
			System.out.println("redis��ȡrequestId,����ʱ:("
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
