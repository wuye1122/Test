package IO;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//该测试类是用于提示客户如何生成秘钥，并调用接口的案例类，客户可在自己的类中使用该类内的代码内容

//鉴权

public class MongdbTest {
	// SECRET_KEY
/*	public static final String SECRET_KEY = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
*/	
	public static final String SECRET_KEY = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qB";
	// SECRET_ID
	public static final String SECRET_ID = "AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";
	// entId
	public static final String entId = "0101290267";//你们的企业id

	public static void main(String[] args) throws Exception {		
		//该串是用于生成秘钥，其中/customWebservice/mvc/sessionDetail/query.do是接口请求地址中去掉前面IP和端口后的内容
		//参数Nonce是随机的正整数，可以写成一个固定值
		//参数Timestamp是当前时间戳
		//SecretId为预先定义好的值
		System.out.println(SECRET_KEY.length());
		System.out.println(SECRET_ID.length());

		String authorization = "/customWebservice/mvc/sessionDetail/query.do?Nonce=34512&Timestamp=1408704141&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";
		//调用接口生成秘钥
		String Signature = new QnSignUitl().sign(authorization, SECRET_KEY);
		
		System.out.println(Signature);
		//URI是要调用的接口地址
		String URI = "http://open.ccod.com/customWebservice/mvc/sessionDetail/query.do?entId="+entId+"&startTime=1478434172000&endTime=1478434182000&pageNum=1&Signature="+Signature;
		System.out.println(URI);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(URI);
			httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Authorization", authorization);
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(response2.getStatusLine());
				System.out.println(response2.getEntity().getContentType());
				HttpEntity entity2 = response2.getEntity();
				String decodeByGBK = EntityUtils.toString(entity2, "UTF-8");
				System.out.println(decodeByGBK);				
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}
	}
}
