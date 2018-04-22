package Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateF {

	/**
	 * @author wuhl
	 * void
	 * 
	 * һ�ָ�ʽ  (�̲߳���ȫ��) ���� ��Ҫ��ʱ����Ҫ�����µ�ʵ��new
	 * 
	 * �̲߳���ȫ��ԭ��--��״̬����״̬�����ĺô�֮һ���������ڸ��ֻ����£�
	 *                �����԰�ȫ�ĵ��á�����һ�������Ƿ�����״̬�ģ��Ϳ����Ƿ�Ķ��������Ķ�����
	 *                ����ȫ�ֱ���������ʵ�����ֶΡ�format���������й����иĶ���SimpleDateFormat��calendar�ֶΣ�
	 *                ���ԣ�������״̬�ġ�

					������Ҳͬʱ���������ڿ��������ϵͳ��ʱ��ע����һ������:
					
					����1.�Լ�д�������ʱ��Ҫ�Զ��̵߳�������µĺ����ע���������ȷ˵��
					����2.���̻߳����£���ÿһ������Ŀɱ������Ҫע�����̰߳�ȫ��
					����3.���ǵ���ͷ���������Ƶ�ʱ��Ҫ������Ƴ���״̬��  
	 * 
	 * 
	 * SimpleDateFormat�����ļ̳й�ϵ��
			Java.lang.Object
			   |
			   +----java.text.Format
			           |
			           +----java.text.DateFormat
			                   |
			                   +----java.text.SimpleDateFormat
			                   		                   
			                 
  SimpleDateFormat�����﷨��
  
  G �����־��
  y ��
  M ��
  d ��
  h ʱ ����������� (1~12)
  H ʱ ��һ���� (0~23)
  m ��
  s ��
  S ����
  E ����
  D һ���еĵڼ���
  F һ���еڼ������ڼ�
  w һ���еڼ�������
  W һ���еڼ�������
  a ���� / ���� ��Ƿ� 
  k ʱ ��һ���� (1~24)
  K ʱ ����������� (0~11)
  z ʱ��
 */
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
           SimpleDateFormat format=new SimpleDateFormat("yyyy-M-d HH:mm:ss ");//new Date().toLocaleString()
           System.out.println(format.format(new Date()));
         /*  
           System.out.println(new Date().toLocaleString());
           System.out.println(new Date().toGMTString());//CMT ��������ʱ��
           System.out.println(new Date().toString());//����  ��-��-ʱ��  �� CST ��
           System.out.println("====================");
           System.out.println(toStantTime(new Date()));
           System.out.println(new Date().getTime());*/

           
    try {
        System.out.println(format.format(new Date()));
        
        System.out.println( df.get().format(new Date()));

		/*	System.out.println(toParseTime(new String("1990/02/03 11:22:33")));
			
			System.out.println(toStantTime(toParseTime(new String("1990/02/03 11:22:33"))));*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
           
	}
    public static String toStantTime(Date d){
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	return format.format(d);
    }
    public static Date toParseTime(String str) throws ParseException{
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	return format.parse(str);   }  
    
    
    protected static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
}
