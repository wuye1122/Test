package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

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


public class TestIvrTimeout {

	/**
	 * @author wuhl
	 * void
	 */
	public String SoapWebService(String serviceName,String transactionID,String data,String address) throws Exception {
		try {
			
             
			URL urlval = new URL(new URL(address), "" ,new URLStreamHandler(){

				@Override
				protected URLConnection openConnection(URL u) 
						throws IOException {
					// TODO Auto-generated method stub
					 URL target = new URL(u.toString());
	                  URLConnection connection = target.openConnection();
	                  // Connection settings
	                  connection.setConnectTimeout(30000);
	                  connection.setReadTimeout(30000);
	                  System.out.println(connection);
	                  return(connection);
				}
				
			});

	
			System.out.println(urlval);
			return "1";

	
		}catch (IOException e) {
			return "timeout";
		} 
		catch (Exception e) {
			return "";
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	try {
		
		
			System.out.println(new TestIvrTimeout().SoapWebService("", "", "", "10.130.24.134"));

	//	System.out.println(new TestIvrTimeout().SoapWebService("", "", "", "10.130.25.120"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		e.printStackTrace();
	}
	}

}
