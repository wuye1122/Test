package URL;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUrl {

	/**
	 * @author wuhl
	 * void 
	 * web工程的一个目录结构
	 * url: src-->source 存放源代码的文件夹
	 * 
	 */
	private final static String FILE_PATH = System.getProperty("Test");
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		    System.out.println("java.version: "+System.getProperty("java.version"));
		    System.out.println("java.class.path: "+System.getProperty("java.class.path"));
		    System.out.println("file.separator: "+System.getProperty("file.separator"));
		//    System.out.println("line.separator: "+System.getProperty("line.separator"));
		 //   System.out.println("path.separator: "+System.getProperty("path.separator"));
		    //获取当前项目根路径几种方法：
		    //相对路径获取 relativelyPath
		    String relativelyPath=System.getProperty("user.dir");
		    System.out.println("1相对路径获取："+relativelyPath);
		    System.out.println("\\一个斜线是转义字符： "+relativelyPath+"\\save");
		    System.out.println("/一个斜线是转义字符： "+relativelyPath+"/save");

		    System.out.println("FILE_PATH："+FILE_PATH);


		 NumberFormat nf = NumberFormat.getNumberInstance();
		              nf.setMaximumFractionDigits(2);
		      		DecimalFormat df=new DecimalFormat(".##");

		  /*  for(int i=0;i<144;i++){	
		    	System.out.println("随机数"+df.format((Math.random()*3000+1)));
		    }*/
   
		    
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

		    String  starta="2017-11-21 00:00:00";
			
					try {
						int start = (int)(sdf.parse(starta).getTime()/1000);
						
						int end =start+86400;//结束时间戳
						
					/*	System.out.println("查询企业明细开始时间 时间戳"+start);
						System.out.println("查询企业明细结束时间 时间戳"+end);
						

						System.out.println("查询企业明细开始时间 时间日期"+stampToDate(start+"000"));
						System.out.println("查询企业明细结束时间 时间日期"+stampToDate(end+"000"));*/
			    
			    for(int i=start;i<end;i+=600){
			    	int end1=i+600;
					//10分钟的时间戳  转换成日期
	          /*      System.out.println("时间戳："+String.valueOf(i+"000"));
					System.out.println("时间戳结束："+String.valueOf(end1+"000"));*/
					System.out.print("时间戳对应时间日期："+stampToDate(String.valueOf(i+"000")));
					System.out.print("时间戳："+String.valueOf(end1+"000"));
                    Date d= new Date(Long.valueOf(i+"000"));
					System.out.println("时间date："+d);				
                    System.out.println(d.getMinutes());
                   
/*					System.out.print("时间戳对应时间日期："+stampToDate(String.valueOf(end1+"000")));
*/			    }
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//开始时间戳
		
		    
	}
	//将时间戳返回成日期
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

}
