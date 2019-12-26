package JUC.util.concurrent;

public class TestJMC {

	/**
	 * @author JUC
	 * void java内存模型 ：
	 * 
	 *  1 sychronized:加锁
	 *  2 volatile:修饰变量
	 * 
	 * 
	 */
	private static int a=0;
	private static  volatile int b=0;
	private static   int c=0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		

		for(int i=0;i<100;i++){
			new TestThread().start();
		}
		
		
		/*TestVolatileThread tv1=new TestVolatileThread();
		TestVolatileThread tv2=new TestVolatileThread();
		tv1.start();
		tv2.start();

		TestSynchronizedThread ts1=new TestSynchronizedThread();
		TestSynchronizedThread ts2=new TestSynchronizedThread();
		ts2.start();
		ts1.start();*/
	}
	
	private static class TestThread extends Thread{
			
		@Override
		public void run() {
			// TODO Auto-generated method stub
		
				System.out.println("start   "+a+" "+Thread.currentThread().getName());
				a++;
				System.out.println("end   "+a+" "+Thread.currentThread().getName());
		
		}

	}

	private static class TestVolatileThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("start volatile  "+b+" "+Thread.currentThread().getName());
			b++;
			System.out.println("end volatile  "+b+" "+Thread.currentThread().getName());

		}
		
	}
	private static class TestSynchronizedThread extends Thread{
	
	  public  synchronized void add(){
		  c++;

	  }
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("start synchronized   "+c+" "+Thread.currentThread().getName());
				add();
				System.out.println("end synchronized   "+c+" "+Thread.currentThread().getName());
			}
}


}
