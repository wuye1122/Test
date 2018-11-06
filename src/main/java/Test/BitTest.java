package Test;

import java.util.HashSet;
import java.util.Set;

public class BitTest {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		 Set<String> topicSet = new HashSet<String>();
		topicSet.add("session_detail");
		topicSet.add("agentProxy");
		System.out.println("==============topicSet:"+topicSet.toString());
		System.out.println("==============topicSet.size:"+topicSet.size());


		//A*2^n
		System.out.println(9<<4);
		//是否奇数偶数  二进制 奇数最低位一定是1
		System.out.println(1&1);
		System.out.println(2313124&1);
	    //交换两个数  异或 ==(如果这个数只能是0或者1,相同取0不同取1,完全和原始数据一样)
		int a=4;
		int b=3;
		System.out.println(a^0);
		System.out.println(a^1);
		System.out.println(a^b);
		System.out.println(b^a);
        a=a^b;
        b=a^b;
        a=a^b;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a^-1);
		System.out.println(b^-1);
		System.out.println(Integer.toBinaryString(3));
		System.out.println(Integer.toBinaryString(-3));
		System.out.println(Integer.toBinaryString(-2));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-0));
		System.out.println(Integer.toBinaryString(1));
        //取绝对值 (a^(a>>31))-(a>>31)
        int c=2;
        int d=-4;
        System.out.println(c>>31);//符号
        System.out.println(d>>31);//符号
        
        System.out.println(c^(c>>31));//0本身
        System.out.println(d^(d>>31));//-1变成正数

        System.out.println(c^(c>>31)-(c>>31));//0本身
        System.out.println((d^(d>>31))-(d>>31));//-1变成正数


	}

}
