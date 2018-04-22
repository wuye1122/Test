package IO;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//�ò�������������ʾ�ͻ����������Կ�������ýӿڵİ����࣬�ͻ������Լ�������ʹ�ø����ڵĴ�������

//��Ȩ

public class MongdbTest {
	// SECRET_KEY
/*	public static final String SECRET_KEY = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
*/	
	public static final String SECRET_KEY = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qB";
	// SECRET_ID
	public static final String SECRET_ID = "AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";
	// entId
	public static final String entId = "0101290267";//���ǵ���ҵid

	public static void main(String[] args) throws Exception {		
		//�ô�������������Կ������/customWebservice/mvc/sessionDetail/query.do�ǽӿ������ַ��ȥ��ǰ��IP�Ͷ˿ں������
		//����Nonce�������������������д��һ���̶�ֵ
		//����Timestamp�ǵ�ǰʱ���
		//SecretIdΪԤ�ȶ���õ�ֵ
		System.out.println(SECRET_KEY.length());
		System.out.println(SECRET_ID.length());

		String authorization = "/customWebservice/mvc/sessionDetail/query.do?Nonce=34512&Timestamp=1408704141&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";
		//���ýӿ�������Կ
		String Signature = new QnSignUitl().sign(authorization, SECRET_KEY);
		
		System.out.println(Signature);
		//URI��Ҫ���õĽӿڵ�ַ
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
