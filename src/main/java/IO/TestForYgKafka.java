package IO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class TestForYgKafka {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String args[]) {
	
         String url="http://localhost:8087/dps2/push/pushDetail.do";
         String param1="entId=0102170721";
         String param2="entId=0000000123";
         
       /*  String result1=HttpRequest.sendGet(url,param1);
         System.out.println("���͵���0123"+result1); 
         String result2=HttpRequest.sendGet(url,param2);
         System.out.println("���͵���0123"+result2); */

   /*    for(int i=0;i<50;i++){
        	   if(i%2==0){
        	         String result1=HttpRequest.sendGet(url,param1);
                       System.out.println(i+"���͵���0721"+result1);
        	         
        	   }else{
        	         String result2=HttpRequest.sendGet(url,param2);
                     System.out.println(i+"���͵���0123"+result2);


        	   }
           }*/

         //����ʱ�����Ӧת��
         System.out.println(new TestForYgKafka().stampToDate("1520914244899"));
         System.out.println(new TestForYgKafka().stampToDate("1520914368433"));
         System.out.println(new TestForYgKafka().stampToDate("1520582709112"));
         System.out.println(new TestForYgKafka().stampToDate("1520743343446"));
         
       new TestForYgKafka().test("2018-03-11 12:42:23", "2018-03-11 12:42:33");
         
       
         int a=-1;
         System.out.println(StringUtils.isNotBlank("current")&&StringUtils.isNotBlank("pageSize"));
         System.out.println(a);
        
	}

	public void  test(String StartTime,String  EndTime){
		try {
			
	                                           //		yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	 		Calendar ca=Calendar.getInstance();
	 		String startTime=sdf1.format(ca.getTime())+" 00:00:00 ";
	 		ca.add(Calendar.DATE, 1);
	 		String endTime=sdf1.format(ca.getTime())+" 00:00:00 ";
	 		String start="";
	 		String end = "";
	 		//Ĭ�ϲ�ѯ������ҵ����
	 		if(StringUtils.isNotBlank(StartTime)&&StringUtils.isNotBlank(EndTime)){

	 			//���������ʱ��Ĭ�ϲ�ѯ���ǵ�������
	 			start=(sdf.parse(StartTime).getTime())+"";
	 			end=(sdf.parse(EndTime).getTime())+"";
	 		}else{
	 			//������뿪ʼ����ʱ��  ��Ҫ��ѯ�ڼ����������
	 			start=(sdf.parse(startTime).getTime())+"";
	 			end=(sdf.parse(endTime).getTime())+"";
	 		}
	 		
	 		System.out.println("��ʼʱ�䣺"+start);
	 		System.out.println("����ʱ�䣺"+end);

		} catch (Exception e) {
			// TODO: handle exception
		} 
	}


	//��ʱ������س�����
		public static String stampToDate(String s){
			String res;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long lt = new Long(s);
			Date date = new Date(lt);
			res = simpleDateFormat.format(date);
			return res;
		}

}
