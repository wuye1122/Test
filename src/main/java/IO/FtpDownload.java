package IO;

import java.io.FileOutputStream;
import java.io.InputStream;

import net.sf.jftp.config.Settings;
import net.sf.jftp.net.BasicConnection;
import net.sf.jftp.net.ConnectionHandler;
import net.sf.jftp.net.ConnectionListener;
import net.sf.jftp.net.FtpConnection;
  
public class FtpDownload implements ConnectionListener  
{  
    // is the connection established?  
    private boolean isThere = false;  
      
    public static long time = 0;  
      
    // connection pool, not necessary but you should take a look at this class  
    // if you want to use multiple event based ftp transfers.  
    private ConnectionHandler handler = new ConnectionHandler();  
  
    private String host;  
    private int port = 21;  
    private String user;  
    private String passwd;  
    private FtpConnection con;
//    private FtpClient ftpClient;
      
    public FtpDownload(String host, int port, String user, String passwd){  
    	
        this.host = host;  
        this.port = port;  
        this.user = user;  
        this.passwd = passwd;  
    }  
    
    public void ftpLogin(String remotePath){
    	
    	// the ftp client default is very small, you may want to increase this  
        Settings.bufferSize = 16384;         
//    	Settings.
        
     // create a FtpConnection - note that it does *not* connect instantly  
//        con =new FtpConnection(host);  
        con =new FtpConnection(host, port, "");  

        
        // set updatelistener, interface methods are below  
        con.addConnectionListener(this);  
          
        // set handler  
        con.setConnectionHandler(handler);  
          
        // connect and login. this is from where connectionFailed() may be called for example  
        con.login(user, passwd);  
        con.chdir(con.getPWD()+remotePath);
        
    }
    
    public void ftpDisconnect(){
    	con.disconnect();
    }
      
    //creates a FtpConnection and downloads a file  
    //public byte[] downloadToBinary(String file,String currentDir)  
    public long downloadToBinary(String file,String currentDir)   throws Exception
    {          
    	long result = 0;
    	InputStream is = null;
    	FileOutputStream os = null;  
        long current = System.currentTimeMillis();  
        //System.out.println("1) "+(System.currentTimeMillis()-current)+"ms.");  
        
          
        // login calls connectionInitialized() below which sets isThere to true  
        while(!isThere)  
        {  
            try { Thread.sleep(10); }  
            catch(Exception ex) { ex.printStackTrace(); }  
        }  
  
        // get download input stream  
       // byte[] bytes = null;  
        try{  
            is =  con.getDownloadInputStream(file);  
            java.io.File outfile = new java.io.File(currentDir+file);
			os = new FileOutputStream(outfile);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
				result = result + c;
			}
//            ByteArrayOutputStream bais = new ByteArrayOutputStream();  
//            int bit = 0;  
//            while((bit = is.read()) != -1){  
//                bais.write(bit);  
//            }  
//            bytes = bais.toByteArray();  
        }catch(Exception e){e.printStackTrace();}  
        finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			
		}
        time = (System.currentTimeMillis()-current);  
      
        System.out.println("Download took "+time+"ms.");  
          
        return result;  
    }  
  
    // download welcome.msg from sourceforge or any other given file  
//    public static void main(String argv[])  
//    {  
//        FtpDownload f = new FtpDownload("10.130.29.62", 21, "liyp", "qnsoft"); 
//        //f.ftpLogin();
//        
//        try{
//        	long result = f.downloadToBinary("SJZ-UCD_ENTERPRISE_DEVICE_DN-20151106.zip","/file");  
//        	System.out.println(result);
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
//        
//        
//        
//    }  
  
// ------------------ needed by ConnectionListener interface -----------------  
  
    // called if the remote directory has changed  
    public void updateRemoteDirectory(BasicConnection con)  
    {  
        System.out.println("new path is: " + con.getPWD());  
    }  
  
    // called if a connection has been established  
    public void connectionInitialized(BasicConnection con)  
    {  
        isThere = true;  
    }  
   
    // called every few kb by DataConnection during the trnsfer (interval can be changed in Settings)  
    public void updateProgress(String file, String type, long bytes) {}  
  
    // called if connection fails  
    public void connectionFailed(BasicConnection con, String why) {System.out.println("connection failed!");}  
  
    // up- or download has finished  
    public void actionFinished(BasicConnection con) {}  
}  