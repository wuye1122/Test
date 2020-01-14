package JUC.collection;

import java.util.ArrayList;
import java.util.List;

public class ComparatorTest {

	/**
	 * @author JUC
	 * void
	 */
	
/*	Comparable和Comparator都是用来实现集合中元素的比较、排序的。
	Comparable是在集合内部定义的方法实现的排序，位于java.lang下。
	Comparator是在集合外部实现的排序，位于java.util下。

	Comparable是一个对象本身就已经支持自比较所需要实现的接口，如String、Integer自己就实现了Comparable接口，
	可完成比较大小操作。自定义类要在加入list容器中后能够排序，也可以实现Comparable接口，
	在用Collections类的sort方法排序时若不指定Comparator，那就以自然顺序排序。所谓自然顺序就是实现Comparable接口设定的排序方式。


	Comparator是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足要求时，
	可写一个比较器来完成两个对象之间大小的比较。
	Comparator体现了一种策略模式(strategy design pattern)，就是不改变对象自身，
	而用一个策略对象(strategy object)来改变它的行为。

	总而言之Comparable是自已完成比较，Comparator是外部程序实现比较。*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		//compareTo(包装类) 	
		/*
		 * 源码
		 * 
		public final class Integer extends Number implements Comparable<Integer>  
		int thisVal = this.value;
		int anotherVal = anotherInteger.value;
		return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));		
		*/
		
		
		Integer i = 5;
		System.out.println(i.compareTo(1));
		System.out.println(i.compareTo(5));
		System.out.println(i.compareTo(10));

		List<ComparatorPo> list=new ArrayList<ComparatorPo>();
        list.add(new ComparatorPo(1,"b") );
        list.add(new ComparatorPo(4,"c") );
        list.add(new ComparatorPo(6,"a") );
        list.add(new ComparatorPo(2,"v") );

        System.out.println(list);
        //
       // Collections.sort(list);
        System.out.println(list);
        
        

	}

}
