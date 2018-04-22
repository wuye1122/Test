package com.channelsoft.reusable.comobj.rsaencrypt;

import java.io.BufferedReader;                                                                                              
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;                                                                                                 
import java.io.InputStream;                                                                                                 
import java.io.InputStreamReader;                                                                                           
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;                                                                                   
import java.security.KeyFactory;                                                                                                                                                                            
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;                                                                                                                                                                      
import java.security.interfaces.RSAPrivateKey;                                                                              
import java.security.interfaces.RSAPublicKey;                                                                               
import java.security.spec.InvalidKeySpecException;                                                                          
import java.security.spec.PKCS8EncodedKeySpec;                                                                              
import java.security.spec.X509EncodedKeySpec;                                                                               

import javax.crypto.BadPaddingException;                                                                                    
import javax.crypto.Cipher;                                                                                                 
import javax.crypto.IllegalBlockSizeException;                                                                              
import javax.crypto.NoSuchPaddingException;                                                                                 

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;                                                                  

import sun.misc.BASE64Decoder;                                                                                              
                                                                                                                            
public class RSAEncrypt {
	Logger logger = Logger.getLogger(RSAEncrypt.class);
	//private static final String privatePath = "\\conf\\pem\\rsa_private_key.pem";
	private static final String privatePath = "/home/slee/conf/pem/rsa_private_key.pem";
	
	//private static final String publicPath = "\\conf\\pem\\rsa_public_key.pem";
	private static final String publicPath = "/home/slee/conf/pem/rsa_public_key.pem";
	
//	private static final String privatePath = "\\pem\\2\\rsa_private_key.pem";
//	
//	private static final String publicPath = "\\pem\\1\\rsa_public_key.pem";
                                                                                                                                                                                                                                                                                                                                                 
    /**                                                                                                                     
     * 绉侀挜                                                                                                                 
     */                                                                                                                     
    private RSAPrivateKey privateKey;                                                                                       
                                                                                                                            
    /**                                                                                                                     
     * 鍏挜                                                                                                                 
     */                                                                                                                     
    private RSAPublicKey publicKey;                                                                                         
                                                                                                                            
    /**                                                                                                                     
     * 瀛楄妭鏁版嵁杞瓧绗︿覆涓撶敤闆嗗悎                                                                                             
     */                                                                                                                     
    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
                                                                                                                            
                                                                                                                            
    /**                                                                                                                     
     * 鑾峰彇绉侀挜                                                                                                             
     * @return 褰撳墠鐨勭閽ュ璞�                                                                                              
     */                                                                                                                     
    public RSAPrivateKey getPrivateKey() {                                                                                  
        return privateKey;                                                                                                  
    }                                                                                                                       
                                                                                                                            
    /**                                                                                                                     
     * 鑾峰彇鍏挜                                                                                                             
     * @return 褰撳墠鐨勫叕閽ュ璞�                                                                                              
     */                                                                                                                     
    public RSAPublicKey getPublicKey() {                                                                                    
        return publicKey;                                                                                                   
    }                                                                                                                                                                                                                                                                                                                                                                     
                                                                                                                            
