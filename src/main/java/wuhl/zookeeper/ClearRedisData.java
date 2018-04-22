package wuhl.zookeeper;

import java.util.ArrayList;
import java.util.List;

public class ClearRedisData {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
String a="slave-1@10.130.29.31:8080,slave-2@10.130.29.32:8080,slave-3@10.130.29.33:8080,slave-2@10.130.29.32:8080";
String b="slave-2@10.130.29.32:8080";
List<String> persistenceNodeListWithAdd =new ArrayList<String>();
persistenceNodeListWithAdd.add(a);
/*persistenceNodeListWithAdd.add("slave-1@10.130.29.31:8080");
persistenceNodeListWithAdd.add("slave-2@10.130.29.32:8080");
persistenceNodeListWithAdd.add("slave-3@10.130.29.33:8080");*/
System.out.println(persistenceNodeListWithAdd.contains(b));
System.out.println(persistenceNodeListWithAdd.size());

System.out.println(a.contains(b));
/*System.out.println(persistenceNodeListWithAdd.contains(b));
*/
String ab="1,2,3,4,5,6,7,8,9,10,11;";
String [] days=ab.split(",");
System.out.println(days.length);






	
	}

}
