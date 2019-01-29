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
		//���ļ��������csv->�����zip���
		 String path="/home/omsp/resin4.0.13/webapps/omsp/dataFile/20180502";
		   /* if (args.length > 0)
		      path = args[0];
		    else {
		      path = "F:\\JUC\\800��ҵ\\201805\\omsp��csvת����zip\\1";
		    }*/
		System.out.println(path);
        File file=new File(path);
        System.out.println("�ļ���"+file.getName());
        System.out.println("�ļ���"+file.isDirectory());
        System.out.println("�ļ���"+file.isFile());

        if(file.isDirectory()){
        	
       	   for(File csv:file.listFiles()){
          		try {
          			/*bw = new BufferedWriter(new FileWriter(csv, true));
          		
          			bw.close();*/
          			//ѹ���ļ�
          			String name = csv.getName();
              		//ѹ���ļ�
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

          			//�ļ��ϴ�
          		} catch (Exception e) {
          			e.printStackTrace();
          		}
              }
         }
		
		
	}

}
