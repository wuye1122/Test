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
	
	/* 排序  https://www.cnblogs.com/huangfox/archive/2012/07/05/2577306.html
	 * map和list和set 
	 * 
	 * map
	 * 
	 * map包括HashMap和TreeMap，上面已经提过，TreeMap用红黑树实现，天然具有排序功能。
	 * 那么HashMap怎么按“key”排序呢？方法很简单，用HashMap来构造一个TreeMap。
	 * 
	 * 
	 * list 数组(Arrays.sort() 底层<--- Collections.sort() )
	 * 
	 * 如果是自定义对象，需要实现Comparable接口使得对象自身就有“比较”的功能，
	 * 当然我们也可以在外部使用Comparator来规定其排序。
	 * 
	 * 
	 * set  
	 *  java中Set集合是一个不包含重复元素的Collection，首先我们先看看遍历方法
	 *  
	 *  set包括HashSet和TreeSet，HashSet是基于HashMap的，TreeSet是基于TreeMap的            
	 *  TreeMap是用红黑树实现，天然就具有排序功能，“天然就具有排序功能”是指它拥有升序、降序的迭代器。            
	 *  那么HashSet怎么排序呢？我们可以将HashSet转成List，然后用List进行排序。       
	 *  
	 *  用自己的关键字段来重写，因为当使用HashSet时，hashCode()方法就会得到调用，
	 *  判断已经存储在集合中的对象的hash code值是否与增加的对象的hash code值一致；
	 *  如果不一致，直接加进去；如果一致，再进行equals方法的比较，equals方法如果返回true，
	 *  表示对象已经加进去了，就不会再增加新的对象，否则加进去。
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
		
		//排序
        System.out.println(list.toString());
        
        //Arrays.sort() 转换成数组   转换成list
        Object[] o= list.toArray();
		Arrays.sort(o);
        System.out.println(Arrays.asList(o));
        System.out.println("原list不变:"+list.toString());
        
        
        //直接使用Collections.sort()
        Collections.sort(list);
        System.out.println("直接改变原来list: "+list.toString());
        
        
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
