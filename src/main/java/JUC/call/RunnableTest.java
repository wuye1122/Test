package JUC.call;


public class RunnableTest implements Runnable{

	private int taskNum;
	
	
	public RunnableTest(int taskNum) {
		super();
		this.taskNum = taskNum;
	}


	@Override
	public void run() {
	
		// TODO Auto-generated method stub
		System.out.println("****Ö´ÐÐ"+this.taskNum);
		try {
			Thread.currentThread().sleep(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}


	@Override
	public String toString() {
		return "RunnableTest [taskNum=" + taskNum + "]";
	}
	
}
