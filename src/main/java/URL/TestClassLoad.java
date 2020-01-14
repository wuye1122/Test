package URL;

//类加载机制
public class TestClassLoad {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*	好了，自此我们已经知道了
		 * BootstrapClassLoader、
		 * ExtClassLoader、
		 * AppClassLoader实际是查阅相应的环境属性
		 * 
		 *  sun.boot.class.path
		 *  java.ext.dirs
		 *  java.class.path来加载资源文件的。
		 *	
		 *
		 */
		
		ClassLoader test =	TestClassLoad.class.getClassLoader();
		System.out.println("TestClassLoad ClassLoader"+test);
		System.out.println("TestClassLoad ParentClassLoader "+test.getParent());
		System.out.println("TestClassLoad ParentClassLoader's parent "+test.getParent().getParent());

		System.out.println("--------------");
		System.out.println(5%10);
		System.out.println(5/10);
			System.out.println("BootstrapClassLoader： "+System.getProperty("sun.boot.class.path"));
			System.out.println("ExtClassLoader： "+System.getProperty("java.ext.dirs"));
			System.out.println("AppClassLoader： "+System.getProperty("java.class.path"));
			System.out.println("--------------");
	}

}
