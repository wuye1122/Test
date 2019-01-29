package JUC.patten.observer;

//主题 
public interface ISubject {

	//添加订阅者
	public void addObserver(IObserver observer);
	//删除订阅者
	public void removeObserver(IObserver observer);
	//通知观察者   --> 将subject 传递给观察者
	public void updateObserver(String subject);

}
