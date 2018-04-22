package wuhl.call;


public class Runner implements Runnable{

	private int taskNum;
	
	
	public Runner(int taskNum) {
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
		return "Runner [taskNum=" + taskNum + "]";
	}
	
}
