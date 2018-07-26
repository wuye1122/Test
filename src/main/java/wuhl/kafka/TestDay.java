package wuhl.kafka;

import kafka.producer.KeyedMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDay {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
			// read file content from file
			int a=0;
			while(true){
				//持续推送15min
				try {
					a++;
					Thread.sleep(1000);
					System.out.println(System.currentTimeMillis()-start);

					if(System.currentTimeMillis()-start==900000){
						//当前推送900000
						return;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		/*long start = System.currentTimeMillis();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    Calendar c = Calendar.getInstance();
		    String endTime = sdf.format(c.getTime());
		    c.add(5, -1);
		    String startTime = sdf.format(c.getTime());

		    System.out.println(startTime);
		    System.out.println(endTime);

		    System.out.println(String.valueOf(""));
		    System.out.println(String.valueOf(null));
*/
		    //collectData(startTime, endTime);
		
	}

}
