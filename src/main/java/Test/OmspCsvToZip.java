package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Util.ZipUtils;

public class OmspCsvToZip {

	/**
	 * @author JUC
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//将文件件里面的csv->打包成zip输出
		 String path="/home/omsp/resin4.0.13/webapps/omsp/dataFile/20180502";
		   /* if (args.length > 0)
		      path = args[0];
		    else {
		      path = "F:\\JUC\\800企业\\201805\\omsp将csv转化成zip\\1";
		    }*/
		System.out.println(path);
        File file=new File(path);
        System.out.println("文件："+file.getName());
        System.out.println("文件："+file.isDirectory());
        System.out.println("文件："+file.isFile());

        if(file.isDirectory()){
        	
       	   for(File csv:file.listFiles()){
          		try {
          			/*bw = new BufferedWriter(new FileWriter(csv, true));
          		
          			bw.close();*/
          			//压缩文件
          			String name = csv.getName();
              		//压缩文件
              	    String parent=csv.getParent();
              	    System.out.println("parent:"+parent);

    				File fileFolder = new File(parent+"/zip");
    				if (!fileFolder.exists()) {
    					fileFolder.mkdirs();
        				System.out.println("fileFolder:"+fileFolder.getName());

    		    	}
    				String zipFilePath = fileFolder.getAbsolutePath()+"/"+name.split("\\.")[0]+".zip";
    				System.out.println("zipFilePath:"+zipFilePath);
    				File fi=new File(zipFilePath);
    				ZipUtils.zip(csv.getAbsolutePath(), zipFilePath);

          			//文件上传
          		} catch (Exception e) {
          			e.printStackTrace();
          		}
              }
         }
		
		
	}

}
