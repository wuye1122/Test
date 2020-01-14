package JUC.util.concurrent;

import java.text.SimpleDateFormat;

public class TestConcurrent {

	/**
	 * @author JUC
	 * void
	 *  SimpleDateFormat线程不安全及解决办法
	 *  //1局部变量不会线程不安全：缺点每次调用会创建对象，方法结束回收对象 消耗资源
	 *  //2 static 共享的会出现线程不安全问题 (当出现共享变量的时候 A线程进去操作一半B线程进行取值)
	 *  1源码->
	 * 
	 */
	 public  static   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    public  static   String [] strArr=new String[]{"2018-05-09","2017-05-09","2018-06-09"};
	    public  static   ThreadLocal<SimpleDateFormat> local=new ThreadLocal<SimpleDateFormat>();

	    public static void main(String [] args){
	        //1	SimpleDateFormat线程安全
	           for(int j=0;j<100;j++){
	               for(int i=0;i<strArr.length;i++){
	                   final int index=i;
	                   new Thread(new Runnable() {
	                       @Override
	                       public void run() {
	                           try {

	                            //加锁synchronized
	                           // synchronized(sdf){
	                            /*while(true){*/
	                               System.out.println(Thread.currentThread().getName()+" index:"+index);
	                               System.out.println(index);
	                               //内部类访问变量  final  System.out.println(i)
	                               String str1=strArr[index];
	                               SimpleDateFormat sdf1=local.get();
	                               if(sdf1==null){
	                                   sdf1= new SimpleDateFormat("yyyy-MM-dd");
	                                   local.set(sdf1);
	                               }
	                               String str2=sdf1.format(sdf1.parse(strArr[index]));

	                            //  String str2=sdf.format(sdf.parse(strArr[index]));
	                               if(!(str1.equals(str2))){
	                                   System.out.println(str1);
	                                   System.out.println(str2);
	                                   //主动抛出异常 check or uncheck throw or throws
	                                   throw new RuntimeException(Thread.currentThread().getName()+"str1:"+str1+"不相等 str2 "+str2);
	                               }
	                           //  }
	                           /* }*/
	                           } catch (Exception e) {
	                               e.printStackTrace();
	                               throw new RuntimeException("线程不安全",e);

	                           }
	                       }
	                   }).start();

	               }
	           }

	    }

}
