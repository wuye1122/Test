package JUC.kafka.zookeeper;

import java.util.Arrays;

public class Test {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Description="2,3,4,5,6,7,8,9,10,11;";
		String a=Description.substring(0, Description.length()-1);
		int sumDay=0;
		String days[] = a.split(",");
/*						logger.debug("打印：getDescription::::------"+p1.getDescription());
*/
		int str=days.length;
		System.out.println("数组长度："+days.length);
		System.out.println("数组长度："+days[days.length-1]);
		System.out.println("数组长度："+days[0]);

		System.out.println(days.length==11);
		System.out.println(Arrays.asList(days).contains("8"));
		if(days.length==11){
			str=7;
		}else{
			System.out.println("打印：getDescription::::"+Description);

			if(Arrays.asList(days).contains("8")){
				str--;
			}
			if(Arrays.asList(days).contains("9")){
				str--;
			}
			if(Arrays.asList(days).contains("10")){
				str--;
			}
			if(Arrays.asList(days).contains("11")){
				str--;
			}
			System.out.println("打印：str:"+str);

		}
		System.out.println("最终加上的是:"+str);

		sumDay += str;
		System.out.println(sumDay);
		
	}

}
