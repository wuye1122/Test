package wuhl.patten.observer;


//具体观察者
public class Observer implements IObserver{

	/**
	 * @author wuhl
	 * void
	 */
    private  ISubject subject;

    private String name;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//新建观察者  将主题注入其中
	public Observer( ISubject subject,String name)
    {
	 this.name=name;
	 this.subject=subject;
      this.subject.addObserver(this);
    }

	@Override
	public void updateSubject(String msg) {
		// TODO Auto-generated method stub
		System.out.println("观察者:"+name+"观察主题："+msg);
	}

}
