package wuhl.Queue;

import java.util.concurrent.ArrayBlockingQueue;


public class Producer extends Thread {

	/**
	 * @author wuhl
	 * void
	
	 */
	//生产队列
	ArrayBlockingQueue<String> pro=null;
	
	
	public Producer(ArrayBlockingQueue<String> pro) {
		super();
		this.pro = pro;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int i=0;
		while(true){
			try {
				System.out.println("生成之前 此时的阻塞队列："+pro.toString());

				Thread.sleep(100);
				pro.put("辣椒"+i);

				System.out.println("生成产品："+i);
				i++;
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("生产产品发现异常："+e);
			}
			
		}
		
	}

}
