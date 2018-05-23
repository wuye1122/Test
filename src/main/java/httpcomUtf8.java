

/*import com.channelsoft.reusable.comobj.IDispatch;
import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.comobj.service.SLEEComponentContext;
import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.channelsoft.reusable.comobj.IDispatch;
import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.comobj.service.SLEEComponentContext;
import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;

public class httpcomUtf8
  implements IDispatch
{

	private static Logger logger = Logger.getLogger(httpcomUtf8.class);

  ComputingContext cc = null;

  SLEEComponentContext comContext = null;

  String encodeUrl(String url, String encoding)
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
    	//String skillGroup []=arr[6].split("=");
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
        
        System.out.println("转换之后的url:"+sb.toString());
       
 /*       System.out.println("arr0:"+arr[0]);
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
    	logger.error("sb:"+sb.toString());*/
    	return sb.toString();
    	
    }else{
    	logger.error("不包含技能组url:"+url.toString());

    	return URLEncoder.encode(url, encoding);

    }
    	
    }
    catch (Exception e)
    {
    	e.printStackTrace();
      this.cc.reportError(e);
    }
    return "";
  }

  public Variant getProperty(String propertyName, Variant[] args)
    throws Exception
  {
    this.cc.reportError(new NoSuchMethodException());

    return null;
  }

  boolean httpRequest(String httpRequest, int timeout, Variant returnValue, Variant errCode, String requestType, String contentType, String charSet, String resultEncoding)
  {
    if (requestType == null)
    {
      requestType = "GET";
    }

    if (contentType == null)
    {
      contentType = "text/html";
    }

    if (charSet == null)
    {
      charSet = "UFT-8";
    }

    if (resultEncoding == null)
    {
      resultEncoding = charSet;
    }

    boolean success = true;
    BufferedReader in = null;
    try
    {
      URL url = new URL(httpRequest);

      HttpURLConnection urlConn = (HttpURLConnection)url
        .openConnection();

      urlConn.setConnectTimeout(timeout * 1000);
      urlConn.setReadTimeout(timeout * 1000);

      if ((requestType == null) || (requestType.equals("")))
      {
        requestType = "Get";
      }
      urlConn.setRequestMethod(requestType.toUpperCase());
      if (requestType.toUpperCase().equals("POST"))
      {
        urlConn.setDoOutput(true);
        urlConn.setRequestProperty("Content-Length", "0");
      }
      urlConn.setRequestProperty("Content-Type", contentType);
      urlConn.setRequestProperty("CharSet", charSet);

      in = new BufferedReader(new InputStreamReader(
        urlConn.getInputStream(), resultEncoding));

      StringBuffer  result = new StringBuffer();
      String line = null;
      while ((line = in.readLine()) != null)
      {
        result.append(line + "\n");
      }

      if (returnValue != null)
      {
        returnValue.setValue(result.toString());
      }
    }
    catch (Exception e)
    {
      try
      {
        if (errCode != null)
        {
          errCode.setValue(e.toString());
        }
      }
      catch (Exception localException1)
      {
      }
      finally
      {
        success = false;
      }

      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (Exception localException2)
        {
        }
      }
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (Exception localException3)
        {
        }
      }
    }

    return success;
  }

  public Variant invoke(String method, Variant[] args)
    throws Exception
  {
    if (method.equalsIgnoreCase("HTTPRequest"))
    {
      if ((args.length < 2) || (args.length > 8))
      {
        this.cc.reportError(new InvalidParameterCountException());

        return null;
      }

      return new Variant(
        httpRequest(args[0].toString(), 
        (int)args[1].getDouble(), args.length > 2 ? args[2] : null, 
        args.length > 3 ? args[3] : null, (args.length > 4) && 
        (args[4] != null) ? args[4].toString() : null, 
        (args.length > 5) && (args[5] != null) ? 
        args[5].toString() : null, (args.length > 6) && 
        (args[6] != null) ? args[6].toString() : null, 
        (args.length > 7) && (args[7] != null) ? 
        args[7].toString() : null), this.cc);
    }
    if (method.equalsIgnoreCase("encodeurl"))
    {
      if ((args.length < 1) || (args.length > 2))
      {
        this.cc.reportError(new InvalidParameterCountException());

        return null;
      }

      return new Variant(
        encodeUrl(args[0].toString(), 
        args.length == 2 ? args[1].toString() : null), this.cc);
    }

    return getProperty(method, args);
  }

  public void setComponentContext(SLEEComponentContext comContext)
  {
    this.comContext = comContext;
  }

  public void setComputingContext(ComputingContext cc)
  {
    this.cc = cc;
    setComponentContext(cc.getSLEEComponentContext());
  }

  public void setProperty(String propertyName, Variant[] args)
    throws Exception
  {
    this.cc.reportError(new NoSuchMethodException());
  }
  
  
  void httpRequest1(String httpRequest, int timeout, String requestType, String contentType, String charSet, String resultEncoding)
  {
    if (requestType == null)
    {
      requestType = "GET";
    }

    if (contentType == null)
    {
      contentType = "text/html";
    }

    if (charSet == null)
    {
      charSet = "UFT-8";
    }

    if (resultEncoding == null)
    {
      resultEncoding = charSet;
    }

    BufferedReader in = null;
    try
    {
    	System.out.println("前httpRequest:"+httpRequest);
    	httpRequest=  encodeUrl(httpRequest,"utf-8");
        System.out.println("后httpRequest:"+httpRequest);
        URL url = new URL(httpRequest);

      HttpURLConnection urlConn = (HttpURLConnection)url
        .openConnection();

      urlConn.setConnectTimeout(timeout * 1000);
      urlConn.setReadTimeout(timeout * 1000);

      if ((requestType == null) || (requestType.equals("")))
      {
        requestType = "Get";
      }
      urlConn.setRequestMethod(requestType.toUpperCase());
      if (requestType.toUpperCase().equals("POST"))
      {
        urlConn.setDoOutput(true);
        urlConn.setRequestProperty("Content-Length", "0");
      }
      urlConn.setRequestProperty("Content-Type", contentType);
      urlConn.setRequestProperty("CharSet", charSet);

      in = new BufferedReader(new InputStreamReader(
        urlConn.getInputStream(), resultEncoding));

      StringBuffer  result = new StringBuffer();
      String line = null;
      while ((line = in.readLine()) != null)
      {
        result.append(line + "\n");
      }
      
      System.out.println("result:"+result);

    }catch(Exception e){
    	logger.debug("请求异常",e);
        System.out.println("e:"+e);
        e.printStackTrace();

    } 
 
    
  }

  public static void main(String [] args){
	  //If Obj.HTTPRequest(WebAddess, TimeOut, sResult, sDesc, ActionType, ContentType,CharSet) Then
	  String httpRequest="http://0101290306.desk.ccod.com:8080/createCCOD/missedcall?entId=0101290306&ani=1099&dnis=20180518&startTime=&endTime=&agentId=1002&groupName=阳光产险客价部公司续项目171103支持组&sessionId=4132895844212932641";
	  String httpRequest1="http://0101290306.desk.ccod.com:8080/createCCOD/missedcall?entId=0101290306&ani=1099&dnis=20180518&startTime=&endTime=&agentId=1002&groupName=23123&sessionId=4132895844212932641";
/*
	  try {
		String url1= URLEncoder.encode(httpRequest, "utf-8");
		System.out.println(url1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

	  int timeout=10;
	  String requestType = "get";
	  String contentType = "text/xml";
	  String  charSet = "utf8";
	  String resultEncoding ="utf8";
	  
	 new httpcomUtf8().httpRequest1(httpRequest, timeout,  requestType, contentType, charSet, resultEncoding);
	  
  }
}