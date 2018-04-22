package wuhl.kafka.po;

import java.util.HashMap;

public class ABCD {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> map=new HashMap<String,Integer>();
       map.put("1", 10);
       map.put("2", 0);
       map.put("3", 0);
       System.out.println(map.get("5"));
/*     System.out.println(map.get("5")-map.get("3"));
     System.out.println(map.get("5")+map.get("3"));*/
     System.out.println(map.get("2")+map.get("3"));
     System.out.println(map.get("2")-map.get("3"));





	}

}
