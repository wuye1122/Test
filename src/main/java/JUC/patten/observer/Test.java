package JUC.patten.observer;

public class Test {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//新建主题
		ConSubject is=new ConSubject();
		
		//新建主题
		ConSubject is1=new ConSubject();

		//订阅过程
		IObserver io=new Observer(is, "张三");
		IObserver io1=new Observer(is,"李四");
      //  is.setMsg("主题一发生变化：");
        is.setMsg("1");
    
        //订阅过程
        is1.addObserver(io);
        is1.addObserver(io1);
        is1.setMsg("3");
        
        
	}

}
