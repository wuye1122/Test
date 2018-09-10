package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class LogUtil {

	/**
	 * @author wuhl void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();

		Map<String, String> map = new HashMap<String, String>();

		try {
			// read file content from file
			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader(
					"writeFile:\\Users\\Administrator.USER-20161101FI\\Desktop\\800企业\\20180108dps\\dps20180108.1725.log");
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			while ((str = br.readLine()) != null) {
				sb.append(str + "/n");
				// System.out.println(str);
				 if(str.contains("推送后接收的返回值，result")){
					 list1.add("result");
				 }
			/*	if(str.contains("[pool-1-thread-4]")||str.contains("[pool-1-thread-24]")||str.contains("[pool-1-thread-42]")||str.contains("[pool-1-thread-44]")){
					System.out.println(str);
				}*/
				if (str.contains("DEBUG")) {

					// debug 开始
					String start = str.substring(20, 23);

					String key = str.substring(str.indexOf("["),
							str.indexOf("]") + 1);

					// System.out.println("key ms:"+key+"value: "+start);

					if (map.containsKey(key)) {
						list.add(map.get(key));
						map.remove(key);
					} else {
						map.put(key, start);
					}

				} else {
					// info 结束
					String end = str.substring(20, 23);
					String key = str.substring(str.indexOf("["),
							str.indexOf("]") + 1);
					// System.out.println("key ms:"+key+"value: "+end);

					if (map.containsKey(key)) {
						String value = String.valueOf(Integer.valueOf(end)
								- Integer.valueOf(map.get(key)));

						if (Integer.valueOf(value) < 0) {
							/*
							 * System.out.println("key:"+key+"----"+end);
							 * System.
							 * out.println("key:"+key+"----"+map.get(key));
							 */

							String val = String
									.valueOf(Integer.valueOf(value) + 1000);
							/* System.out.println("val:"+val); */

							map.put(key, val);

						} else{
							map.put(key, value);

						}
						
						list.add(map.get(key));
						map.remove(key);

					}

				}

			}

			System.out.println("map："+map.size());
			br.close();
			reader.close();
			System.out.println(list1.size());
	        
			//将数据插入redis
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
				try {
					for (String s : list) {
						System.out.println(s);
				/*		jedis.lpush("pushTime:20180108", s);*/
					}
					System.out.println("添加完成...."+list.size());
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
		

			//
		/*	for (String s : list) {
				System.out.println(s);
			}
*/
			// write string to file
			/*
			 * FileWriter writer = new FileWriter("c://test2.txt");
			 * BufferedWriter bw = new BufferedWriter(writer);
			 * System.out.println("打印当前行："+sb.toString());
			 * bw.write(sb.toString());
			 * 
			 * bw.close(); writer.close();
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
