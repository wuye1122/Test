package JUC.redis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class DpsTest {

	/**
	 * @author JUC
	 * void
	 * 
	 * 测试dps mongodb  redis
	 * 
	 */
	
	
	public void getMongoRecentCon(String entid,String startT,String endT){
		  //开始时间
	    long s = System.currentTimeMillis();
		MongoDatabase db = MongoUtil.getDB("dps");
		MongoCollection<Document> collection = db.getCollection("DPS_LOG");
	
		try {
			BasicDBObject queryBson = new BasicDBObject();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar ca=Calendar.getInstance();
			String startTime=sdf1.format(ca.getTime())+" 00:00:00 ";
			ca.add(Calendar.DATE, 1);
			String endTime=sdf1.format(ca.getTime())+" 00:00:00 ";
			String start="";
			String end = "";
			//默认查询当天企业数据
			if(StringUtils.isNotBlank(startT)&&StringUtils.isNotBlank(endT)){

				//如果不传入时间默认查询的是当天数据
				start=(sdf.parse(startT).getTime())+"";
				end=(sdf.parse(endT).getTime())+"";
			}else{
				//如果传入开始结束时间  需要查询期间的所有天数
				start=(sdf.parse(startTime).getTime())+"";
				end=(sdf.parse(endTime).getTime())+"";
			}

			//match

			BasicDBObject cond = new BasicDBObject();
			//查询每天企业的推送详细
			queryBson.append("PUSH_TIME", new BasicDBObject(QueryOperators.GTE,start).append(QueryOperators.LTE,end));

			if(StringUtils.isNotBlank(entid)){

				queryBson.put("ENT_ID",entid);
			}
			if(StringUtils.isNotBlank(entid)){
				queryBson.put("IS_FAIL",entid);

			}
			BasicDBObject match = new BasicDBObject("$match", queryBson);
			//group

			BasicDBObject skip = new BasicDBObject("$skip", 0);
			BasicDBObject limit = new BasicDBObject("$limit", 20);

			//project
			BasicDBObject projectFields = new BasicDBObject( "_id",0);
			projectFields.put("pushDuration","$PUSH_DURATION");

			projectFields.put("entId","$ENT_ID");
			projectFields.put("pushTime","$PUSH_TIME");
	        //projectFields.put("pushDuration","NumberLong('PUSH_DURATION')");
			projectFields.put("pushUrl","$PUSH_URL");
			projectFields.put("isFail","$IS_FAIL");
			projectFields.put("pushParam","$PUSH_PARAM");
			projectFields.put("classInfo","$CLASS_INFO");


			BasicDBObject project = new BasicDBObject("$project", projectFields);
			List<BasicDBObject> condList= new ArrayList<BasicDBObject>();
			condList.add(match);
			//传入参数不为空 进行分页
			if(StringUtils.isNotBlank("0")&&StringUtils.isNotBlank("20")){
				condList.add(skip);
				condList.add(limit);
			}

			condList.add(project);


			//查询出当天企业的 成功数
			MongoCursor<Document> result= collection.aggregate(condList).iterator();

			while(result.hasNext()){
				Document ac = (Document) result.next();
				System.out.println(ac);
				String json = ac.toJson();
				System.out.println(json);
				
				
			}	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
			
		
		}
	

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		// TODO Auto-generated method stub

	//	new DpsTest().getMongoRecentCon("", "", "");
	/*	Date date=new Date();
		System.out.println(sdf1.format(date));
		Integer a=0;
		Integer b=1999;
		Integer c=-12;
		Integer d=null;
		System.out.println(c>Integer.MAX_VALUE);
		System.out.println(a<Integer.MAX_VALUE&&a>=0);
		String str=String.format("%b",a);
		System.out.println(str);

		System.out.println(isPureDigital(a.toString()));;
		System.out.println(isPureDigital(b.toString()));;
		System.out.println(isPureDigital(c.toString()));;
		System.out.println(isPureDigital(String.valueOf(d)));;
		String aa="2313;dsada;";
		String bb="2313,dsada;";
		System.out.println(System.currentTimeMillis());
		
		
		System.out.println();
		System.out.println(String.valueOf(System.currentTimeMillis()).subSequence(0, 10));
		System.out.println(String.valueOf(System.currentTimeMillis()).subSequence(0, 10));

           int ab= Integer.valueOf(String.valueOf(System.currentTimeMillis()).subSequence(0, 10).toString());
   		System.out.println(ab-1800);
   		String ca="1522495000";
   		
      System.out.println(new DpsTest().getDayExpire("1522495000"));
      System.out.println(new DpsTest().getDayExpire("1522495000")-86400);
    
      List<String>lis=new ArrayList<String>(); 
      lis.add("23");
      lis.add("3");
    System.out.println(lis.size()==0||lis.contains("3"));*/  
		/*
		List<AgentStateDetailPo> list=new ArrayList<AgentStateDetailPo>();
		AgentStateDetailPo po=new AgentStateDetailPo();
		po.setAgentId("2313");
		AgentStateDetailPo po2=new AgentStateDetailPo();
		po2.setAgentId("231");
		list.add(po);
		list.add(po2);
		System.out.println(JSON.toJSONString(list));

		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(AgentStateDetailPo.class, "is_handled" );
		System.out.println(JSON.toJSONString(list,filter));*/

//		System.out.println(new DpsTest().getNextDay("1522598399",0));
		
		String path="/root/resin-4.0.13/webapps/dps/adapter_jar_lib/Plugin3.jar";
	     
		System.out.println(path.split("/").length);
		System.out.println(path.split("/")[path.split("/").length-2]);
		System.out.println(path.split("/")[path.split("/").length-1]);
		System.out.println(path.split("/"));
	}
	
	
	//获取key 当天失效
	public Long getTodayExpire(){
		Calendar c = Calendar.getInstance();
		//c.setTimeInMillis(Long.valueOf(ms + "000"));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = s.format(c.getTime()); // 当前日期
		System.out.println(curDate);
		c.add(Calendar.DATE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
	//	System.out.println(s.format(c.getTime()));
		return c.getTimeInMillis();
	}
	
	
	//获取key 当天失效
	public static Long getNextDay(String ms,Integer between){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(ms + "000"));
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = s.format(c.getTime()); // 当前日期
        System.out.println(curDate);
        c.add(Calendar.DATE, between);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        String netDay = String.valueOf(c.getTimeInMillis());
        // String nextDay=netDay.substring(0, netDay.length() - 3);
        return Long.valueOf(netDay);
    }
	public static boolean isPureDigital(String string) {
        if (StringUtils.isBlank(string))
            return false;
        String regEx1 = "\\d+";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

}
