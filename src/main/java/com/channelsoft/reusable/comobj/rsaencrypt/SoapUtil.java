package com.channelsoft.reusable.comobj.rsaencrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
//import com.tydic.auth.client.utils.AuthConfigManagerClient;
//import com.tydic.osgi.org.springframework.context.ISpringContext;
//import com.tydic.osgi.org.springframework.context.SpringContextUtils;
import com.channelsoft.reusable.comobj.IDispatch;
import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.comobj.service.SLEEComponentContext;
import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;



public class SoapUtil implements IDispatch{
	 ComputingContext cc = null;
	
	 SLEEComponentContext comContext = null;
	 Logger logger = Logger.getLogger(SoapUtil.class);
	/** 
	 * 消息体认证
	 * @param json 前台拼接的报文
	 * @return 认证密文(数字签名)
	 */
	public static String signatureInfo(String json) {
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
		String encryptValue= null;
		try {
            String code = rsaEncrypt.MD5(json);
    		//把MD5加密内容通过私钥认证
    		encryptValue = rsaEncrypt.enc(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptValue; 	
	}
	
	/** 
	 * 发送报文
	 * @param serviceName 服务名称
	 * @param transactionID 流水号
	 * @param data 拼接报文
	 * @return xml报文
	 */
	public static String req(String ServiceCode,String transactionID,String data){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String ReqTime = formatter.format(new Date());
		//data = JSON.parseObject(data).toJSONString();
		String encryptValue = signatureInfo(data);
		Map<String, String> headMap = new HashMap<String, String>();
		//headMap.put("TransactionID", "189a5a5e-592c-4a98-a061-f74a22fe20e1");
		headMap.put("TransactionID", transactionID);
		headMap.put("SignatureInfo", encryptValue);
		headMap.put("ReqTime", ReqTime);
		headMap.put("SYS_ID", "103");
		headMap.put("ServiceCode", ServiceCode);
		String val = null;
		try {
			val = JSON.toJSONString(headMap);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String xml = 
		     "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tyd=\"http://www.tydic.com/\"><soapenv:Header/>" +
		     "<soapenv:Body><Business>{" +
		     "\"TcpCont\":" + val + ",\"SvcCont\":" + data +	 
		     "}</Business>" +
		     "</soapenv:Body></soapenv:Envelope>";
		return xml;
	}
	
	/** 
	 * 返回报文
	 * @param serviceName 服务名称
	 * @param transactionID 流水号
	 * @param data 拼接报文
	 * @return xml报文
	 * @throws UnsupportedEncodingException 
	 */
	public  String rsp(String data) throws UnsupportedEncodingException{
		logger.info("进入处理返回结果方法rsp");
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
		String repValue = data.substring(data.indexOf("<Business>")+10, data.indexOf("</Business>"));
		repValue = repValue.replaceAll("'", "\"");
		logger.info("处理后内容为："+repValue);
		Map<String, Map<String, Object>> maps = null;
		try {
			maps = JSON.parseObject(repValue,Map.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Map<String, Object> TcpContMap = (Map<String, Object>) maps.get("TcpCont");
		Map<String, Object> SvcCont = (Map<String, Object>) maps.get("SvcCont");
		String content =  SvcCont.toString() ;
		logger.info("得到SvcCont内容："+content);
		String SignatureInfo = (String) TcpContMap.get("SignatureInfo");
		logger.info("得到签名信息："+SignatureInfo);
		String decValue = rsaEncrypt.dec(SignatureInfo);
		logger.info("解密秘钥后的数据："+decValue);
		String encValue = rsaEncrypt.MD5(content);
		logger.info("MD5加密content内容后的数据："+encValue);
		if(decValue.equals(encValue)){
			logger.info("认证成功,返回结果："+content);
			return content;	
		}else{
			logger.info("认证失败");
			return "认证失败";
			
		}		
	}
	
	/** 
	 * soap协议掉后台服务
	 * @param serviceName 服务名称
	 * @param transactionID 流水号
	 * @param data 拼接报文
	 * @return 返回报文
	 * @throws Exception 
	 */
	public String SoapWebService(String serviceName,String transactionID,String data,String address) throws Exception {
		try {
			//创建连接
			logger.info("进入SoapWebService");
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			//创建实际的消息
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();

			//创建消息的部分对象            
			SOAPPart soapPart = message.getSOAPPart();

			//获取发送报文
			String xml = req(serviceName,transactionID,data);
			logger.info("生成请求报文："+xml);
			InputStream fileInputStream = new ByteArrayInputStream(xml.getBytes());   
			StreamSource preppedMsgSrc = new StreamSource(fileInputStream);
			//填充消息
			soapPart.setContent(preppedMsgSrc);
			//保存消息
			message.saveChanges();
			
			//System.out.println("\nREQUEST:\n");
			//message.writeTo(System.out);
			//System.out.println();
			
			//ISpringContext springInstance = SpringContextUtils.getInstance();
			//AuthConfigManagerClient configManager = (AuthConfigManagerClient) springInstance.getBean("configManager"); 
			//String destination = configManager.getProperty("com.tydic.framework.web.ias.url");
			//String destination = "http://192.168.161.91:1110/smiService/services/smiClubInterface";
			//发送消息
			SOAPMessage reply = connection.call(message, address);
	
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			ByteArrayOutputStream myOutStr = new ByteArrayOutputStream();
			if(reply!=null){

			//提取的回复内容
			Source sourceContent = reply.getSOAPPart().getContent();
			//为转换设置输出
			StreamResult result = new StreamResult(System.out);
			result.setOutputStream(myOutStr);

			transformer.transform(sourceContent,result);
			}
			String msg = myOutStr.toString("UTF-8").trim();      
			logger.info("收到回复的内容："+msg);
			//System.out.println(msg);
			connection.close();
			return rsp(msg);
		} catch (Exception e) {
			this.cc.reportError(e);
			return "";
		}
	}
	
	/** 
	 * soap协议掉后台服务（直接传xml调用）
	 * @param serviceName 服务名称
	 * @param transactionID 流水号
	 * @param data 拼接报文
	 * @return 返回报文
	 */
	public String SoapWebServiceNoHead(String serviceName,String transactionID,String xml) {
		try {
			//创建连接
			
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			//创建实际的消息
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();

			//创建消息的部分对象            
			SOAPPart soapPart = message.getSOAPPart();

			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String ReqTime = formatter.format(new Date());
			
			InputStream fileInputStream = new ByteArrayInputStream(xml.getBytes());   
			StreamSource preppedMsgSrc = new StreamSource(fileInputStream);
			//填充消息
			soapPart.setContent(preppedMsgSrc);
			//保存消息
			message.saveChanges();

			System.out.println("\nREQUEST:\n");
			message.writeTo(System.out);
			System.out.println();


			String destination = "";
			//发送消息
			SOAPMessage reply = connection.call(message, destination);


			//System.out.println("\nRESPONSE:\n");
			
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			ByteArrayOutputStream myOutStr = new ByteArrayOutputStream();

			//提取的回复内容
			Source sourceContent = reply.getSOAPPart().getContent();
			//为转换设置输出
			StreamResult result = new StreamResult(System.out);
			result.setOutputStream(myOutStr);

			transformer.transform(sourceContent,result);
			//System.out.println();
			String msg = myOutStr.toString().trim();
			//关闭连接           
			connection.close();
			return msg;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public static void main(String[] args) throws Exception{  
//		String msgData = "{    \"SOO\": [        {            \"PUB_REQ\": {                \"TYPE\": \"RechargeOrder\"            },            \"RECHARGEORDER_REQ\": {                \" ACCESSTYPE \": \"1\",               \" AMOUNT \": \"3000\",                \" CARD_NUMBER \": \"9ea8a97716989c43989749414b67337a\",                \" CARD_PASSWORD \": \"9ea8a97716989c43989749414b67337a\",                \" DESTINATION_ID \": \"17011111111\",                \" PARTNER \": \"123456\"            }        }    ]}";
		String msgData = "{\"SOO\":[{\"PUB_REQ\":{\"TYPE\":\"RechargeOrder\"},\"RECHARGEORDER_REQ\":{\"ACCESSTYPE\":\"1\",\"AMOUNT\":\"3000\",\"CARD_NUMBER\":\"9ea8a97716989c43989749414b67337a\",\"CARD_PASSWORD\":\"9ea8a97716989c43989749414b67337a\",\"DESTINATION_ID\":\"17011111111\",\"PARTNER\":\"123456\"}}]}";
		       msgData = "{\"SOO\":[{\"PUB_REQ\":{\"TYPE\":\"RechargeOrder\"},\"RECHARGEORDER_REQ\":{\"ACCESSTYPE\":\"1\",\"AMOUNT\":\"100\",\"CARD_NUMBER\":\"123456789987654321\",\"CARD_PASSWORD\":\"987654321123456789\",\"DESTINATION_ID\":\"17090340319\",\"PARTNER\":\"123456\"}}]}";
		       msgData = "{\"SOO\":[{\"PUB_REQ\":{\"TYPE\":\"RechargeOrder\"},\"RECHARGEORDER_REQ\":{\"ACCESSTYPE\":\"2\",\"AMOUNT\":\"10000\",\"CARD_NUMBER\":\"96d0028878d58c890f73e4ef3b449d12ce5126f844ce8540\",\"CARD_PASSWORD\":\"8707655a57cf605961ddae9a90dd76923a6b42b3fd7546cc\",\"DESTINATION_ID\":\"17090340319\",\"PARTNER\":\"123456\"}}]}";
		System.out.println(msgData);
		System.out.println(msgData.replaceAll("\"", "\"\""));
		String serviceCode = "/ServiceBus/custView/cust/CardOrderI";
		String address = "http://103.25.23.34:8020/esb/CardOrderNo?token=2a760f6a-dd1a-417a-98b7-0b7a508922f0";
		String transactionId = "12345654";
		SoapUtil soapClient = new SoapUtil();
		String ret = soapClient.SoapWebService(serviceCode, transactionId, msgData, address);
		System.out.println(ret);
//		Map<String, Map<String, Object>> maps = null;
//		try {
//			maps = JSON.parseObject(ret,Map.class);
//			Map<String, Object> SvcCont = (Map<String, Object>) maps.get("SvcCont");
//			String content =  SvcCont.toString() ;
//			System.out.println(content);
//			maps = JSON.parseObject(content, Map.class);
//
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
//		String orderNo = "";
//		System.out.println(orderNo);
		int begin = ret.indexOf("ORDERNO") + 10;
		int end = ret.indexOf("\"", begin);
		String orderNo = ret.substring(begin, end);
		System.out.println(orderNo);
		


		
		String txt = "07642012490863497";
		String plainText = null;
		String cipherText = null;
		String key = "12345678ffqjpejfq34uf348ut234jfq234u8fwser4htfqw3fhwq3or4fgq23o748grtp2q349t234t8frhaldkfhq923q239fhqf3hqp9yhfqfqwehfqpehfq23q";
		String encryptedCardNo = CipherUtil.encryptData(txt, key);
		plainText = CipherUtil.decryptData(encryptedCardNo, key);
		System.out.println("text = " + txt);
		System.out.println("encryptedCardNo = " + encryptedCardNo);
		System.out.println("plainText = " + plainText);
		txt = "013874772879568100";
		String encryptedCardPwd = CipherUtil.encryptData(txt, key);
		System.out.println("text = " + txt);
		System.out.println("encryptedCardPwd = " + encryptedCardPwd);
		plainText = CipherUtil.decryptData(encryptedCardPwd, key);
		System.out.println("plainText = " + plainText);
		Map<String, Object> data=new HashMap<String, Object>();
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStr = sf.format(new Date());
		txt = "http://139.196.196.176:8080/recharge_web/recharge/exchange_card?card_amount=100&card_number=39b875782948e87a39b875782948e87a8b8cb85466b3e76b&card_password=a54a1fbe46638597a54a1fbe466385979f6fd9e3fc9cdd96&notify_url=www.channelsoft.com&partner=qnsoft&request_order_no=1234567890&sign_type=MD5&time_stamp=20160128160704&version=3.5&partner=qnsoft&sign=336b614a62abdeaaf37c1a6b57e19021";
		String msg = "card_amount=10000&card_number=39b875782948e87a39b875782948e87a8b8cb85466b3e76b&card_password=a54a1fbe46638597a54a1fbe466385979f6fd9e3fc9cdd96&notify_url=www.channelsoft.com&request_order_no=1234567890&sign_type=MD5&time_stamp=20160128160704&version=3.5&partner=123456";
		       msg = "card_amount=10000&card_number=" + encryptedCardNo + "&card_password=" + encryptedCardPwd + "&notify_url=www.channelsoft.com&partner=123456&request_order_no=" + orderNo + "&sign_type=MD5&time_stamp=" + timeStr + "&version=3.5&partner_key=12345678";
		       System.out.println(msg);
		String signText = rsaEncrypt.MD5(msg);
		signText = signText.toLowerCase();
		System.out.println(signText);
		System.out.println("old sign is :" + "336b614a62abdeaaf37c1a6b57e19021");
		String url = "http://139.196.196.176:8080/recharge_web/recharge/exchange_card?" + msg + "&sign=" + signText;
		System.out.println(url);
		//data.put("BillCycleID", 201505);
		data.put("ContactChannle", "103");
		//data.put("CUST_ID", "31000000027941");
		//data.put("EXT_SYSTEM", "103");
		//data.put("PWD", "c33367701511b4f6020ec61ded352059");
		data.put("MSISDN", "17090340319");
		//data.put("QRY_TYPE", "2");
		//data.put("RESET_TYPE", "1");
//		String address = "http://103.25.23.34:8022/esb/QueryCurrentBill?token=09fe0fce-c079-4504-8de3-521a63b040fd";

		Map<String, Object> type=new HashMap<String, Object>();
		type.put("TYPE", "QRY_PROD");
		
		Map<String, Object> soo=new HashMap<String, Object>();
		soo.put("PROD", data);
		soo.put("PUB_REQ", type);
		String reqJson = null;
		try {
			reqJson = JSON.toJSONString(soo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reqJson = "{\"SOO\":["  + reqJson + "]}";
		System.out.println();
		System.out.println(reqJson);
		SoapUtil soapUilt = new SoapUtil();
		String rspJson = soapUilt.SoapWebService("/ServiceBus/prodView/prod/queryCurrentBill", "7229772799466800642", reqJson,address);   
		//String rsp = rsp(rspJson);
		System.out.println("===================");
		System.out.println(rspJson);

    }

	@Override
	public Variant getProperty(String arg0, Variant[] arg1) throws Exception {
	    this.cc.reportError(new NoSuchMethodException());

	    return null;
	}

	@Override
	public Variant invoke(String method, Variant[] args) throws Exception {
	    if (method.equalsIgnoreCase("SoapWebService"))
	    {
	      if ((args.length!=4))
	      {
	        this.cc.reportError(new InvalidParameterCountException());

	        return null;
	      }
	      //SoapUtil soapUtil = new SoapUtil();
	      return new Variant(
	    		 SoapWebService(args[0].toString(), args[1].toString(),args[2].toString(),args[3].toString()), this.cc);
	    }
	    if (method.equalsIgnoreCase("desEncrypt"))
	    {
	      if ((args.length!=2))
	      {
	        this.cc.reportError(new InvalidParameterCountException());
	        return null;

	      }
	      //SoapUtil soapUtil = new SoapUtil();
	        return new Variant(
	        		CipherUtil.encryptData(args[0].toString(), args[1].toString()),this.cc);
	    }
	    if (method.equalsIgnoreCase("desDecrypt"))
	    {
	      if ((args.length!=2))
	      {
	        this.cc.reportError(new InvalidParameterCountException());
	        return null;

	      }
	      //SoapUtil soapUtil = new SoapUtil();
	        return new Variant(
	        		CipherUtil.decryptData(args[0].toString(), args[1].toString()),this.cc);
	    }
	    if (method.equalsIgnoreCase("md5"))
	    {
	      if ((args.length!=1))
	      {
	        this.cc.reportError(new InvalidParameterCountException());

	        return null;
	      }

	      return new Variant(
	        MD5.MD5(args[0].toString()), this.cc);
	    }
	    return getProperty(method, args);
	}
	 public void setComponentContext(SLEEComponentContext comContext)
	  {
	    this.comContext = comContext;
	  }
	@Override
	public void setComputingContext(ComputingContext cc) {
		// TODO Auto-generated method stub
		this.cc = cc;
	    setComponentContext(cc.getSLEEComponentContext());
	}

	@Override
	public void setProperty(String arg0, Variant[] arg1) throws Exception {
		// TODO Auto-generated method stub
		this.cc.reportError(new NoSuchMethodException());
	}
	
}
