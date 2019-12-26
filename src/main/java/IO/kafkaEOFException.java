package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import JUC.kafka.po.CallDetailPo;

public class kafkaEOFException {

	/**
	 * @author JUC
	 * void
	 * 
	 * EOFException :end of file 到达文件结尾了
	 * 
	 */



public static void main(String[] args) {
	// TODO Auto-generated method stub
	CallDetailPo user1=new CallDetailPo("weqeq","29");
	CallDetailPo user2=new CallDetailPo("weqeq","29");

	  /*   User user1=new User("yiwangzhibujian",27);
	    User user2=new User("laizhezhikezhui",24);*/
	    
	    ByteArrayOutputStream bos=new ByteArrayOutputStream();
	    ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
		
	    
	    oos.writeObject(user1);
	    oos.writeObject(user2);
	    oos.writeObject(null);
	    
	    byte[] data = bos.toByteArray();
	    ByteArrayInputStream bis=new ByteArrayInputStream(data);
	    ObjectInputStream ois=new ObjectInputStream(bis);
	    

			System.out.println(ois.readObject());	
	    System.out.println(ois.readObject());
	    System.out.println(ois.readObject());
	    System.out.println(ois.readObject());
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
