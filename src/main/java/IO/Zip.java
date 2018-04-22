package IO;


import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class Zip {

	/**
	 * @author wuhl
	 * void
	 */
	public   List<String> downFile(String url, int port,String username, String password, String remotePath,String localPath,String date) throws ServerException { 
	    List<String> fileList=new ArrayList<String>();
		FTPClient ftp = new FTPClient(); 
		FtpDownload f = new FtpDownload(url,port, username,password);
		//ftp.setBufferSize(1024);
	    try { 
	        int reply; 
	        ftp.setControlEncoding("GBK");
	        ftp.connect(url, port); 
	        
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(username, password);//登录
	        f.ftpLogin(remotePath);
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            f.ftpDisconnect();
	            throw new ServerException("连接失败");
	        } 
	        ftp.changeWorkingDirectory(ftp.printWorkingDirectory()+remotePath);
	       // ftp.changeWorkingDirectory(remotePath);

	        FTPFile[] fs = ftp.listFiles(); 	
	        System.out.println("file文件下面个数："+fs.length);
	        for(FTPFile ff:fs){ 
	        	try{
					
//		                OutputStream is = new FileOutputStream(localFile);  
//		                ftp.enterLocalPassiveMode();
//		                logger.error("----------开始下载-----------");
//		                ftp.retrieveFile(ff.getName(), is);
//		                logger.error("----------结束下载-----------");
//		                is.close(); 
	        			long result = f.downloadToBinary(ff.getName(),localPath);
		                fileList.add(ff.getName());
	        	
	        		
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	                
	        } 
	        
	    } catch (IOException e) { 
	    	e.printStackTrace();
	    	throw new ServerException(e.getMessage());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw new ServerException(e.getMessage());
		}finally { 
			try {
				ftp.logout();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	        f.ftpDisconnect();
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	                f.ftpDisconnect();
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return fileList;
	} 
	public static void main(String[] args) throws ServerException {
		// TODO Auto-generated method stub
        /*  System.out.println("======进行压缩文件======");
         
           String result=HttpRequest.sendGet("http://localhost:8087/dps2/push-monitor/query.do", "");
           System.out.println(result);*/
           
       //    new  HttpClientUtil().upload();
          
       //   new Zip().downFile("221.130.101.161", 2121, "yunying", "123321", "/file", "d:/", "20170916");
	}

}
