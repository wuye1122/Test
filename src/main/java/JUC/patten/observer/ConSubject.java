package JUC.patten.observer;

import java.util.ArrayList;
import java.util.List;

public class ConSubject implements ISubject{

	/**
	 * @author JUC
	 * void
	 */
	private List<IObserver> observer;

	//关注的主题信息
	private String msg;
	
	
	public ConSubject() {
		super();
		this.observer = new ArrayList<IObserver>();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		ChangeMes(msg);
	}
		public void ChangeMes(String msg){
			this.updateObserver(msg);
		}
	@Override
	public void addObserver(IObserver observer) {
		// TODO Auto-generated method stub
		this.observer.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		// TODO Auto-generated method stub
		if(this.observer.contains(observer)){
			this.observer.remove(observer);
		}
	}

	@Override
	public void updateObserver(String msg) {
		// TODO Auto-generated method stub
		//通知所有的观察者  情况发生变化
		for(IObserver o:observer){
			//进行通知所有订阅该主题的
			if(msg.equals("1")){
				o.updateSubject("第一次："+msg);

			}else if(msg.equals("2")){
				o.updateSubject("第二次："+msg);

			}else if(msg.equals("3")){
				o.updateSubject("第三次："+msg);

			}else{
				o.updateSubject("正常："+msg);

			}
		}
	}

}
