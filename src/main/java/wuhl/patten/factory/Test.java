package wuhl.patten.factory;

public class Test {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String msg="��������";   
		
		ShapeFactory factory=new ShapeFactory();
		
		//������൱������������ ��ȡ���� ���÷��������߳�run������ ��������
		factory.getShape("apple").push(msg);
		factory.getShape("orange").push(msg);
		factory.getShape("bear").push(msg);

	}

}
