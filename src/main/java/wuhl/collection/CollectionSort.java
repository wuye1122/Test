package wuhl.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CollectionSort {

	/**
	 * @author wuhl
	 * void
	 */
	
	/* ����  https://www.cnblogs.com/huangfox/archive/2012/07/05/2577306.html
	 * map��list��set 
	 * 
	 * map
	 * 
	 * map����HashMap��TreeMap�������Ѿ������TreeMap�ú����ʵ�֣���Ȼ���������ܡ�
	 * ��ôHashMap��ô����key�������أ������ܼ򵥣���HashMap������һ��TreeMap��
	 * 
	 * 
	 * list ����(Arrays.sort() �ײ�<--- Collections.sort() )
	 * 
	 * ������Զ��������Ҫʵ��Comparable�ӿ�ʹ�ö���������С��Ƚϡ��Ĺ��ܣ�
	 * ��Ȼ����Ҳ�������ⲿʹ��Comparator���涨������
	 * 
	 * 
	 * set  
	 *  java��Set������һ���������ظ�Ԫ�ص�Collection�����������ȿ�����������
	 *  
	 *  set����HashSet��TreeSet��HashSet�ǻ���HashMap�ģ�TreeSet�ǻ���TreeMap��            
	 *  TreeMap���ú����ʵ�֣���Ȼ�;��������ܣ�����Ȼ�;��������ܡ���ָ��ӵ�����򡢽���ĵ�������            
	 *  ��ôHashSet��ô�����أ����ǿ��Խ�HashSetת��List��Ȼ����List��������       
	 *  
	 *  ���Լ��Ĺؼ��ֶ�����д����Ϊ��ʹ��HashSetʱ��hashCode()�����ͻ�õ����ã�
	 *  �ж��Ѿ��洢�ڼ����еĶ����hash codeֵ�Ƿ������ӵĶ����hash codeֵһ�£�
	 *  �����һ�£�ֱ�Ӽӽ�ȥ�����һ�£��ٽ���equals�����ıȽϣ�equals�����������true��
	 *  ��ʾ�����Ѿ��ӽ�ȥ�ˣ��Ͳ����������µĶ��󣬷���ӽ�ȥ��
	 *           
	 *                
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List list =new ArrayList();
		list.add("2");
		list.add("1");
		list.add("3");
		list.add("4");
		
		//����
        System.out.println(list.toString());
        
        //Arrays.sort() ת��������   ת����list
        Object[] o= list.toArray();
		Arrays.sort(o);
        System.out.println(Arrays.asList(o));
        System.out.println("ԭlist����:"+list.toString());
        
        
        //ֱ��ʹ��Collections.sort()
        Collections.sort(list);
        System.out.println("ֱ�Ӹı�ԭ��list: "+list.toString());
        
        
        Set<String> set = new HashSet<String>();  
        
        set.add("a");  
        set.add("b");  
        set.add("c");  
        set.add("d");
        System.out.println(set.toString());
        Set<String> treeSet = new TreeSet<String>(set);  
        System.out.println(treeSet.toString());

	}

}
