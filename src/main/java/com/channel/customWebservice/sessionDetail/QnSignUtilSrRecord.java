package com.channel.customWebservice.sessionDetail;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class QnSignUtilSrRecord {

    // SECRET_KEY
    public static final String SECRET_KEY = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
    // SECRET_ID
    public static final String SECRET_ID = "AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";

    // entId
    public static final String entId = "10094";

    public static void main(String[] args) throws Exception {
        int num = (int) (Math.random() * 90000) + 10000;//entId=0101490275&pageNum=1&sessionId=5069304761828914757
        long time = System.currentTimeMillis();
        String authorization = "/customWebservice/mvc/sessionDetail/querySrRecord.do?Nonce=10000233&Timestamp=1489655846&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";
        String Signature = QnSignUitl.sign(authorization, SECRET_KEY);
        System.out.println("Signature:"+Signature);
                                               //1611122006462726494
        //db.session_detail.find({"session_id":"?1611122006462726494?"})

        String URI = "http://hbyd.ccod.com:8080/customWebservice/mvc/sessionDetail/querySrRecord.do?entId=" + entId + "&sessionId=3052316004005707797&pageNum=1&Signature="+Signature;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(URI);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.setHeader("Accept", "application/json;charset=utf-8");
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
