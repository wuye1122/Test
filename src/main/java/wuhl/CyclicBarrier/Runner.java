package wuhl.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Runner implements Runnable {

	/**
	 * @author wuhl
	 * void
	 */
	 // һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ� (common barrier point)  
    private CyclicBarrier barrier;  
  
    private String name;  
  
    public Runner(CyclicBarrier barrier, String name) {  
        super();  
        this.barrier = barrier;  
        this.name = name;  
    }  
  
    @Override  
    public void run() {  
        try {  
            Thread.sleep(1000 * (new Random()).nextInt(8));  
            System.out.println(name + " ׼������...");  
            // barrier��await�����������в����߶��Ѿ��ڴ� barrier �ϵ��� await ����֮ǰ����һֱ�ȴ���  
            barrier.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } catch (BrokenBarrierException e) {  
            e.printStackTrace();  
        }  
        System.out.println(name + " ���ܣ�");  
    }  
	

}
