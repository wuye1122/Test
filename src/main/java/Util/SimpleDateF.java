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
	 * 一种格式  (线程不安全的) 所有 需要的时候都需要创建新的实例new
	 * 
	 * 线程不安全的原因：--无状态：无状态方法的好处之一，就是它在各种环境下，
	 *                都可以安全的调用。衡量一个方法是否是有状态的，就看它是否改动了其它的东西，
	 *                比如全局变量，比如实例的字段。format方法在运行过程中改动了SimpleDateFormat的calendar字段，
	 *                所以，它是有状态的。

					　　这也同时提醒我们在开发和设计系统的时候注意下一下三点:
					
					　　1.自己写公用类的时候，要对多线程调用情况下的后果在注释里进行明确说明
					　　2.对线程环境下，对每一个共享的可变变量都要注意其线程安全性
					　　3.我们的类和方法在做设计的时候，要尽量设计成无状态的  
	 * 
	 * 
	 * SimpleDateFormat函数的继承关系：
			Java.lang.Object
			   |
			   +----java.text.Format
			           |
			           +----java.text.DateFormat
			                   |
			                   +----java.text.SimpleDateFormat
			                   		                   
			                 
  SimpleDateFormat函数语法：
  
  G 年代标志符
  y 年
  M 月
  d 日
  h 时 在上午或下午 (1~12)
  H 时 在一天中 (0~23)
  m 分
  s 秒
  S 毫秒
  E 星期
  D 一年中的第几天
  F 一月中第几个星期几
  w 一年中第几个星期
  W 一月中第几个星期
  a 上午 / 下午 标记符 
  k 时 在一天中 (1~24)
  K 时 在上午或下午 (0~11)
  z 时区
 */
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
           SimpleDateFormat format=new SimpleDateFormat("yyyy-M-d HH:mm:ss ");//new Date().toLocaleString()
           System.out.println(format.format(new Date()));
         /*  
           System.out.println(new Date().toLocaleString());
           System.out.println(new Date().toGMTString());//CMT 格林尼治时间
           System.out.println(new Date().toString());//星期  月-日-时间  年 CST 年
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