   /**                                                                                                                      
    * 浠庢枃浠朵腑杈撳叆娴佷腑鍔犺浇鍏挜                                                                                              
    * @param in 鍏挜杈撳叆娴�                                                                                                 
    * @throws Exception 鍔犺浇鍏挜鏃朵骇鐢熺殑寮傚父                                                                                
    */                                                                                                                      
   public void loadPublicKey(InputStream in) throws Exception{                                                              
       try {                                                                                                                
           BufferedReader br= new BufferedReader(new InputStreamReader(in));                                                
           String readLine= null;                                                                                           
           StringBuilder sb= new StringBuilder();                                                                           
           while((readLine= br.readLine())!=null){                                                                          
               if(readLine.charAt(0)=='-'){                                                                                 
                   continue;                                                                                                
               }else{                                                                                                       
                   sb.append(readLine);                                                                                     
                   sb.append('\r');                                                                                         
               }                                                                                                            
           }                                                                                                                
           loadPublicKey(sb.toString());                                                                                    
       } catch (IOException e) {                                                                                            
           throw new Exception("公钥数据流读取错误");                                                                       
       } catch (NullPointerException e) {                                                                                   
           throw new Exception("公钥输入流为空");                                                                           
       }                                                                                                                    
   }                                                                                                                        
                                                                                                                            
                                                                                                                            
   /**                                                                                                                      
    * 浠庡瓧绗︿覆涓姞杞藉叕閽�                                                                                                   
    * @param publicKeyStr 鍏挜鏁版嵁瀛楃涓�                                                                                   
    * @throws Exception 鍔犺浇鍏挜鏃朵骇鐢熺殑寮傚父                                                                                
    */                                                                                                                      
   public void loadPublicKey(String publicKeyStr) throws Exception{                                                         
       try {                                                                                                                
           BASE64Decoder base64Decoder= new BASE64Decoder();                                                                
           byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);                                                         
           KeyFactory keyFactory= KeyFactory.getInstance("RSA");                                                            
           X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);                                                      
           this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);                                               
       } catch (NoSuchAlgorithmException e) {                                                                               
           throw new Exception("无此算法");                                                                                 
       } catch (InvalidKeySpecException e) {                                                                                
           throw new Exception("公钥非法");                                                                                 
       } catch (IOException e) {                                                                                            
           throw new Exception("公钥数据内容读取错误");                                                                     
       } catch (NullPointerException e) {                                                                                   
           throw new Exception("公钥数据为空");                                                                             
       }                                                                                                                    
   }                                                                                                                        
                                                                                                                            
   /**                                                                                                                      
    * 浠庢枃浠朵腑鍔犺浇绉侀挜                                                                                                      
    * @param keyFileName 绉侀挜鏂囦欢鍚�                                                                                        
    * @return 鏄惁鎴愬姛                                                                                                      
    * @throws Exception                                                                                                     
    */                                                                                                                      
   public void loadPrivateKey(InputStream in) throws Exception{                                                             
       try {                                                                                                                
           BufferedReader br= new BufferedReader(new InputStreamReader(in));                                                
           String readLine= null;                                                                                           
           StringBuilder sb= new StringBuilder();                                                                           
           while((readLine= br.readLine())!=null){                                                                          
               if(readLine.charAt(0)=='-'){                                                                                 
                   continue;                                                                                                
               }else{                                                                                                       
                   sb.append(readLine);                                                                                     
                   sb.append('\r');                                                                                         
               }                                                                                                            
           }                                                                                                                
           loadPrivateKey(sb.toString());                                                                                   
       } catch (IOException e) {                                                                                            
           throw new Exception("私钥数据读取错误");                                                                         
       } catch (NullPointerException e) {                                                                                   
           throw new Exception("私钥输入流为空");                                                                           
       }                                                                                                                    
   }                                                                                                                        
                                                                                                                            
   public void loadPrivateKey(String privateKeyStr) throws Exception{                                                       
       try {                                                                                                                
           BASE64Decoder base64Decoder= new BASE64Decoder();                                                                
           byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);                                                        
           PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);                                                    
           KeyFactory keyFactory= KeyFactory.getInstance("RSA");                                                            
           this.privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);                                            
       } catch (NoSuchAlgorithmException e) {                                                                               
           throw new Exception("无此算法");                                                                                 
       } catch (InvalidKeySpecException e) {                                                                                
           throw new Exception("私钥非法");                                                                                 
       } catch (IOException e) {                                                                                            
           throw new Exception("私钥数据内容读取错误");                                                                     
       } catch (NullPointerException e) {                                                                                   
           throw new Exception("私钥数据为空");                                                                             
       }                                                                                                                    
   }                                                                                                                        
                                                                                                                            
   /**                                                                                                                      
    * 鍔犲瘑杩囩▼                                                                                                              
    * @param privateKey 绉侀挜                                                                                                 
    * @param plainTextData 鏄庢枃鏁版嵁                                                                                         
    * @return                                                                                                               
    * @throws Exception 鍔犲瘑杩囩▼涓殑寮傚父淇℃伅                                                                                
    */                                                                                                                      
   public byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception{                                    
       if(privateKey== null){                                                                                                
           throw new Exception("加密私钥为空, 请设置");                                                                     
       }                                                                                                                    
       Cipher cipher= null;                                                                                                 
       try {                                                                                                                
           cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding", new BouncyCastleProvider());                                                   
           cipher.init(Cipher.ENCRYPT_MODE, privateKey);                                                                     
           byte[] output= cipher.doFinal(plainTextData);                                                                    
           return output;                                                                                                   
       } catch (NoSuchAlgorithmException e) {                                                                               
           throw new Exception("无此加密算法");                                                                             
       } catch (NoSuchPaddingException e) {                                                                                 
           e.printStackTrace();                                                                                             
           return null;                                                                                                     
       }catch (InvalidKeyException e) {                                                                                     
           throw new Exception("加密公钥非法,请检查");                                                                      
       } catch (IllegalBlockSizeException e) {                                                                              
           throw new Exception("明文长度非法");                                                                             
       } catch (BadPaddingException e) {                                                                                    
           throw new Exception("明文数据已损坏");                                                                           
       }                                                                                                                    
   }                                                                                                                        
                                                                                                                            
   /**                                                                                                                      
    * 瑙ｅ瘑杩囩▼                                                                                                              
    * @param publicKey 鍏挜                                                                                                
    * @param cipherData 瀵嗘枃鏁版嵁                                                                                            
    * @return 鏄庢枃                                                                                                          
    * @throws Exception 瑙ｅ瘑杩囩▼涓殑寮傚父淇℃伅                                                                                
    */                                                                                                                      
   public byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception{                                     
       if (publicKey== null){                                                                                              
           throw new Exception("解密公钥为空, 请设置");                                                                     
       }                                                                                                                    
       Cipher cipher= null;                                                                                                 
       try {                                                                                                                
           cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding", new BouncyCastleProvider());                                                   
           cipher.init(Cipher.DECRYPT_MODE, publicKey);                                                                    
           byte[] output= cipher.doFinal(cipherData);                                                                       
           return output;                                                                                                   
       } catch (NoSuchAlgorithmException e) {                                                                               
           throw new Exception("无此解密算法");                                                                             
       } catch (NoSuchPaddingException e) {                                                                                 
           e.printStackTrace();                                                                                             
           return null;                                                                                                     
       }catch (InvalidKeyException e) {                                                                                     
           throw new Exception("解密私钥非法,请检查");                                                                      
       } catch (IllegalBlockSizeException e) {                                                                              
           throw new Exception("密文长度非法");                                                                             
       } catch (BadPaddingException e) {                                                                                    
           throw new Exception("密文数据已损坏");                                                                           
       }                                                                                                                    
   }                                                                                                                        
                                                                                                                            
                                                                                                                            
   /**                                                                                                                      
    * 瀛楄妭鏁版嵁杞崄鍏繘鍒跺瓧绗︿覆                                                                                              
    * @param data 杈撳叆鏁版嵁                                                                                                  
    * @return 鍗佸叚杩涘埗鍐呭                                                                                                  
    */                                                                                                                      
   public static String byteArrayToString(byte[] data){                                                                     
       StringBuilder stringBuilder= new StringBuilder();                                                                    
       for (int i=0; i<data.length; i++){                                                                                   
           //鍙栧嚭瀛楄妭鐨勯珮鍥涗綅 浣滀负绱㈠紩寰楀埌鐩稿簲鐨勫崄鍏繘鍒舵爣璇嗙 娉ㄦ剰鏃犵鍙峰彸绉�                                              
           stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);                                                           
           //鍙栧嚭瀛楄妭鐨勪綆鍥涗綅 浣滀负绱㈠紩寰楀埌鐩稿簲鐨勫崄鍏繘鍒舵爣璇嗙                                                              
           stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);                                                                
           if (i<data.length-1){                                                                                            
               stringBuilder.append(' ');                                                                                   
           }                                                                                                                
       }                                                                                                                    
       return stringBuilder.toString();                                                                                     
   }
   
   public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
   
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2; 
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));  
		}
		return result;
	}
	
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
	
	/**
	 * 绉侀挜鍔犲瘑
	 */
	public String enc(String code){
		RSAEncrypt rsaEncrypt= new RSAEncrypt();
		String encryptValue = null;
		try {
			String relativelyPath=System.getProperty("user.dir"); 
			//File file = new File(relativelyPath+privatePath);
			logger.info("loading密钥");
			File file = new File(privatePath);
			FileInputStream fis = new FileInputStream(file);
			rsaEncrypt.loadPrivateKey(fis);
			byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPrivateKey(), code.getBytes());
			encryptValue = byte2hex(cipher);
		} catch (Exception e) {
			System.err.println(e.getMessage());                                                                              
	        System.err.println("加载私钥失败"); 
		}
		return encryptValue;
	}
    
	/**
	 * 鍏挜瑙ｅ瘑
	 */
	public String dec(String signatureInfo){
		logger.info("进入公钥解密秘钥的方法dec");
		logger.info("loading公钥");
		RSAEncrypt rsaEncrypt= new RSAEncrypt();
		String Text = null;
		try {
			String relativelyPath=System.getProperty("user.dir"); 
			//File file = new File(relativelyPath+publicPath);
		    File file = new File(publicPath);
			FileInputStream fis = new FileInputStream(file);
			rsaEncrypt.loadPublicKey(fis);
			logger.info("加载公钥成功");
			byte[] decCipher = hexStringToByte(signatureInfo);
			byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), decCipher);
			//Text =  new String(RSAEncrypt.byte2hex(plainText));
			Text =  new String(plainText);
		} catch (Exception e) {
			logger.error(e.getMessage());                                                                            
			logger.error("加载公钥失败");
		}
		return Text;
	}
	
	/** 
	 * MD5鍔犲瘑
	 * @param val 鏄庢枃
	 * @return 瀵嗘枃
	 * @throws UnsupportedEncodingException 
	 */
	public String MD5(String val) throws UnsupportedEncodingException{
		logger.info("对conntent进行MD5加密的方法MD5");
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			logger.equals(e.getMessage());
		}
 		md5.update(val.getBytes("UTF-8"));
 		String code = RSAEncrypt.byte2hex(md5.digest());
 		return code;
	}
	
   public static void main(String[] args){                                                                                  
		/*RSAEncrypt rsaEncrypt = new RSAEncrypt();
		//鍔犺浇绉侀挜  
		try {
			File file = new File("D:/rsa_private_key.pem");
			FileInputStream fis = new FileInputStream(file);
			rsaEncrypt.loadPrivateKey(fis);// RSAEncrypt.class.getResourceAsStream("/configuration.properties"));
			System.out.println("鍔犺浇绉侀挜鎴愬姛");   
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       //鍔犺浇鍏挜                                                                                                           
       try {
    	   File file = new File("D:/rsa_public_key.pem");
		   FileInputStream fis = new FileInputStream(file);
           rsaEncrypt.loadPublicKey(fis);                                                         
           System.out.println("鍔犺浇鍏挜鎴愬姛");                                                                              
       } catch (Exception e) {                                                                                              
           System.err.println(e.getMessage());                                                                              
           System.err.println("鍔犺浇鍏挜澶辫触");                                                                              
       }                                                                                                                    
                                                                                                                                                                                                                                                
       //娴嬭瘯瀛楃涓�                                                                                                        
        String encryptStr= "{\"SOO\":[{\"CUST_LOGIN\":{\"EXT_SYSTEM\":\"111111\",\"LOGIN_NBR\":\"18108611556\",\"LOGIN_TYPE\":\"1\",\"PWD\":\"123456\",\"SEL_IN_ORG_ID\":\"01\"},\"PUB_REQ\":{\"TYPE\":\"Example\"}}]}";                                                                        
                                                                                                                            
        try { 
        	String code = rsaEncrypt.MD5(encryptStr);
    		System.out.println("MD5鍔犲瘑锛�+code);
            //鍔犲瘑                                                                                                          
            byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPrivateKey(), code.getBytes()); 
            System.out.println("绉侀挜鍔犲瘑锛�+RSAEncrypt.byte2hex(cipher));
            
            //瑙ｅ瘑
    		//String dec =  "C3C1B2C330325EE1FBE7C938B7D563BB1E2BCC2287658C9B79BDC656188DD4836FB84CA7157837E6726B7D3375AAF9A04FD391FB790F7A33ED5F738709BECAD388BE8E60EA3F3A454B78C3D8C92B499A9711E0F14F8FD85BD08F7335986C04F779B62B19B28B744B2D676F66D7A3BBB720F1FFA1E4AB968CFADD5A293FA48483";
    		String dec =  "2588C808563FB816C8CB090E153D6AD0A2E5387770CFA1C22BC1888E268D871B123CC605EC0BD6ABE6C25564FEFC7559C6BB0BC02A896B27292E69624D0EF05021FC35B3D136808DB066CEEA64C319DD2213BC09F0BFC819502D5CC3CE2E54EEA719D0A3096179CB9FA18AD2508F2DD60356F5D8F62F14CE6C46C4CAA93C503F";
    		byte[] decCipher = hexStringToByte(dec);
            byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), decCipher);                                                                                                     
            System.out.println("鍏挜瑙ｅ瘑锛�+new String(plainText));   
        } catch (Exception e) {                                                                                             
            System.err.println(e.getMessage());                                                                             
        }                     */     
	   
	   //String md5= "85F19C5709CA9D6C042F56F2475FE96A09D18B81661555C2D878ADAA5E272289BFA3774598989088823288FCCA67F175230F3F0F2CBE615C7CDD7446CDDE5926B61B7F4E3B4D395A80DCFFFAE9AB99C71E36881A32A1919E0ECBDA1C3409EEE35A926A5AAA4A9D840889856937554FA8041796C7AC06F138F56C994BF8CCE1E4";
	     String md5 = "D6D09EA7C22F24A0ACD0C207DFA92AFC";
	     RSAEncrypt rsa = new RSAEncrypt();
	     System.out.println(rsa.enc(md5));
	     System.out.println(rsa.dec(rsa.enc(md5)
	    		 ));
    }

}                                                                                                                           
