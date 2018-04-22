package wuhl.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class TestGetRecentContact {

	/**
	 * @author wuhl
	 * void
	 */
	//上次联系人  根据最近联系人和坐席状态 进行路由到坐席
	public String getState(List<MongoRecentAgentInfo> list,HashMap<String,Long> map){
		List<String> list1=new ArrayList<String>();

		List<String> list2=new ArrayList<String>();
		 
		String agentId="";
		System.out.println(list.size());
		System.out.println(map.size());

		//有最近联系人
		if(list.size()!=0){
			for(MongoRecentAgentInfo po:list){
				if(Integer.valueOf(po.getScore())<=3){
					list1.add(po.getAgentId());
				}else{
					list2.add(po.getAgentId());
				}
			}
			
			System.out.println(list1.size());
			System.out.println(list2.size());
			System.out.println("满意的："+list1.toString());
			System.out.println("不满意的："+list2.toString());

			//先找满意的 
			if(list1.size()!=0){
				if(map.size()!=0){
					System.out.println("list1.size!=0  map.size!=0 ======");
					System.out.println("dsa:"+map.toString());

					for(String l:list1){
						if(map.get(l)!=null){
						 agentId=l;//获取未联系过的联系人
						 break;
						}
					}					
				}else{
					System.out.println("list1.size!=0  map.size=0 ======");

					return "坐席忙请等待！";
				//	System.out.println("坐席忙请等待！");
				}
			
			}
			
			if(StringUtils.isNotEmpty(agentId)){
				System.out.println("返回满意最近联系人坐席id:"+agentId);
				return agentId;

			}else{
				if(map.size()!=0){
					Set<Entry<String, Long>> set=map.entrySet();
					for(Entry<String, Long> s:set){
					  if(!list2.contains(s.getKey())){
						  agentId=s.getKey();//获取未联系过的联系人
						  break;
					  }	  
					}
					//获取一个联系
					if(StringUtils.isNotEmpty(agentId)){
						System.out.println("返回满意未联系过坐席id:"+agentId);
						return agentId;

					}else{
						// System.out.println(map.keySet());
						 Set<String> s=	map.keySet();
						 Object[] o=s.toArray();

						System.out.println("随机返回一个空闲坐席:"+o[0]);  	
						return o[0].toString();

					}
									
				}else{
					return "坐席忙请等待！";

				}
			}
	
		}else{
			if(map.size()!=0){
				// System.out.println(map.keySet());
				 Set<String> s=	map.keySet();
				 Object[] o=s.toArray();
				System.out.println("没有最近联系人随机返回"+o[0]);  	
				return o[0].toString();

			}else{
				System.out.println("坐席忙请等待！");
				return "坐席忙请等待！";

			}
				
		}
		
	}

	//test IVR获取agentId	
	public void testIvrAgent(){
		long start = System.currentTimeMillis();
		//String param7="entId=8989&skillGroupName=多渠道测试组1&remoteUrl=BTL:45020036";

				String ent="0101212777";
				String skill="1000006750";
				//查询最近联系人
				Test mongodb=new Test();
				List<MongoRecentAgentInfo> list= mongodb.getMongoRecentCon(ent,"recent_contacts","TEL:88822248","故障报修");
			
				
				//查询坐席状态
				ZsetRedisHelper redis=new ZsetRedisHelper();
				HashMap<String,Long> map=redis.getAgentStatus(ent, skill);

				TestGetRecentContact test=new TestGetRecentContact();
		         String s= test.getState(list, map);	

		       System.out.println("查询坐席状态总时间:(" + (System.currentTimeMillis() - start)
						+ ")ms");
	}
	
	public void getDateBy5(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

	    String  starta="2017-11-21 00:02:00";
		
				try {
					int start = (int)(sdf.parse(starta).getTime()/1000);
					
					int end =start+86400;//结束时间戳
					
				/*	System.out.println("查询企业明细开始时间 时间戳"+start);
					System.out.println("查询企业明细结束时间 时间戳"+end);
					

					System.out.println("查询企业明细开始时间 时间日期"+stampToDate(start+"000"));
					System.out.println("查询企业明细结束时间 时间日期"+stampToDate(end+"000"));*/
		    
		    for(int i=start;i<end;i+=600){
		    	int end1=i+600;
		
				System.out.print("时间戳对应时间日期："+stampToDate(String.valueOf(i+"000")));
				System.out.print("时间戳："+String.valueOf(end1+"000"));
                Date d= new Date(Long.valueOf(i+"000"));
				System.out.println("时间date："+d);				
/*                System.out.println(d.getMinutes());
*/                Calendar calendar = Calendar.getInstance();
              //  calendar.setTime(d);
                long l=Long.valueOf(i+"000");
       
/*					System.out.print("时间戳对应时间日期："+stampToDate(String.valueOf(end1+"000")));
*/			    }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//开始时间戳
	
	}
	
	//将时间戳转换时间
	public static String stampToDate(String s){
			String res;
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long lt = new Long(s);
			Date date = new Date(lt);
			res = simpleDateFormat.format(date);
			return res;
		}
		
	//获取key失效时间戳 5 10分钟失效
	public void getExpire(String ms){
		Calendar c = Calendar.getInstance();
		// c.setTimeInMillis(Long.valueOf("1513821096000"));
		c.setTimeInMillis(Long.valueOf(ms + "000"));
		// System.out.println("======="+c.getTimeInMillis());
		// System.out.println(c.getTime());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int i = c.get(Calendar.MINUTE);
		int addMinBy5 = 5 - i % 5;
		int addMiNBy10 = 10 - i % 10;
		// System.out.println("增加的5分钟："+addMinBy5+"增加的时间"+addMinBy5*60);
		// System.out.println("增加的10分钟："+addMiNBy10+"增加的时间"+addMiNBy10*60);

		Long by5 = (Long.valueOf(ms) + addMinBy5 * 60);
		Long by10 = (Long.valueOf(ms) + addMiNBy10 * 60);

		// System.out.println(by5);
		// System.out.println(by10);

		/*
		 * int j=i%5==0?i:i+(5-i%5); System.out.println("=="+j); int
		 * z=i%10==0?i:i+(10-i%10); System.out.println("--"+z);
		 */
		/*
		 * for(int i=1;i<=59;i++){ int j=i%5==0?i:i+(5-i%5);
		 * System.out.println("=="+j); int z=i%10==0?i:i+(10-i%10);
		 * System.out.println("--"+z);
		 * 
		 * 
		 * }
		 */
		// 获取年
		int year = c.get(Calendar.YEAR);
		// 获取月份，0表示1月份
		int month = c.get(Calendar.MONTH) + 1;
		// 获取当前天数
		int day = c.get(Calendar.DAY_OF_MONTH);
		// 获取本月最小天数
		int first = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 获取本月最大天数
		int last = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 获取当前小时
		int time = c.get(Calendar.HOUR_OF_DAY);
		// 获取当前分钟
		int min = c.get(Calendar.MINUTE);
		// 获取当前秒
		int sec = c.get(Calendar.SECOND);

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String curDate = s.format(c.getTime()); // 当前日期
		// System.out.println("当前时间：" + year + "-" + month + "-" + day + " " +
		// time + ":" + min + ":" + sec);
		System.out.println(curDate);
		System.out.println(String.valueOf(by5));
		System.out.println(String.valueOf(by10));

		System.out.println(stampToDate(String.valueOf(by5) + "000"));
		System.out.println(stampToDate(String.valueOf(by10) + "000"));

	}
	
	//获取key 10分钟失效
	public String getTenExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));
		int i = c.get(Calendar.MINUTE);
		int addMiNBy10 = 10 - i % 10;
		// System.out.println("增加的10分钟："+addMiNBy10+"增加的时间"+addMiNBy10*60);

		Long by10 = (Long.valueOf(ms) + addMiNBy10 * 60);

		// System.out.println(by10);


		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String curDate = s.format(c.getTime()); // 当前日期
	
		System.out.println(curDate);
		System.out.println(String.valueOf(by10));

		System.out.println(stampToDate(String.valueOf(by10) + "000"));
		return String.valueOf(by10);

	}
	
	//获取key 5分钟失效
	public String getFiveFExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));
		int i = c.get(Calendar.MINUTE);
		int addMinBy5 = 5 - i % 5;
		// System.out.println("增加的5分钟："+addMinBy5+"增加的时间"+addMinBy5*60);

		Long by5 = (Long.valueOf(ms) + addMinBy5 * 60);

		// System.out.println(by5);

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String curDate = s.format(c.getTime()); // 当前日期

		System.out.println(curDate);
		System.out.println(String.valueOf(by5));
		System.out.println(stampToDate(String.valueOf(by5) + "000"));
		return String.valueOf(by5);

	}	
	
	//获取key 当天失效
	public String getDayExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		String curDate = s.format(c.getTime()); // 当前日期
		System.out.println(curDate);
		c.add(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		System.out.println(s.format(c.getTime()));
		String netDay = String.valueOf(c.getTimeInMillis());

		String nextDay=netDay.substring(0, netDay.length() - 3);
		return nextDay;

	}	
	
	//获取key 当月失效
	public String getMonthExpire(String ms){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.valueOf(ms + "000"));

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		String curDate = s.format(c.getTime()); // 当前日期
		System.out.println(curDate);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		System.out.println(s.format(c.getTime()));
		String netDay = String.valueOf(c.getTimeInMillis());
		System.out.println(netDay.substring(0, netDay.length() - 3));
		String nextMonth=netDay.substring(0, netDay.length() - 3);
		return nextMonth;

	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//test IVR获取agentId
	//  new TestGetRecentContact().testIvrAgent();
		int a=5985;
		System.out.println(a%5);
		System.out.println(a/50);

		//今日头条 限制呼叫
		//new ZsetRedisHelper().limitCall();
		

		//今日头条 限制呼叫 时间戳对应时间
	//	new TestGetRecentContact().getDateBy5();
		
		//获取key的失效时间：下一天
	/*	String ms="1513836662";
		new TestGetRecentContact().getDayExpire(ms);
        System.out.println("===============");
		
		//获取key的失效时间： 5 10分钟
		String ms5="1513836662";
		new TestGetRecentContact().getFiveFExpire(ms5);
        System.out.println("===============");

       //获取key的失效时间： 5 10分钟
		String ms10 = "1513836662";
		System.out.println(new TestGetRecentContact().getTenExpire(ms10));
		System.out.println("===============");
              
        //获取key的失效时间： 下一个月
        String ms6="1508566262";
		new TestGetRecentContact().getMonthExpire(ms6);
        System.out.println("===============");*/
	
        
        /*	int start=1513836002;
		int end=1513839600;
		for(int i=start;i<=end;i=i+60){
			new TestGetRecentContact().getExpire(String.valueOf(i));

		}*/
	    

	}

}
