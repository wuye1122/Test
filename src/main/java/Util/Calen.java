package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calen {

	/**
	 * @author wuhl
	 * void
	 */
	
	
/*	//��ʱ������س�����
	public static String stampToDate1(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		
//		
//		SimpleDateFormat fo=new SimpleDateFormat("yyyyMMdd");
//        Calendar ca=Calendar.getInstance();
//        System.out.println(fo.format(ca.getTime()));
//     
//        ca.add(Calendar.DAY_OF_MONTH, -1);//�����ڽ��мӼ�
//        System.out.println(fo.format(ca.getTime()));
//        System.out.println("===============");
//        
//        System.out.println(ca.get(Calendar.YEAR)+"-"+ca.get(Calendar.MONTH)+"-"+ca.get(Calendar.DAY_OF_MONTH)+"-"+ca.get(Calendar.HOUR_OF_DAY)+"-"+ca.get(Calendar.MINUTE));
//        System.out.println(ca.get(Calendar.YEAR)+"-"+ca.get(Calendar.MONTH)+"-"+ca.get(Calendar.DATE)+"-"+ca.get(Calendar.HOUR)+"-"+ca.get(Calendar.MINUTE));
//        System.out.println(ca.get(Calendar.MONTH)+1+": 0����һ�·�  --- Calendar���·ݱ�������һ��");
//        Calendar ca1=Calendar.getInstance();
//        System.out.println(ca.getTime());//�����Ѿ��� day_of_month����-1
//        System.out.println(new Date());
       
	//	System.out.println(getGlsInfoDate());
		SimpleDateFormat fo=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fo1=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

	    Calendar ca=Calendar.getInstance();
	    System.out.println("==="+ca.getTime());
        String startTime2=fo.format(ca.getTime())+" 00:00:00 ";

	    ca.add(Calendar.DATE, 1);
        String startTime1=fo.format(ca.getTime())+" 00:00:00 ";
    
      //  System.out.println("��ʼʱ��"+startTime2);
       // System.out.println("����ʱ��"+startTime1);

       /*      System.out.println(ca.getTime());
        System.out.println(fo.format(ca.getTime()));*/
    //    System.out.println(ca.getTimeInMillis()/1000);
   /*     String startTime=fo.format(ca.getTime())+" 00:00:00 ";
        String endTime=fo.format(ca.getTime())+" 23:59:59 ";*/
         try {
     //   	 System.out.println(startTime2);
      //  	 System.out.println(startTime1);

			Date start= fo1.parse(startTime2);
			Date end = fo1.parse(startTime1);
		

			     ca.add(Calendar.DATE, -30);
		        String startTime3=fo.format(ca.getTime())+" 00:00:00 ";
		    	Date start3= fo1.parse(startTime3);
			System.out.println("======"+startTime2);//1515340800000
			System.out.println("-----"+start.getTime());
			System.out.println(start);
			System.out.println(stampToDate("1515340800000"));
			
			
			System.out.println(startTime3);//1515340800000
			System.out.println(start3.getTime());
			System.out.println(start3);

		//	System.out.println(start.getTime()/1000);
		//	System.out.println(end.getTime()/1000);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
/*        System.out.println(startTime);
        System.out.println(endTime);*/
        
        int a= (int) (System.currentTimeMillis() / 1000);
       
        //System.out.println(a);
      
        int b=(int)(new Date().getTime()/1000);
        
      //  System.out.println(b);
        
        
        
        /*Calen.AA();*/

      
	}
	
	

	
	public static long getTimeMill(String time){
		
		SimpleDateFormat fo1=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		
		try {
			Date start= fo1.parse(time);
			return (long)(start.getTime()/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

		
	}

	public static String getGlsInfoDate(){
		  Calendar now = Calendar.getInstance();  

		  int year  = now.get(Calendar.YEAR);
		  
		  int month = now.get(Calendar.MONTH) +1;//1-12
		  
		  int  day  = now.get(Calendar.DAY_OF_MONTH) ;

		  if(day==1){
			  if(month==1){
				  year=year-1; 
				  month=12;
			  }else{
				month=month-1;
			  }
			  
		  }
		  String mon = String.valueOf((month < 10 ? ("0"+month) : month)); 
				    
		return year+""+mon;
		
	}

	
	public static void AA(){
		
		       System.out.println(System.currentTimeMillis());
				for(int i=1510675200;i<=1510761600;i+=600){
		     	System.out.println(i);
		        System.out.println(stampToDate(String.valueOf(i+"000")));
				}
	}
	
	
	   public static String stampToDate(String s){
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        long lt = new Long(s);
	        Date date = new Date(lt);
	        res = simpleDateFormat.format(date);
	        return res;
	    }
}
