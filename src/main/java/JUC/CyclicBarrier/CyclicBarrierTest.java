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
		
		
		 //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去  
        //Waits until all parties have invoked await on this barrier.   
        CyclicBarrier barrier = new CyclicBarrier(3);  
  
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        executor.submit(new Thread(new Runner(barrier, "1号选手")));  
        executor.submit(new Thread(new Runner(barrier, "2号选手")));  
        executor.submit(new Thread(new Runner(barrier, "3号选手")));  
  
        executor.shutdown();  
    } 

	}


