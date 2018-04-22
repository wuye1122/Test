package wuhl.Queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueueTest {

	/**
	 * @author wuhl
	 * void
	 * 阻塞队列介绍：
	 * put:当Queue已经满了时，会进入等待，只要不被中断，
	 *     就会插入数据到队列中。会阻塞，可以响应中断。
	 * 
	 * take:进行队头消费
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayBlockingQueue<String> test=new ArrayBlockingQueue<String>(5);

		testProducerConsumer(test);
		
		System.out.println(test.toString());
		
		ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>(5);
		queue.add("1");
		queue.add("22");
		queue.add("333");
		queue.add("4444");
		queue.add("55555");
		System.out.println(queue.toString());

	/*	try {
			System.out.println(queue.take());//取出阻塞
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


	/*	try {
			queue.put("55555");//死锁 插入阻塞
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		//queue.add("6");//java.lang.IllegalStateException: Queue full
		
	}
	  /** 
     * @作用 此方法用来测试生产者和消费者 
     * 为了让程序在获取不到元素时不报错有两种方式： 
     * 1.让生产者的生产速度大于消费者的消费速度 
     * 2.在消费者获取资源出错时让消费者线程暂停一段时间，不输出错误。 
     * @param test
     * 
     * 1生产者一直生产  消费者sleep在消费  (能够看出队列里面数据) 
     *   
     * 2消费者一直消费 生成者很慢 测试take  
     */  
    public static void testProducerConsumer (ArrayBlockingQueue<String> test) {  
        Thread tConsumer = new Consumer(test);  
        Thread tProducer = new Producer(test);  
        tConsumer.start();  
        tProducer.start();  

    }  
	
	
	

}
