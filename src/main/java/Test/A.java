package Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

public class A {

	/**
	 * @author wuhl
	 * void
	 */
	
	 public static Long getNextDay(Integer between){
		 
	  
	  
         
	        Calendar c = Calendar.getInstance();
	     //   c.setTimeInMillis(Long.valueOf(ms + "000"));
	        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	        String curDate = s.format(c.getTime()); // 当前日期
	      //  System.out.println(curDate);
	        c.add(Calendar.DATE, between);
	        c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);	
	        System.out.println(c.getTime());	       
	        String netDay = String.valueOf(c.getTimeInMillis());
	        String nextDay=netDay.substring(0, netDay.length() - 3);
	        return Long.valueOf(nextDay+"000");
	    }
	 
	static class ResultMessage {
			private String result;
			private String desc;

			public ResultMessage(String result, String desc) {
				this.result = result;
				this.desc = desc;
			}
			public ResultMessage(){
			}

			public String getResult() {
				return result;
			}

			public void setResult(String result) {
				this.result = result;
			}

			public String getDesc() {
				return desc;
			}

			public void setDesc(String desc) {
				this.desc = desc;
			}


		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(StringUtils.isNotBlank(null));
		System.out.println(StringUtils.isNotBlank("null"));

		System.out.println((StringUtils.isNotBlank("a")&&!("null".equals("a"))));


			Map<String, A> sdDetailMap = new HashMap<String, A>();//缓存自动外拨数据
		  sdDetailMap.put("1", new A());
		  sdDetailMap.put("2", new A());
		  
            System.out.println(sdDetailMap.size());
            System.out.println(sdDetailMap.get(null));
            System.out.println(sdDetailMap.get("3"));

            if(sdDetailMap.get("2")!=null){
            	System.out.println(sdDetailMap.get("2"));
            	System.out.println(sdDetailMap.size());
            	System.out.println(sdDetailMap.remove("2"));
            	System.out.println(sdDetailMap.size());

            }
        	System.out.println("现在长度："+sdDetailMap.size());

         System.out.println(sdDetailMap.remove("1"));   
            sdDetailMap.remove("2");
        	System.out.println("现在长度："+sdDetailMap.size());
            sdDetailMap.remove("2");

        	System.out.println("=====================");

		Map<String ,String> map=new HashMap<String,String>();
		map.remove(null);
		
		int b=Integer.parseInt("0");
		System.out.println(b);
    String a="http://2023.cdesk.com:8070/createCCOD/missedcall|com.channelsoft.dps.adapter.DataPushForCCOD|5|1|4.5默认推送接口||";
	// a="|null||||"; 
    String arr[]= a.split("\\|",-1);
	 
	 System.out.println(arr.length);
	 for(int i=0;i<arr.length;i++){
		 System.out.println(arr[i]);
	 }
	 System.out.println("===========");
	 System.out.println(getNextDay(0));
	 System.out.println(getNextDay(-60));

           int i=0;   
           while(++i<50){
        	   if(i==20){
            	   System.out.println(i);
                      break;
        	   }
           }
    	   System.out.println(i);
    	   
    	   
    	   List<Object> list=new ArrayList<Object>();
    	   Map p1=new HashMap();
    	   p1.put("result", "slave-1");
    	   p1.put("desc", "success");

    	   Map p2=new HashMap();
    	   p2.put("result", "slave-2");
    	   p2.put("desc", "success");
    	   Map p3=new HashMap();
    	   p3.put("result", "slave-3");
    	   p3.put("desc", "success");
    	   list.add(p1);
    	   list.add(p2);
    	   list.add(p3);
    	   System.out.println(list);
    	   for(Object obj:list){
               String	json= JSON.toJSONString(obj);
               System.out.println(json);
              ResultMessage message = JSON.parseObject(json,ResultMessage.class);

           
           }
    	   
    	   String o1 ="";
      try {
    	  String o="2231";
    	  o1 = Long.toHexString(new Long(o.trim()));
          System.out.println("16："+o1);
        } catch (Exception e) {
	// TODO: handle exception
        	e.printStackTrace();
	
     } 
      System.out.println("01:"+o1);
    	  

	}

}
