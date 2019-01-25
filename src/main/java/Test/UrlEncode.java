package Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncode {

	/**
	 * @author wuhl
	 * void
	 */
	
	static String encodeUrl(String url, String encoding)
			    throws Exception
			  {
			    if (encoding == null)
			    {
			      encoding = "UTF-8";
			    }

			    try
			    {
			    	//   return URLEncoder.encode(url, encoding);
			    	
			    	// http://0101290306.desk.ccod.com:8080/createCCOD/missedcall
			    	//?entId=0101290306  &  ani=1099  & dnis=20180518 &startTime= &endTime=&agentId=1002&groupName=涓枃鎶?兘缁?sessionId=4132895681943699580
			       // return URLEncoder.encode(url, encoding);
			    if(url.contains("groupName")){
			    	String [] arr=url.split("&");
			    	System.out.println(arr.length);
			    	StringBuffer sb=new StringBuffer();
			    	String skillGroup []=arr[6].split("=");
			        sb.append(arr[0]);
			        System.out.println("sb:"+sb);
			        for(int i=1;i<arr.length;i++){
			        	
			        	if(!(arr[i].contains("groupName"))){
			        		sb.append("&").append(arr[i]);
			        	}else{
			                String skillGroup1[]=arr[i].split("=");
			                sb.append("&").append(skillGroup1[0]).append("=");
			                sb.append(URLEncoder.encode(skillGroup1[1],"UTF-8"));
			        	}
			        }
			        
			     /*   System.out.println("转换之后的url:"+sb.toString());
			       
			        System.out.println("arr0:"+arr[0]);
			        System.out.println("arr1:"+arr[1]);
			        System.out.println("arr2:"+arr[2]);
			        System.out.println("arr3:"+arr[3]);
			        System.out.println("arr4:"+arr[4]);
			        System.out.println("arr5:"+arr[5]);
			        System.out.println("arr6:"+arr[6]);
			        System.out.println("arr7:"+arr[7]);

			        System.out.println("skillGroup:"+skillGroup.length);
			        System.out.println("skillGroup[0]:"+skillGroup[0]);
			        System.out.println("skillGroup[1]:"+skillGroup[1]);

			    	sb.append(arr[0]);
			    	sb.append("&");
			    	sb.append(arr[1]);
			    	sb.append("&");
			    	sb.append(arr[2]);
			    	sb.append("&");
			    	sb.append(arr[3]);
			    	sb.append("&");
			    	sb.append(arr[4]);
			    	sb.append("&");
			    	sb.append(arr[5]);
			    	sb.append("&");
			        sb.append(skillGroup[0]); 
			    	sb.append("=");
			        if(null!=skillGroup[1]){
			        	sb.append(URLEncoder.encode(skillGroup[1],"UTF-8"));
			        } 	
			    	sb.append("&");	
			    	sb.append(arr[7]);
			    	System.out.println("含技能组url:"+sb.toString());*/
			    	return sb.toString();
			    	
			    }else{
			    	System.out.println("不包含技能组url:"+url.toString());

			    	return URLEncoder.encode(url, encoding);

			    }
			    	
			    }
			    catch (Exception e)
			    {
			    	e.printStackTrace();
			    }
			    return "";
			  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 String httpRequest="http://0101290306.desk.ccod.com:8080/createCCOD/missedcall?entId=0101290306&ani=1099&dnis=20180518&startTime=&endTime=&agentId=1002&groupName=阳光产险客价部公司续项目171103支持组&sessionId=4132895844212932641";

	try {
		String url1=encodeUrl(httpRequest,"utf-8");
		System.out.println("url1:"+url1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	try {
		System.out.println(URLEncoder.encode("人工服务","UTF-8"));
		System.out.println(URLEncoder.encode("山东泰安","UTF-8"));
		System.out.println(URLEncoder.encode("河北邢台","UTF-8"));
		System.out.println(URLEncoder.encode("天津宝坻","UTF-8"));
		
		System.out.println(URLEncoder.encode("支","UTF-8"));
		System.out.println(URLEncoder.encode("人工","UTF-8"));
		System.out.println(URLEncoder.encode("阳光产险客价部公司续项目171103支持组","UTF-8"));
		int a='山';
		int b='东';
		int c='泰';
		int d='安';

		System.out.println("========小马快跑几种实现=========");
		System.out.println(URLEncoder.encode("小马快跑","gbk"));
		System.out.println(URLEncoder.encode("小马快跑","UTF-8"));
		System.out.println(URLEncoder.encode("小马快跑"));


	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
