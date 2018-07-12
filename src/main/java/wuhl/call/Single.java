package wuhl.call;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Single {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Map<Integer,ExecutorService> map =new HashMap<Integer,ExecutorService>();
		String str = new BigInteger("5c5b2eff96174211", 16).toString(10);
		System.out.println("str:"+str);
		str=str.substring(str.length()-2,str.length());
		System.out.println("str:"+str);
		String str1 = new BigInteger("5c5b2eff9617422", 16).toString(10);
		System.out.println("str1:"+str1);
		str1=str1.substring(str1.length()-2,str1.length());
		System.out.println("str1:"+str1);

		String str2 = new BigInteger("a5b44946c037061", 16).toString(10);
		System.out.println("str2:"+str2);
		str2=str2.substring(str2.length()-2,str2.length());
		System.out.println("str2:"+str2);
		System.out.println(Integer.valueOf(str)/10);
		System.out.println(Integer.valueOf(str1)/10);
		System.out.println(Integer.valueOf(str2)/10);

	/*	for(int i=0;i<10;i++){
			ExecutorService executorService = Executors.newFixedThreadPool(1);
              map.put(i,executorService);
		}*/

	/*	for(int i=0;i<100;i++){
			//System.out.println(i/10);
			String str4 = new BigInteger("a5b44902e036fa2", 16).toString(10);
			System.out.println("str:"+str);
			int parseInt = Integer.parseInt(str);
			System.out.println(parseInt);
			String hexString = Integer.toHexString(parseInt);


			System.out.println(hexString);
        //	map.get(i/10).submit(new dps(i));

		}
*/
	}

	static class dps implements Runnable{
        public int  i;
		dps(int i){
			this.i=i;
		}
		@Override
		public void run() {
			System.out.println("第【"+i+"】个"+Thread.currentThread().getName() + "线程被调用了。");
		}
	}

}
