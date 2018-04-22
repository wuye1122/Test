package wuhl.reflect;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SessionUtil {

	private static final int SESSION_ID_BYTES = 16;

	public static synchronized String generateSessionId() {

		// Generate a byte array containing a session identifier
		Random random = new SecureRandom(); // ȡ�����������, Ĭ����SecureRandom
		byte bytes[] = new byte[SESSION_ID_BYTES];
		random.nextBytes(bytes); // ����16�ֽڵ�byte
		bytes = getDigest().digest(bytes); // ȡժҪ,Ĭ����"MD5"�㷨

		// Render the result as a String of hexadecimal digits
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) { // ת��Ϊ16�����ַ���
			byte b1 = (byte) ((bytes[i] & 0xf0) >> 4);
			byte b2 = (byte) (bytes[i] & 0x0f);
			if (b1 < 10)
				result.append((char) ('0' + b1));
			else
				result.append((char) ('A' + (b1 - 10)));
			if (b2 < 10)
				result.append((char) ('0' + b2));
			else
				result.append((char) ('A' + (b2 - 10)));
		}
		return (result.toString());

	}

	private static MessageDigest getDigest() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String sid = SessionUtil.generateSessionId();
		System.out.println("jsessionid=" + sid);
	
		long start = System.currentTimeMillis();
		for(int i=0;i<50000;i++){
			System.out.println("session id :"+SessionUtil.generateSessionId());
		}
		   System.out.println("redis��ȡrequestId,����ʱ:(" + (System.currentTimeMillis() - start) + ")ms");
	}
}