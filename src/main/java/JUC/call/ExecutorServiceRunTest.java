package JUC.call;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceRunTest {

	/**
	 * @author JUC
	 * void
	 * 
	 * 1�������飺
	 * �Ƚ�5����������5���߳�ȥִ�У� ʣ�����������е��У� ʣ����������� 
	 * 
	 * 2ִ��˳��    [����5 ���8 ����5]
	 * 
	 * ���Ⱥ����߳��� 12345  Ȼ��������� 678910 Ȼ�������ִ�� 11 12 13 (14 15������)
	 * ����˳�� 12345 11 12 13 678910   ��14,15������
	 */
    public static BlockingQueue blockQueue = new ArrayBlockingQueue<Runnable>(10, true);


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	 /*ExecutorService executorService = new ThreadPoolExecutor(5, 10, 1,
	            TimeUnit.MINUTES, blockQueue);*/
	  ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8, 200, TimeUnit.MILLISECONDS,
              new ArrayBlockingQueue<Runnable>(5)){
		  @Override
		protected void beforeExecute(Thread t, Runnable r) {
			// TODO Auto-generated method stub
			super.beforeExecute(t, r);
			
		}
		  @Override
				protected void afterExecute(Runnable r, Throwable t) {
					// TODO Auto-generated method stub
					super.afterExecute(r, t);
				}
	  };	
	 

	 for(int i=0;i<15;i++){
		 RunnableTest myTask = new RunnableTest(i);
		 try {
			 
			 
			 if(executor.getQueue().size()==5){//�̳߳�����	 
				 System.out.println("�Ѿ�����-------======="+i);
				 System.out.println("��ʱ����������"+executor.getQueue().toString());
			 }
			/* if(executor.getQueue().size()!=5){//�̳߳�����
				 System.out.println("��ʱ����������"+executor.getQueue().toString());
                Thread.currentThread().notify();
			 }*/
			 executor.execute(myTask);
		} catch (Exception e) {
			// TODO: handle exception		
			 System.out.println("��ʱ�����������ݣ�"+i);
		}
		
		 System.out.println("�̳߳����߳���Ŀ��"+executor.getPoolSize());
		 System.out.println("�����еȴ�ִ�е�������Ŀ��"+executor.getQueue().size());
		 System.out.println("��ʱ����������"+executor.getQueue().toString());

		 System.out.println("--------------");
		 /*if(executor.getQueue().size()==5){//�̳߳�����
			 System.out.println("��ʱ����������"+executor.getQueue().toString());

		 }*/

     }
	      executor.shutdown();
	}

}
