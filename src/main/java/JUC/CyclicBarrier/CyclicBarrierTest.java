package JUC.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		 //�����������Ϊ4����������ֻ������3��ѡ�֣�����Զ�ȴ���ȥ  
        //Waits until all parties have invoked await on this barrier.   
        CyclicBarrier barrier = new CyclicBarrier(3);  
  
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        executor.submit(new Thread(new Runner(barrier, "1��ѡ��")));  
        executor.submit(new Thread(new Runner(barrier, "2��ѡ��")));  
        executor.submit(new Thread(new Runner(barrier, "3��ѡ��")));  
  
        executor.shutdown();  
    } 

	}


