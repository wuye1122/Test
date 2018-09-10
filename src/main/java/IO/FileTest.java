package IO;

import java.io.File;

public class FileTest {

	/**
	 * @author wuhl
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="writeFile:/Users/Administrator.USER-20161101FI/Desktop/800∆Û“µ/plugin";
              File file=new File(path);
	          
     
               
              if(file.isDirectory()){
            	   for(String fileJar:file.list()){
                 	  System.out.println(fileJar);
                 	  if(fileJar.endsWith(".jar")){
                 		  File fileAdd=new File(file.getPath()+"/"+fileJar);
                 		  System.out.println(fileAdd);
                 	  }
                   }
              }
           
	             
	}

}
