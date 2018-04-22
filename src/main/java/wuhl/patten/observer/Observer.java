package wuhl.patten.observer;


//����۲���
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

	//�½��۲���  ������ע������
	public Observer( ISubject subject,String name)
    {
	 this.name=name;
	 this.subject=subject;
      this.subject.addObserver(this);
    }

	@Override
	public void updateSubject(String msg) {
		// TODO Auto-generated method stub
		System.out.println("�۲���:"+name+"�۲����⣺"+msg);
	}

}
