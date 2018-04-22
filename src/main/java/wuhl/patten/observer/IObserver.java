package wuhl.patten.observer;


//观察者 订阅
public interface IObserver {
	
	// 接到主题更新信息     根据实际观察者需要进行通知
	public void updateSubject(String msg);

}
