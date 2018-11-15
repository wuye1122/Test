package IO;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author kangqz
 * 2016-01-25
 */
public class HttpPushDataUtils {
	private static Logger logger = Logger.getLogger(HttpPushDataUtils.class);
	private static PoolingHttpClientConnectionManager cm;
	private static RequestConfig requestConfig;
	private static String EMPTY_STR = "";
	private static String UTF_8 = "UTF-8";

	private static void init(){
		if(cm == null){
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(50);//整个连接池最大连接数
			cm.setDefaultMaxPerRoute(5);//每路由最大连接数，默认值是2
		}
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(5000);
		// 设置读取超时
		configBuilder.setSocketTimeout(5000);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(5000);

		requestConfig = configBuilder.build();
	}

	/**
	 * 通过连接池获取HttpClient
	 * @return
	 */
	private static CloseableHttpClient getHttpClient(){
		init();
		return HttpClients.custom().setConnectionManager(cm).build();
	}

	/**
	 *
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url){
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException{
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		httpGet.setConfig(requestConfig);

		return getResult(httpGet);
	}

	public static String httpGetRequest(String url, Map<String, Object> headers,
										Map<String, Object> params) throws URISyntaxException{
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		for (Map.Entry<String, Object> param: headers.entrySet()) {
			httpGet.addHeader(param.getKey(), (String) param.getValue());
		}
		return getResult(httpGet);
	}

	public static String httpPostRequest(String url){
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}

	public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
		return getResult(httpPost);
	}

	public static String httpPostRequest(String url, Map<String, Object> headers,
										 Map<String, Object> params) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(url);

		for (Map.Entry<String, Object> param: headers.entrySet()) {
			httpPost.addHeader(param.getKey(), (String) param.getValue());
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

		return getResult(httpPost);
	}

	private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params){
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param: params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), (String) param.getValue()));
		}

		return pairs;
	}


	/**
	 * 处理Http请求
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request){
		CloseableHttpClient httpClient = getHttpClient();
		try{
			logger.debug("httpClient execute start!");
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			if(entity!=null){
				String result = EntityUtils.toString(entity);
				logger.debug("result is: "+result);
				response.close();
				logger.debug("response has closed !");
				return result;
			}
			logger.debug("response's entity is null!");
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{

		}
		logger.debug("response's entity is null,return empty string!");
		return EMPTY_STR;
	}

	/**
	 * 模拟http文件传输 pic将适配器分发dps
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
			multipartEntity.addPart(file.getName(), filebody);//相当于<input type="file" name="media"/>
/*
			entity.addPart("fileUploadFileName", new StringBody(file.getName(), Charset.forName("UTF-8")));
*/
			//设计文件以外的参数
			Set<String> keySet = postParam.keySet();
			for (String key : keySet) {
				//相当于<input type="text" name="name" value=name>
				multipartEntity.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain", Consts.UTF_8)));
			}

			HttpEntity reqEntity =  multipartEntity.build();



			httppost.setEntity(reqEntity);

			System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				logger.debug("url:"+url +"传输适配器结果:"+response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {

					System.out.println("Response content length: " + resEntity.getContentLength());
					//resultMap.put("state", response.getStatusLine());
					//打印响应内容
					resultMap.put("data", EntityUtils.toString(resEntity, Charset.forName("UTF-8")));

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