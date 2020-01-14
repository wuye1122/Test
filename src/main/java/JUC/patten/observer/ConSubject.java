package JUC.patten.observer;

import java.util.ArrayList;
import java.util.List;

public class ConSubject implements ISubject{

	/**
	 * @author JUC
	 * void
	 */
	private List<IObserver> observer;

	//��ע��������Ϣ
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
		//֪ͨ���еĹ۲���  ��������仯
		for(IObserver o:observer){
			//����֪ͨ���ж��ĸ������
			if(msg.equals("1")){
				o.updateSubject("��һ�Σ�"+msg);

			}else if(msg.equals("2")){
				o.updateSubject("�ڶ��Σ�"+msg);

			}else if(msg.equals("3")){
				o.updateSubject("�����Σ�"+msg);

			}else{
				o.updateSubject("������"+msg);

			}
		}
	}

}
