package URL;

public class StringU {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String a="slave.ids=slave-1@10.130.29.41:8080,slave-2@127.0.0.1:8087,slave-3@127.0.0.1:8087";
	            String [] arr= a.split(",");
	           System.out.println("打印数组："+arr.length);

	            String [] arrUrl=new String[arr.length];
	            //所有分片信息存入数组
	            for(int i=0;i<arr.length;i++){
	            	System.out.println("打印机器地址："+arr[i]);
	            	System.out.println("打印机器地址："+arr[i].split("@")[1]);
	                arrUrl[i]=arr[i].split("@")[1];
	            }
	            System.out.println(arrUrl.length);
	}

}
