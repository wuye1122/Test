package com.channelsoft.reusable.comobj.rsaencrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5 {
	Logger logger = Logger.getLogger(MD5.class);
	public static String MD5(String val) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md5.update(val.getBytes());
		String code = byte2hex(md5.digest());
		return code;
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
		return hs.toLowerCase();
	}

	public static void main(String[] args) {
		try {			
			//密码明文: abc@123!ye
			String strOrigPassword="918543162885eNmu738G6N2G4Fwd9980Ac";//原始密码
			//密码密文: 3164c8b1814b3daba6e2cd45c6a05c90
			String strEncryptPassword=MD5.MD5(strOrigPassword);//加密后密码 
			
			System.out.println("密码明文: "+strOrigPassword);
			System.out.println("密码密文: "+strEncryptPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}