package JUC.kafka.po;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestJson {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   File file = new File("E:\\workspace\\Test\\src\\main\\java\\JUC\\kafka\\po\\CallDetailPoJson");
			
		   
		   try {
	        	
	        	//1全部读取出来
				//String content = FileUtils.readFileToString(file);
	        	
	        	//2分行读取
			StringBuffer sb = new StringBuffer("");
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String str = null;

			while ((str = br.readLine()) != null) {
/*				sb.append(str + "/n");
*/				System.out.println(str);
			}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
