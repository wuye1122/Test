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
	 * ����ӳ�䵽ֵ�Ķ���һ��ӳ�䲻�ܰ����ظ��ļ���ÿ�������ֻ��ӳ�䵽һ��ֵ��
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
		//k v����
	//	System.out.println("k����:"+map.keySet().toString());
	//	System.out.println("v����:"+map.values().toString());

		//�Ƿ����k v
	//	System.out.println("�Ƿ����k:"+map.containsKey("1"));
	//	System.out.println("�Ƿ����v:"+map.containsValue("11"));
		
		//map.entrySet()  --> []
		Set<Entry<String, String>> set=map.entrySet();
		//System.out.println("map.entrySet():"+map.entrySet());	
		

		/* ����
		 * map��list��set 
		 * 
		 * map  ������Ҫת����set
		 *       
		 *       map���ֱ�����ʽ(keySet   entrySet)
		 *       ����    ����keySet��ʵ�Ǳ�����2�Σ�һ����תΪiterator��һ�ξʹ�hashmap��ȡ��key�����ڵ�value��
		 *       ��entrysetֻ�Ǳ����˵�һ�Σ�����key��value���ŵ���entry�У����ԾͿ��ˡ�
		 * 
		 * list ���� ʹ��forѭ�� 
		 * 
		 * set  ���� ʹ��iterator 
		 *                
		 * */

		//for ()
		
	/*	Iterator<Map.Entry<String,String>> it=  map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry m=(Map.Entry)it.next();			
			System.out.println("1���� k��"+m.getKey()+" v :"+m.getValue());
			
			map.put("1", "11");
			map.put("2", "22");
			map.put("3", "33");
			map.put("4", "44");
			String a=it.next().getKey();
			String b=it.next().getValue();
			System.out.println("1���� k��"+a+" v :"+b);


		}*/
		
		
		//����ı���
		/*Iterator<String> it1=  map.keySet().iterator();
		while(it1.hasNext()){
			Object k=it1.next();
			Object v=map.get(k);
		//	System.out.println("2���� k��"+k+" v:"+v);
		}
		*/
		//����ʹ��for each
		for(Map.Entry m:map.entrySet()){
			
			if(m.getKey()!=null){
				System.out.println("key  "+m.getKey());	
				System.out.println("value "+m.getValue());	
				break;
			}


		}
		
		/* ����
		 * map��list��set 
		 * 
		 *  * 
		 * map
		 * 
		 * map����HashMap��TreeMap�������Ѿ������TreeMap�ú����ʵ�֣���Ȼ���������ܡ�
		 * ��ôHashMap��ô����key�������أ������ܼ򵥣���HashMap������һ��TreeMap��
		 * 
		 *                
		 * */
		//HashMap -> TreeMap
		Map treeMap=new TreeMap(map);
	//	System.out.println("����key����֮���map:"+treeMap);
		//��ô���ա�value����������

		
	}

}
