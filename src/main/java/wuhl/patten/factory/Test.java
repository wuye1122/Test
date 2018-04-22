package wuhl.patten.factory;

public class Test {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String msg="正在推送";   
		
		ShapeFactory factory=new ShapeFactory();
		
		//传入的相当于适配器名称 获取该类 将该方法放入线程run方法中 进行推送
		factory.getShape("apple").push(msg);
		factory.getShape("orange").push(msg);
		factory.getShape("bear").push(msg);

	}

}
