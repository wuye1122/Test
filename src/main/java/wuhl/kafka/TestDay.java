package wuhl.kafka;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestDay {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    Calendar c = Calendar.getInstance();
		    String endTime = sdf.format(c.getTime());
		    c.add(5, -1);
		    String startTime = sdf.format(c.getTime());

		    System.out.println(startTime);
		    System.out.println(endTime);

		    System.out.println(String.valueOf(""));
		    System.out.println(String.valueOf(null));

		    //collectData(startTime, endTime);
		
	}

}
