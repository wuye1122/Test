package JUC.patten.observer;

public class Test {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//�½�����
		ConSubject is=new ConSubject();
		
		//�½�����
		ConSubject is1=new ConSubject();

		//���Ĺ���
		IObserver io=new Observer(is, "����");
		IObserver io1=new Observer(is,"����");
      //  is.setMsg("����һ�����仯��");
        is.setMsg("1");
    
        //���Ĺ���
        is1.addObserver(io);
        is1.addObserver(io1);
        is1.setMsg("3");
        
        
	}

}
