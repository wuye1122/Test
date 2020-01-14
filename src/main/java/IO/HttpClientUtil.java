package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;




public class HttpClientUtil {

	private static Log logger = LogFactory
			.getLog(HttpClientUtil.class);
	/** 
     * post��ʽ�ύ����ģ���û���¼���� 
     */  
    public void postForm() {  
        // ����Ĭ�ϵ�httpClientʵ��.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // ����httppost    
        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");  
        // ������������    
        List formparams = new ArrayList();  
        formparams.add(new BasicNameValuePair("username", "admin"));  
        formparams.add(new BasicNameValuePair("password", "123456"));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // �ر�����,�ͷ���Դ    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * ���� post������ʱ���Ӧ�ò����ݴ��ݲ�����ͬ���ز�ͬ��� 
     */  
    public void post() {  
        // ����Ĭ�ϵ�httpClientʵ��.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // ����httppost    
        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");  
        // ������������    
        List formparams = new ArrayList();  
        formparams.add(new BasicNameValuePair("type", "house"));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // �ر�����,�ͷ���Դ    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * ���� get���� 
     */  
    public void get() {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // ����httpget.    
            HttpGet httpget = new HttpGet("http://www.baidu.com/");  
            System.out.println("executing request " + httpget.getURI());  
            // ִ��get����.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // ��ȡ��Ӧʵ��    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // ��ӡ��Ӧ״̬    
                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    // ��ӡ��Ӧ���ݳ���    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // ��ӡ��Ӧ����    
                    System.out.println("Response content: " + EntityUtils.toString(entity));  
                }  
                System.out.println("------------------------------------");  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // �ر�����,�ͷ���Դ    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * �ϴ��ļ� 
     */  
    public Map<String,Object> upload(String url,File file,Map<String,String> postParam) {  
    	  Map<String,Object> resultMap = new HashMap<String,Object>();  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
           // HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");  
           // HttpPost httppost = new HttpPost("http://localhost:8087/dps2/adapter/add.do");  
              HttpPost httppost = new HttpPost(url);  

            
          //  FileBody bin = new FileBody(new File("writeFile:\\1\\Date.jar"));
              FileBody filebody = new FileBody(file);  
          
              MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();    
              multipartEntity.addPart(file.getName(), filebody);//�൱��<input type="file" name="media"/>    
              //����ļ�����Ĳ���  
              Set<String> keySet = postParam.keySet();  
              for (String key : keySet) {  
                 //�൱��<input type="text" name="name" value=name>    
                 multipartEntity.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain", Consts.UTF_8)));    
              }  
                
              HttpEntity reqEntity =  multipartEntity.build();   
              
   /*
              HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin)
              		.addPart("comment", comment)
              		.addPart("md", md)
              		.addPart("adapterName", adapterName)
              		.addPart("classPath", classPath)
              		.build();*/
              
         /*   
           // FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));  
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
            StringBody md = new StringBody(md5, ContentType.TEXT_PLAIN);  
            StringBody adapterName = new StringBody(bin.getFilename(), ContentType.TEXT_PLAIN);  
            StringBody classPath = new StringBody(path, ContentType.TEXT_PLAIN);  

            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin)
            		.addPart("comment", comment)
            		.addPart("md", md)
            		.addPart("adapterName", adapterName)
            		.addPart("classPath", classPath)
            		.build();  */
  
  
            httppost.setEntity(reqEntity);  
  
            System.out.println("executing request " + httppost.getRequestLine());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                System.out.println("----------------------------------------");  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                if (resEntity != null) {  

                     System.out.println("Response content length: " + resEntity.getContentLength());  
                     //��ӡ��Ӧ����    
                     resultMap.put("data", EntityUtils.toString(resEntity,Charset.forName("UTF-8")));
                }  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return resultMap;  
    }  

}
