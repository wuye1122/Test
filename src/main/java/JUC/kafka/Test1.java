package JUC.kafka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, String> map = new HashMap<String, String>();

		try {
			// read file content from file
			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader(
					"writeFile:\\Users\\Administrator.USER-20161101FI\\Desktop\\800企业\\今日头条接口\\new_r_ags_e_back.json");
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			int a=0;
			while ((str = br.readLine()) != null) {
				sb.append(str + "/n");
		        a++;
		        
		          System.out.println("new_r_ags_e"+":"+str);

				}
			System.out.println(a);


	}catch(Exception e){
		e.printStackTrace();
		} 
	}

}
