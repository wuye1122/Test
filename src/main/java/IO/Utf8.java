package IO;

import java.io.UnsupportedEncodingException;

public class Utf8 {

	/**
	 * @author wuhl
	 * void 关于编码 乱码
	 *  JVM将 按照GBK编码将字节数组解析成字符，然后将字符转换为unicode格式的字节数组
	 *  当源码文件是UTF-8, 我们需要通知编译器源码的格式，javac -encoding utf-8 ... , 编译时，JVM按照utf-8 解析成字符，然后转换为unicode格式的字节数组， 那么不论源码文件是什么格式，同样的字符串，最后得到的unicode字节数组是完全一致的，显示的时候，也是转成GBK来显示（跟OS环境有关）
	 *  
	 * 乱码：
	 *   
	 *   字符串原本的编码格式 与 读取时解析用的编码格式不一致导致的 
	 * @throws UnsupportedEncodingException 
	 *   
	 *  
	 * 
	 */
	//简化后就是:
	public String unicodeToUtf8 (String s) throws UnsupportedEncodingException {

		return new String( s.getBytes("utf-8") , "utf-8");
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "你好哦!";
		try {
			System.out.println(s.getBytes());//其中 getBytes() 是将unicode 转换为操作系统默认的格式的字节数组，即"你好哦"的 GBK格式，
			System.out.println(s.getBytes().length);
			
			//new String (bytes, Charset) 中的charset 是指定读取 bytes 的方式，这里指定为UTF-8,即把bytes的内容当做UTF-8 格式对待。
			           // 编码方式    解码方式
			System.out.println( new String(s.getBytes(),"GBK"));
			System.out.println( new String(s.getBytes("GBK"),"GBK"));
			System.out.println( new String(s.getBytes("iso-8859-1"),"GBK"));
			
		//	System.out.println( new String(s.getBytes("UTF-8"),"GBK"));
			System.out.println( new String(s.getBytes(),"UTF-8"));
			System.out.println( new String(s.getBytes("GBK"),"UTF-8"));
			System.out.println( new String(s.getBytes("UTF-8"),"UTF-8"));

	/*		但是为什么在tomcat 下，使用 new String(s.getBytes("iso-8859-1") ,"GBK") 却可以用呢？ 答案是：
			tomcat 默认使用iso-8859-1编码， 也就是说，如果原本字符串是GBK的，tomcat传输过程中，将GBK转成iso-8859-1了，
			默认情况下，使用iso-8859-1读取中文肯定是有问题的，那么我们需要将iso-8859-1 再转成GBK， 而iso-8859-1 是单字节编码的，
			即他认为一个字节是一个字符， 那么这种转换不会对原来的字节数组做任何改变，因为字节数组本来就是由单个字节组成的，
			如果之前用GBK编码，那么转成iso-8859-1后编码内容完全没变， 则 s.getBytes("iso-8859-1")  实际上还是原来GBK的编码内容
			则 new String(s.getBytes("iso-8859-1") ,"GBK")  就可以正确解码了。 所以说这是一种巧合。
    */
			
			//利用getBytes将unicode字符串转成UTF-8格式的字节数组
			byte[] utf8Bytes = s.getBytes("UTF-8"); 
			//然后用utf-8 对这个字节数组解码成新的字符串
			String utf8Str = new String(utf8Bytes, "UTF-8");
			/*
			另外对于读写文件，
			OutputStreamWriter w1 = new OutputStreamWriter(new FileOutputStream("D:\\file1.txt"),"UTF-8");
			InputStreamReader( stream, charset)*/
			
			
						
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
