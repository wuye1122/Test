package JUC.CyclicBarrier;

import java.lang.reflect.Method;

public class Test33 {

	/**
	 * @author JUC
	 * void
	 */
	public void show(){
		System.out.println("this is Test3 class");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class c=Class.forName("JUC.CyclicBarrier.Test33");
		    Object o=c.newInstance();
		    Method m=c.getMethod("show");
		    m.invoke(o);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
