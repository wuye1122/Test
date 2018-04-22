package wuhl.Queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueueTest {

	/**
	 * @author wuhl
	 * void
	 * �������н��ܣ�
	 * put:��Queue�Ѿ�����ʱ�������ȴ���ֻҪ�����жϣ�
	 *     �ͻ�������ݵ������С���������������Ӧ�жϡ�
	 * 
	 * take:���ж�ͷ����
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
			System.out.println(queue.take());//ȡ������
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


	/*	try {
			queue.put("55555");//���� ��������
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		//queue.add("6");//java.lang.IllegalStateException: Queue full
		
	}
	  /** 
     * @���� �˷����������������ߺ������� 
     * Ϊ���ó����ڻ�ȡ����Ԫ��ʱ�����������ַ�ʽ�� 
     * 1.�������ߵ������ٶȴ��������ߵ������ٶ� 
     * 2.�������߻�ȡ��Դ����ʱ���������߳���ͣһ��ʱ�䣬��������� 
     * @param test
     * 
     * 1������һֱ����  ������sleep������  (�ܹ�����������������) 
     *   
     * 2������һֱ���� �����ߺ��� ����take  
     */  
    public static void testProducerConsumer (ArrayBlockingQueue<String> test) {  
        Thread tConsumer = new Consumer(test);  
        Thread tProducer = new Producer(test);  
        tConsumer.start();  
        tProducer.start();  

    }  
	
	
	

}
