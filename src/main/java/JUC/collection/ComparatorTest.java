package JUC.collection;

import java.util.ArrayList;
import java.util.List;

public class ComparatorTest {

	/**
	 * @author JUC
	 * void
	 */
	
/*	Comparable��Comparator��������ʵ�ּ�����Ԫ�صıȽϡ�����ġ�
	Comparable���ڼ����ڲ�����ķ���ʵ�ֵ�����λ��java.lang�¡�
	Comparator���ڼ����ⲿʵ�ֵ�����λ��java.util�¡�

	Comparable��һ����������Ѿ�֧���ԱȽ�����Ҫʵ�ֵĽӿڣ���String��Integer�Լ���ʵ����Comparable�ӿڣ�
	����ɱȽϴ�С�������Զ�����Ҫ�ڼ���list�����к��ܹ�����Ҳ����ʵ��Comparable�ӿڣ�
	����Collections���sort��������ʱ����ָ��Comparator���Ǿ�����Ȼ˳��������ν��Ȼ˳�����ʵ��Comparable�ӿ��趨������ʽ��


	Comparator��һ��ר�õıȽ��������������֧���ԱȽϻ����ԱȽϺ�����������Ҫ��ʱ��
	��дһ���Ƚ����������������֮���С�ıȽϡ�
	Comparator������һ�ֲ���ģʽ(strategy design pattern)�����ǲ��ı��������
	����һ�����Զ���(strategy object)���ı�������Ϊ��

	�ܶ���֮Comparable��������ɱȽϣ�Comparator���ⲿ����ʵ�ֱȽϡ�*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		//compareTo(��װ��) 	
		/*
		 * Դ��
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
