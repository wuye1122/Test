package JUC.Queue;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer extends Thread {

	/**
	 * @author JUC
	 * void
	 */
	
	//���Ѷ���
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
				System.out.println("����֮ǰ  ��ʱ���������У�"+con.toString());

			    String c=con.take();
				System.out.println("���ѵĲ�Ʒ��"+c);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���Ѳ�Ʒ�����쳣��"+e);

			}
			
		}
		
		
	}
	

}
