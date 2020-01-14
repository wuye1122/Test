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
		     * ����list
		     * jedis.lpush("pushTime1:1", "Redis");
		      jedis.lpush("pushTime1:1", "jedis");*/
			jedis.select(4);
			
		     // ��ȡ���ݲ����
		  /*   Set<String> keys= jedis.keys("*");
		     System.out.println("�ܹ��е�key����"+keys.size());
		     Iterator<String> it=keys.iterator();
		     while(it.hasNext()) {   
		       String key=it.next();     
		       System.out.println(key);     
		     }   */ 
			//��ȡlist
			//�洢���ݵ��б���
		    // ��ȡ�洢�����ݲ����  �ڶ�������ʼλ��   �������������λ��(-1����ȫ��)

		     List<String> list0 = jedis.lrange("pushTime:0", 0 ,-1);
		     List<String> list1 = jedis.lrange("pushTime:1", 0 ,-1);
		     List<String> list2 = jedis.lrange("pushTime:2", 0 ,-1);
		     List<String> list3 = jedis.lrange("pushTime2:5", 0 ,-1);
		     
		   //  System.out.println(  jedis.lpush("pushTime:5", "stra"));
		                            
		/*     System.out.println(list0.toString()+" ���ȣ�"+list0.size());		     
		     System.out.println(list1.toString()+" ���ȣ�"+list1.size());
		     System.out.println(list2.toString()+" ���ȣ�"+list2.size());*/
			
		   /*  System.out.println(  jedis.lpush("pushTime:5", "A"));
		     System.out.println(  jedis.lpush("pushTime:5", "readFile"));*/
		     System.out.println(list3.toString()+" ���ȣ�"+list3.size());

		     int sum=0;
		     for(int i=0;i<list3.size();i++){
		    	 sum+=Integer.parseInt(list3.get(i));
		     }
	          System.out.println("�̸߳�����"+list3.size()+" ������ʱ����"+sum);
	          float s=sum;
	          System.out.println("�̸߳�����"+list3.size()+" ƽ�����ͣ�"+s/list3.size());

		  /*   System.out.println(list.toString());
		     System.out.println("��ӡ����: "+list.size());
		     for(int i=0; i<list.size(); i++) {        
		       System.out.println("�б���Ϊ: "+list.get(i));      
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
