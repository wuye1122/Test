package wuhl.Queue;

import java.util.concurrent.ArrayBlockingQueue;


public class Producer extends Thread {

	/**
	 * @author wuhl
	 * void
	
	 */
	//��������
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
				System.out.println("����֮ǰ ��ʱ���������У�"+pro.toString());

				Thread.sleep(100);
				pro.put("����"+i);

				System.out.println("���ɲ�Ʒ��"+i);
				i++;
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("������Ʒ�����쳣��"+e);
			}
			
		}
		
	}

}
