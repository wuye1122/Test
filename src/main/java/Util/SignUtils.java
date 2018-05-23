package Util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author cjm
 *
 */
public class SignUtils {
	/**
	 * 获取鉴权
	 * @param entId
	 * @return
	 * @throws IOException
	 */
		public static String queryWithSign(String entId,Integer pageNum,String startTime,String endTime) throws IOException {
			//http://open.ccod.com/customWebservice/mvc/sessionDetail/query.do 
			String result  = SignUtils.getResult("http://open.ccod.com/customWebservice/mvc/Encryption/queryEncryption.do?request=query");
			JSONObject obj = (JSONObject) JSONObject.parse(result);
			//"/customWebservice/mvc/sessionDetail/query.do?Nonce=41953&Timestamp=1525690440958&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";
			String authorization = obj.getString("Authorization");
			String Signature = obj.getString("Signature");
			System.out.println("Signature="+Signature);
			System.out.println("authorization="+authorization);
			StringBuffer URI = new StringBuffer("http://open.ccod.com/customWebservice/mvc/sessionDetail/query.do?");
			URI.append("entId =").append(entId).append("&");
			if(pageNum == null){
				URI.append("pageNum=1").append("&");
			}else{
				URI.append("pageNum=").append(pageNum).append("&");
			}
			URI.append("startTime=").append(startTime).append("&");
			URI.append("endTime=").append(endTime).append("&");
			URI.append("Signature=").append(Signature);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpPost httpPost = new HttpPost(URI.toString());
				httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
				httpPost.setHeader("Accept", "application/json;charset=utf-8");
				httpPost.setHeader("Authorization", authorization);
				CloseableHttpResponse response2 = httpclient.execute(httpPost);
				try {
					HttpEntity entity2 = response2.getEntity();
					String decodeByGBK = EntityUtils.toString(entity2, "UTF-8");
					return  decodeByGBK;
				} finally {
					response2.close();
				}
			} finally {
				httpclient.close();
			}
		}
		public static String getResult(String url) throws ClientProtocolException, IOException{
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String decodeByGBK = null;
			try {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
				httpPost.setHeader("Accept", "application/json;charset=utf-8");				
				CloseableHttpResponse response2 = httpclient.execute(httpPost);
				try {
					HttpEntity entity2 = response2.getEntity();
					 decodeByGBK = EntityUtils.toString(entity2, "UTF-8");
					System.out.println(decodeByGBK);
				} finally {
					response2.close();
				}
			} finally {
				httpclient.close();
			}
			return decodeByGBK;
		}
		
		public static void main(String []args) throws IOException{
		System.out.println(new SignUtils().queryWithSign("0101190378", 1, "1525104000000", "1525704000000"));	
		}
}
