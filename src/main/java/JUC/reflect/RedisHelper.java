package JUC.reflect;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisHelper {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisPool jedisPool = new JedisPool("10.130.29.226", 6381);
		Jedis jedis = null;
		try {
			long start = System.currentTimeMillis();

			jedis = jedisPool.getResource();
			//jedis.select(4);

		    /* 
		     * 插入list
		     * jedis.lpush("pushTime1:1", "Redis");
		      jedis.lpush("pushTime1:1", "jedis");*/
			jedis.select(4);
			
		     // 获取数据并输出
		  /*   Set<String> keys= jedis.keys("*");
		     System.out.println("总共有的key数："+keys.size());
		     Iterator<String> it=keys.iterator();
		     while(it.hasNext()) {   
		       String key=it.next();     
		       System.out.println(key);     
		     }   */ 
			//获取list
			//存储数据到列表中
		    // 获取存储的数据并输出  第二个代表开始位置   第三个代表结束位置(-1代表全部)

		     List<String> list0 = jedis.lrange("pushTime:0", 0 ,-1);
		     List<String> list1 = jedis.lrange("pushTime:1", 0 ,-1);
		     List<String> list2 = jedis.lrange("pushTime:2", 0 ,-1);
		     List<String> list3 = jedis.lrange("pushTime2:5", 0 ,-1);
		     
		   //  System.out.println(  jedis.lpush("pushTime:5", "stra"));
		                            
		/*     System.out.println(list0.toString()+" 长度："+list0.size());		     
		     System.out.println(list1.toString()+" 长度："+list1.size());
		     System.out.println(list2.toString()+" 长度："+list2.size());*/
			
		   /*  System.out.println(  jedis.lpush("pushTime:5", "A"));
		     System.out.println(  jedis.lpush("pushTime:5", "readFile"));*/
		     System.out.println(list3.toString()+" 长度："+list3.size());

		     int sum=0;
		     for(int i=0;i<list3.size();i++){
		    	 sum+=Integer.parseInt(list3.get(i));
		     }
	          System.out.println("线程个数："+list3.size()+" 推送总时长："+sum);
	          float s=sum;
	          System.out.println("线程个数："+list3.size()+" 平均推送："+s/list3.size());

		  /*   System.out.println(list.toString());
		     System.out.println("打印长度: "+list.size());
		     for(int i=0; i<list.size(); i++) {        
		       System.out.println("列表项为: "+list.get(i));      
		     }   */

	
			try {
				
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
