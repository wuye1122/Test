package JUC.patten.factory;

public class ShapeFactory {

	
	public Shape getShape(String msg){
		if(msg==null){
			return null;
		}
		//此时传进来msg  相当于适配器（adapter）
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
