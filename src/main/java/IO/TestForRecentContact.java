package IO;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class TestForRecentContact {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String args[]) {
		
		
		
		
		 String omsp="http://10.130.24.134:8080/omsp/mvc/mail/resend";
       

		    for(int i=1;i<=10;i++){
                String omspParam ="";
		        if(i>=10){
                    omspParam = "date=201806"+i+"&flag=3";
                }else{
		            String j=String.valueOf("0")+String.valueOf(i);
		            omspParam = "date=201806"+j+"&flag=3";

                }
                System.out.println(omspParam);
		        System.out.println(HttpRequest.sendGet(omsp,omspParam));
        }

         
     
		// TODO Auto-generated method stub
	    // public void upload(String url,String path,String md5) 
	/*	String url = "http://10.130.29.33:8080/dps/download/add.do";
		String path = "D:\\IdeaProjects\\dps2\\src\\main\\webapp\\adapter_jar_lib/DataPushForTest1.jar";
		File file = new File(path);
		String md5 = Md5Util.getMd5ByFile(file);
		System.out.println(md5);
		  Map<String,String> postParam = new HashMap<String,String>();   
	        postParam.put("md5", md5);  
      		postParam.put("adapterName", file.getName());
		
		Map<String,Object> resultMap=new HttpClientUtil().upload(url, file, postParam);
          
		 System.out.println(resultMap);*/
     /*    String url="http://localhost:8087/dps2/initPush/add.do";
         String param="entId=111&eventType=R_DETAIL&conditions=&pushStatus=1&ddsVersion=1";
         //String param1="entId=111&eventType=R_DETAIL&conditions=&pushStatus=0&ddsVersion=1";
         String param2="entId=111&eventType=CALLSERVER_R_DETAIL&conditions=0,2&pushStatus=1&ddsVersion=0";*/

        /* 
         entId
         eventType
         conditions
         pushStatus
         ddsVersion
         url
         *
         */
         //4.5添加CALLSERVER_R_DETAIL 0
        // String param3="entId=140&eventType=NEW_R_DETAIL&conditions=0,4&pushStatus=0&ddsVersion=0&url=http://localhost:8081/dps1/apiPage/test_post_record.do";
         //4.5添加R_DETAIL 0
           
         //4.5添加R_DETAIL 0 关闭

         //4.5添加R_DETAIL 0 开启

          //4.4   4  10 11 R_DETAIL         1 
         
         
       //  String url="http://localhost:8087/dps2/initPush/add.do";
      //   String url="http://localhost/customWebservice/mvc/recentContact/queryAgent";
         //三个非空校验
         String param="";
         String param1="entId=&skillGroupName=R_DETAIL&remoteUrl=2313";
         String param2="entId=8989&skillGroupName=&remoteUrl=2313";
         String param3="entId=8989&skillGroupName=R_DETAIL&remoteUrl=";

         //无联系人
         String param4="entId=8989&skillGroupName=R_DETAIL&remoteUrl=3123";
   
         //有联系人    //查询不到坐席
         String param5="entId=dps&skillGroupName=故障报修&remoteUrl=TEL:18514787534";
        
         
         //有联系人    返回坐席id
         String param6="entId=8989&skillGroupName=故障报修&remoteUrl=BTL:45020066";
         //有联系人    坐席忙
                   
         
   /*  	 String result=HttpRequest.sendGet(url,param);
         System.out.println(result);
         String result1=HttpRequest.sendGet(url,param1);
         System.out.println(result1);
         String result2=HttpRequest.sendGet(url,param2);
         System.out.println(result2);
         String result3=HttpRequest.sendGet(url,param3);
         System.out.println(result3);*/
         String param77="entId=0101212777&remoteUri=TEL:88822248&startTime=1497542400000&endTime=1497542400000&duration=2313&sessionId=321";
         String url11="http://localhost/customWebservice/mvc/recentContact/queryAgent";
    	// String result5=HttpRequest.sendGet(url11,param77);

         String param78="entId=0101212777&remoteUri=TEL:88822248&startTime=1497542400000&endTime=1497542400000&duration=2313&sessionId=321";
         String param79="entId=0101212777&remoteUri=TEL:88822248&startTime=1497542400000&endTime=1497542400000&duration=2313&sessionId=321&ivrKeys=1,3,4,12#";

         
         String url1="http://localhost/customWebservice/mvc/queueForJRTT/addIvrInfo";
         
         
         String param7="entId=8989&skillGroupName=多渠道测试组1&remoteUri=BTL:45020036";

         String url2="http://localhost/customWebservice/mvc/queueForJRTT/queryQueue";
         
        /* String result5=HttpRequest.sendGet(url2,param7);
         
  
         System.out.println(result5);
        */
         String url7="http://localhost/customWebservice/mvc/recentContact/queryAgent";
        /* %E5%B9%BF%E4%B8%9C%E7%8F%A0%E6%B5%B7
         %B9%E3%B6%AB%D6%E9%BA%A3
         %5C71%4E1C%6CF0%5B89
         */
      //   System.out.println(HttpRequest.sendGet(url7,param7));
         String httpRequest="http://0101290306.desk.ccod.com:8080/createCCOD/missedcall";

         String param11="entId=0101290306&ani=1099&dnis=20180518&startTime=&endTime=&agentId=1002&groupName=%5C71%4E1C%6CF0%5B89&sessionId=4132895844212932641";
         
        // System.out.println(HttpRequest.sendGet(httpRequest,param11));

//         String result5=HttpRequest.sendGet(url,param5);
//         System.out.println(result5);
//         String result6=HttpRequest.sendGet(url,param6);
//         System.out.println(result6);
//         String result7=HttpRequest.sendGet(url,param7);
//         System.out.println(result7);
       
       
       /*for(int i=1;i<=7;i++){	
    	   System.out.println("param"+i);
    	   String a="param"+i;
        	 String result=HttpRequest.sendGet(url,a);
             System.out.println(a+":----"+result);
             try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }*/
      /*   String agent=null;
         String agent1="";
         String agent2=" ";

         System.out.println(StringUtils.isEmpty(agent));
         System.out.println(StringUtils.isEmpty(agent1));
         System.out.println(StringUtils.isEmpty(agent2));
         
         System.out.println(StringUtils.isBlank(agent));
         System.out.println(StringUtils.isBlank(agent1));
         System.out.println(StringUtils.isBlank(agent2));*/

     /*    // 验证类型
         private Integer validateType;
         //部门id
         private String departmentId;
         // 企业id
         private Integer enterpriseId;
         //时间戳
         private Long timestamp;
         //验证值
         private String sign;
         //选填字段 统计项吗名称
         private String fields;
        //坐席工号
         private String cnos;
         //条数
         private Integer limit;
         //偏移量
         private Integer offset;*/
        //http://localhost:9393/statistics/agent
        /* String jrttUrl="http://localhost:9393/statistics/agent";
         String parama="";
         String result=HttpRequest.sendGet(jrttUrl,parama);
         System.out.println("不传参数："+result);
         String paramb="validateType=8989&departmentId=多渠道测试组1&enterpriseId=BTL:45020036&timestamp=&sign&fields=&cnos=&limit=&offset";
         String result1=HttpRequest.sendGet(jrttUrl,paramb);
         System.out.println("正确结果："+result1);*/
         
         
     /*    //运营平台 更新数据 1发邮件 3 更新数据
         String omsp="http://10.130.24.134:8080/omsp/mvc/mail/resend";
         for(int i=12;i<27;i++){
             String omspParam="date=201804"+i+"&flag=3";
             System.out.println(omspParam);
             System.out.println(HttpRequest.sendGet(omsp,omspParam));
         }*/
  //       
  /*       String result2=HttpRequest.sendGet(jrttUrl,paramc);
         System.out.println(result2);
         String result3=HttpRequest.sendGet(jrttUrl,paramd);
         System.out.println(result3);
         HashMap<String, String> map = new HashMap<String, String>();

         System.out.println(map.isEmpty());
         System.out.println(map.size());
*/
	}

}
