package JUC.patten.observer;

//���� 
public interface ISubject {

	//��Ӷ�����
	public void addObserver(IObserver observer);
	//ɾ��������
	public void removeObserver(IObserver observer);
	//֪ͨ�۲���   --> ��subject ���ݸ��۲���
	public void updateObserver(String subject);

}
