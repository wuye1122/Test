package wuhl.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;


public class HashMapTest1 {

	/**
	 * @author wuhl
	 * void
	 * 
	 * 
	 * 将键映射到值的对象。一个映射不能包含重复的键；每个键最多只能映射到一个值。
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "11");
		map.put("2", "22");
		map.put("3", "33");
		//map.put("4", "44");
		
		//map ==>{}
	//	System.out.println(map.toString());
		//k v集合
	//	System.out.println("k集合:"+map.keySet().toString());
	//	System.out.println("v集合:"+map.values().toString());

		//是否包含k v
	//	System.out.println("是否包含k:"+map.containsKey("1"));
	//	System.out.println("是否包含v:"+map.containsValue("11"));
		
		//map.entrySet()  --> []
		Set<Entry<String, String>> set=map.entrySet();
		//System.out.println("map.entrySet():"+map.entrySet());	
		

		/* 遍历
		 * map和list和set 
		 * 
		 * map  遍历需要转换成set
		 *       
		 *       map两种遍历方式(keySet   entrySet)
		 *       　　    对于keySet其实是遍历了2次，一次是转为iterator，一次就从hashmap中取出key所对于的value。
		 *       而entryset只是遍历了第一次，他把key和value都放到了entry中，所以就快了。
		 * 
		 * list 遍历 使用for循环 
		 * 
		 * set  遍历 使用iterator 
		 *                
		 * */

		//for ()
		
	/*	Iterator<Map.Entry<String,String>> it=  map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry m=(Map.Entry)it.next();			
			System.out.println("1遍历 k："+m.getKey()+" v :"+m.getValue());
			
			map.put("1", "11");
			map.put("2", "22");
			map.put("3", "33");
			map.put("4", "44");
			String a=it.next().getKey();
			String b=it.next().getValue();
			System.out.println("1遍历 k："+a+" v :"+b);


		}*/
		
		
		//错误的便利
		/*Iterator<String> it1=  map.keySet().iterator();
		while(it1.hasNext()){
			Object k=it1.next();
			Object v=map.get(k);
		//	System.out.println("2遍历 k："+k+" v:"+v);
		}
		*/
		//尽量使用for each
		for(Map.Entry m:map.entrySet()){
			
			if(m.getKey()!=null){
				System.out.println("key  "+m.getKey());	
				System.out.println("value "+m.getValue());	
				break;
			}


		}
		
		/* 排序
		 * map和list和set 
		 * 
		 *  * 
		 * map
		 * 
		 * map包括HashMap和TreeMap，上面已经提过，TreeMap用红黑树实现，天然具有排序功能。
		 * 那么HashMap怎么按“key”排序呢？方法很简单，用HashMap来构造一个TreeMap。
		 * 
		 *                
		 * */
		//HashMap -> TreeMap
		Map treeMap=new TreeMap(map);
	//	System.out.println("按照key排序之后的map:"+treeMap);
		//怎么按照“value”进行排序？

		
	}

}
