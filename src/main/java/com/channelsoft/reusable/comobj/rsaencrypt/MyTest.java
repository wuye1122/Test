package com.channelsoft.reusable.comobj.rsaencrypt;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.usmlreasoning.ComputingContextImpl;

public class MyTest {
	public static void main(String[] args) throws Exception {
		String ServiceCode = "/ServiceBus/custView/cust/PCCheck001";
//		String ServiceCode = "/ServiceBus/saleView/order/suspend001";
//		String ServiceCode = "/ServiceBus/custView/cust/custInfo004";
		String TransactionID = "816700836360929836";
//		String address = "http://103.25.23.34:8020/esb/prodQry001";
		String address = "http://103.25.23.34:8022/esb/PCCheck001?token=d2841b25-3475-41c2-92df-c69886f56b9f";
//		String address = "http://103.25.23.34:8022/esb/suspend001?token=3b236e76-4662-4df6-8c61-46a8e3d2df56";
//		String address = "http://103.25.23.34:8022/esb/MemberInfo?token=f8ef6cd0-930d-4a4e-ac70-b7cc024f4849";
	
		String TelNum = "17090340319";
	//	String data = "{\"SOO\":[{\"PROD\":{\"ACC_NBR_170\":\"17090340175\",\"CHANNEL_CODE\":\"103\",\"EXT_SYSTEM\":\"103\"},\"PUB_REQ\":{\"TYPE\":\"QRY_PROD\"}}]}";
	//	String data = "{\"SOO\":[{\"CUST_PAY\":{\"CHANNEL_CODE\":\"103\",\"CUST_ID\":\"\",\"EXT_SYSTEM\":\"103\",\"PWD\":\"c33367701511b4f6020ec61ded352059\",\"QRY_NUMBER\":\"17090340319\",\"QRY_TYPE\":\"1\"},\"PUB_REQ\":{\"TYPE\":\"PAY_CHECK\"}}]}";
		String data = "{}";
//		String data = "{\"SOO\":[{\"PUB_REQ\":{\"TYPE\":\"SAVE_SALE_ORDER_INST\"},\"SALE_ORDER_INST\":{\"CHANNEL_CODE\": \"113\",\"CUST_ID\":\"31000000027941\",\"EXT_ORDER_ID\":\"\",\"EXT_SYSTEM\": \"10002\",\"SALE_ORDER_ID\":\"09123\"}},{\"PUB_REQ\":{\"TYPE\":\"SAVE_SALE_PROD_INST\"},\"SALE_PROD_INST\":{\"ACC_NBR\":\"17090340319\",\"MAIN_FLAG\":\"\",\"NEW_FLAG\":\"0\",\"PRODUCT_ID\":\"\",\"PROD_INST_ID\":\"0987\",\"SALE_ORDER_ID\":\"9876\",\"SERVICE_OFFER_ID\":\"108\"}}]}";
		//String param = "+";
		//System.out.println(param);
		SoapUtil httpCom = new SoapUtil();
		ComputingContext cc = new ComputingContextImpl();
		Variant[] varss = new Variant[4];
		varss[0] = new Variant(ServiceCode,cc);
		varss[1] = new Variant(TransactionID,cc);
		varss[2] = new Variant(data,cc);
		varss[3] = new Variant(address,cc);
		//System.out.println(varss[0].toString());
		Variant result =httpCom.invoke("SoapWebService", varss);
		System.out.println();
		System.out.println(result.toString()+"myTest");
		
		System.out.println(System.getProperty("user.dir"));
		
		ServiceCode = "/ServiceBus/custView/cust/custInfo004";
		TransactionID = "816700836360929836";
		address = "http://103.25.23.34:8022/esb/MemberInfo?token=f8ef6cd0-930d-4a4e-ac70-b7cc024f4849";
		data = "{}";
		httpCom = new SoapUtil();
		cc = new ComputingContextImpl();
		varss = new Variant[4];
		varss[0] = new Variant(ServiceCode,cc);
		varss[1] = new Variant(TransactionID,cc);
		varss[2] = new Variant(data,cc);
		varss[3] = new Variant(address,cc);
		//System.out.println(varss[0].toString());
		result =httpCom.invoke("SoapWebService", varss);
		System.out.println();
		System.out.println(result.toString()+"myTest");
		
		System.out.println(System.getProperty("user.dir"));
		
		ServiceCode = "/ServiceBus/saleView/order/suspend001";
		TransactionID = "816700836360929836";
		address = "http://103.25.23.34:8022/esb/suspend001?token=3b236e76-4662-4df6-8c61-46a8e3d2df56";
		data = "{}";
		httpCom = new SoapUtil();
		cc = new ComputingContextImpl();
		varss = new Variant[4];
		varss[0] = new Variant(ServiceCode,cc);
		varss[1] = new Variant(TransactionID,cc);
		varss[2] = new Variant(data,cc);
		varss[3] = new Variant(address,cc);
		//System.out.println(varss[0].toString());
		result =httpCom.invoke("SoapWebService", varss);
		System.out.println();
		System.out.println(result.toString()+"myTest");
		
		System.out.println(System.getProperty("user.dir"));
	
	}
	@Test
	public void test(){
		System.out.println(System.getProperty("user.dir"));
	}
}
