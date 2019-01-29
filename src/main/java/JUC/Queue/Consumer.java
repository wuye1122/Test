package JUC.Queue;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer extends Thread {

	/**
	 * @author JUC
	 * void
	 */
	
	//消费队列
	ArrayBlockingQueue<String> con=null;
	
	
	public Consumer(ArrayBlockingQueue<String> con) {
		super();
		this.con = con;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(5000);
				System.out.println("消费之前  此时的阻塞队列："+con.toString());

			    String c=con.take();
				System.out.println("消费的产品是"+c);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("消费产品发现异常："+e);

			}
			
		}
		
		
	}
	

}
