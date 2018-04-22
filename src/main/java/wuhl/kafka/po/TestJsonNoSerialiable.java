package wuhl.kafka.po;

public class TestJsonNoSerialiable {

	   public String name;
	   public String address;
	   public transient int SSN;
	   public int number;
	   public void mailCheck()
	   {
	      System.out.println("Mailing a check to " + name
	                           + " " + address);
	   }
	@Override
	public String toString() {
		return "TestJsonNoSerialiable [name=" + name + ", address=" + address
				+ ", SSN=" + SSN + ", number=" + number + "]";
	}
	

}
