package JUC.util.concurrent;

import java.text.SimpleDateFormat;

public class TestConcurrent {

	/**
	 * @author JUC
	 * void
	 *  SimpleDateFormat�̲߳���ȫ������취
	 *  //1�ֲ����������̲߳���ȫ��ȱ��ÿ�ε��ûᴴ�����󣬷����������ն��� ������Դ
	 *  //2 static ����Ļ�����̲߳���ȫ���� (�����ֹ��������ʱ�� A�߳̽�ȥ����һ��B�߳̽���ȡֵ)
	 *  1Դ��->
	 * 
	 */
	 public  static   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    public  static   String [] strArr=new String[]{"2018-05-09","2017-05-09","2018-06-09"};
	    public  static   ThreadLocal<SimpleDateFormat> local=new ThreadLocal<SimpleDateFormat>();

	    public static void main(String [] args){
	        //1	SimpleDateFormat�̰߳�ȫ
	           for(int j=0;j<100;j++){
	               for(int i=0;i<strArr.length;i++){
	                   final int index=i;
	                   new Thread(new Runnable() {
	                       @Override
	                       public void run() {
	                           try {

	                            //����synchronized
	                           // synchronized(sdf){
	                            /*while(true){*/
	                               System.out.println(Thread.currentThread().getName()+" index:"+index);
	                               System.out.println(index);
	                               //�ڲ�����ʱ���  final  System.out.println(i)
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
	                                   //�����׳��쳣 check or uncheck throw or throws
	                                   throw new RuntimeException(Thread.currentThread().getName()+"str1:"+str1+"����� str2 "+str2);
	                               }
	                           //  }
	                           /* }*/
	                           } catch (Exception e) {
	                               e.printStackTrace();
	                               throw new RuntimeException("�̲߳���ȫ",e);

	                           }
	                       }
	                   }).start();

	               }
	           }

	    }

}
