package IO;

public class Test1 {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        StringBuffer sb= new StringBuffer("");

		String total="2018-01-08 08:55:06,474 DEBUG - [pool-1-thread-26] [DataPushThread] [pool-1-thread-26]正在处理本次推送任务,参数为{";
		String total1="2018-01-08 10:55:03,273 INFO  - [pool-1-thread-5] [DataPushThread] [pool-1-thread-5]推送后接收的返回值，";
  
		
	
		String start=total.substring(20, 23);
		System.out.println(total.indexOf("DEBUG"));
		System.out.println(total.indexOf("["));
		System.out.println(total.indexOf("]"));
		String key=total.substring(total.indexOf("["),total.indexOf("]")+1);
		System.out.println("------------"+key);


		System.out.println(total1.indexOf("INFO"));
		System.out.println(total1.indexOf("["));
		System.out.println(total1.indexOf("]"));
		String key1=total1.substring(total1.indexOf("["),total.indexOf("]"));
		System.out.println("------------"+key1);




		System.out.println("开始ms:"+start);

	}

}
