package JUC.patten.factory;

public class ShapeFactory {

	
	public Shape getShape(String msg){
		if(msg==null){
			return null;
		}
		//��ʱ������msg  �൱����������adapter��
		if(msg.equals("apple")){
			return new AppleShape();
		}else if(msg.equals("orange")){
			return new OrangeShape();
		}else if(msg.equals("bear")){
			return new BearShape();

		}
		return null;
	}
}
