package JUC.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public  class RedisHelper {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisPool jedisPool = new JedisPool("10.130.29.226", 6381);
		
	//	JedisPool jedisPool = new JedisPool("10.130.29.34", 6379);
		Jedis jedis = null;
		try {
			long start = System.currentTimeMillis();

			jedis = jedisPool.getResource();
			jedis.select(4);

		//	System.out.println(jedis.clientList());
			System.out.println(jedis.getClient().getHost());
			System.out.println(jedis.getClient().getPort());
			System.out.println(jedis.getClient().getDB());
	/*		System.out.println(jedis.getDB());
			System.out.println(jedis.getClient().getAll());
*/
		    /* 
		     * ����list
		     * jedis.lpush("pushTime1:1", "Redis");
		      jedis.lpush("pushTime1:1", "jedis");*/
			//jedis.select(8);
		

		/*	

			System.out.println();
			System.out.println(jedis.getClient().getHost());
			System.out.println(jedis.getClient().getPort());
			System.out.println(jedis.getDB());*/

		
          
              
		     // ��ȡ���ݲ����
	
		   //  Iterator<String> it=keys.iterator();
	/*	     while(it.hasNext()) {   
		    	 String ss="74d246bd-f591-458d-b3da-e98bd312990b:8989";
		       String key=it.next(); 
		       if(key.length()!=ss.length()){
				     System.out.println(key);
		       }
		     } */
			//��ȡlist
			//�洢���ݵ��б���
		    // ��ȡ�洢�����ݲ����  �ڶ�������ʼλ��   �������������λ��(-1����ȫ��)
/*
		     List<String> list0 = jedis.lrange("pushTime:0", 0 ,-1);
		     List<String> list1 = jedis.lrange("pushTime:1", 0 ,-1);
		     List<String> list2 = jedis.lrange("pushTime:2", 0 ,-1);
		     List<String> list3 = jedis.lrange("pushTime:20180108", 0 ,-1);*/
		    

		 //    System.out.println(Runtime.getRuntime().availableProcessors());

		    // jedis.del("pushTime:0");
		     //System.out.println(  jedis.lpush("pushTime:5", "stra"));
		                            
		/*     System.out.println(list0.toString()+" ���ȣ�"+list0.size());		     
		     System.out.println(list1.toString()+" ���ȣ�"+list1.size());
		     System.out.println(list2.toString()+" ���ȣ�"+list2.size());*/
			
		   /*  System.out.println(  jedis.lpush("pushTime:5", "A"));
		     System.out.println(  jedis.lpush("pushTime:5", "readFile"));*/
		 /*    System.out.println(list3.toString()+" ���ȣ�"+list3.size());

		     int sum=0;
		     for(int i=0;i<list3.size();i++){
		    	 sum+=Integer.parseInt(list3.get(i));
		     }
	          System.out.println("�̸߳�����"+list3.size()+" ������ʱ����"+sum);
	          float s=sum;
	          System.out.println("�̸߳�����"+list3.size()+" ƽ�����ͣ�"+s/list3.size());*/

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
				e.printStackTrace();
				System.out.println("���������쳣");
				System.out.println(e);
			}
		/*	System.out.println("redis��ȡrequestId,����ʱ:("
					+ (System.currentTimeMillis() - start) + ")ms");*/

	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (jedis != null)  
	                jedis.close();  
	        }  
	        jedisPool.destroy();  
	}

}
