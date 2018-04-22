package IO;

import java.io.UnsupportedEncodingException;

public class Utf8 {

	/**
	 * @author wuhl
	 * void ���ڱ��� ����
	 *  JVM�� ����GBK���뽫�ֽ�����������ַ���Ȼ���ַ�ת��Ϊunicode��ʽ���ֽ�����
	 *  ��Դ���ļ���UTF-8, ������Ҫ֪ͨ������Դ��ĸ�ʽ��javac -encoding utf-8 ... , ����ʱ��JVM����utf-8 �������ַ���Ȼ��ת��Ϊunicode��ʽ���ֽ����飬 ��ô����Դ���ļ���ʲô��ʽ��ͬ�����ַ��������õ���unicode�ֽ���������ȫһ�µģ���ʾ��ʱ��Ҳ��ת��GBK����ʾ����OS�����йأ�
	 *  
	 * ���룺
	 *   
	 *   �ַ���ԭ���ı����ʽ �� ��ȡʱ�����õı����ʽ��һ�µ��µ� 
	 * @throws UnsupportedEncodingException 
	 *   
	 *  
	 * 
	 */
	//�򻯺����:
	public String unicodeToUtf8 (String s) throws UnsupportedEncodingException {

		return new String( s.getBytes("utf-8") , "utf-8");
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "���Ŷ!";
		try {
			System.out.println(s.getBytes());//���� getBytes() �ǽ�unicode ת��Ϊ����ϵͳĬ�ϵĸ�ʽ���ֽ����飬��"���Ŷ"�� GBK��ʽ��
			System.out.println(s.getBytes().length);
			
			//new String (bytes, Charset) �е�charset ��ָ����ȡ bytes �ķ�ʽ������ָ��ΪUTF-8,����bytes�����ݵ���UTF-8 ��ʽ�Դ���
			           // ���뷽ʽ    ���뷽ʽ
			System.out.println( new String(s.getBytes(),"GBK"));
			System.out.println( new String(s.getBytes("GBK"),"GBK"));
			System.out.println( new String(s.getBytes("iso-8859-1"),"GBK"));
			
		//	System.out.println( new String(s.getBytes("UTF-8"),"GBK"));
			System.out.println( new String(s.getBytes(),"UTF-8"));
			System.out.println( new String(s.getBytes("GBK"),"UTF-8"));
			System.out.println( new String(s.getBytes("UTF-8"),"UTF-8"));

	/*		����Ϊʲô��tomcat �£�ʹ�� new String(s.getBytes("iso-8859-1") ,"GBK") ȴ�������أ� ���ǣ�
			tomcat Ĭ��ʹ��iso-8859-1���룬 Ҳ����˵�����ԭ���ַ�����GBK�ģ�tomcat��������У���GBKת��iso-8859-1�ˣ�
			Ĭ������£�ʹ��iso-8859-1��ȡ���Ŀ϶���������ģ���ô������Ҫ��iso-8859-1 ��ת��GBK�� ��iso-8859-1 �ǵ��ֽڱ���ģ�
			������Ϊһ���ֽ���һ���ַ��� ��ô����ת�������ԭ�����ֽ��������κθı䣬��Ϊ�ֽ����鱾�������ɵ����ֽ���ɵģ�
			���֮ǰ��GBK���룬��ôת��iso-8859-1�����������ȫû�䣬 �� s.getBytes("iso-8859-1")  ʵ���ϻ���ԭ��GBK�ı�������
			�� new String(s.getBytes("iso-8859-1") ,"GBK")  �Ϳ�����ȷ�����ˡ� ����˵����һ���ɺϡ�
    */
			
			//����getBytes��unicode�ַ���ת��UTF-8��ʽ���ֽ�����
			byte[] utf8Bytes = s.getBytes("UTF-8"); 
			//Ȼ����utf-8 ������ֽ����������µ��ַ���
			String utf8Str = new String(utf8Bytes, "UTF-8");
			/*
			������ڶ�д�ļ���
			OutputStreamWriter w1 = new OutputStreamWriter(new FileOutputStream("D:\\file1.txt"),"UTF-8");
			InputStreamReader( stream, charset)*/
			
			
						
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
