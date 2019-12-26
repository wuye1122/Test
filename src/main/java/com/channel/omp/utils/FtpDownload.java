package com.channel.omp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import Util.ZipUtils;
import net.sf.jftp.config.Settings;
import net.sf.jftp.net.BasicConnection;
import net.sf.jftp.net.ConnectionHandler;
import net.sf.jftp.net.ConnectionListener;
import net.sf.jftp.net.FtpConnection;

public class FtpDownload implements ConnectionListener {
	public static final String FTP_REMOTEPATH = "/file";
	public static final String FILEPATH = "D:\\tenp\\";



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
	// private FtpClient ftpClient;

	public FtpDownload(String host, int port, String user, String passwd) {

		this.host = host;
		this.port = port;
		this.user = user;
		this.passwd = passwd;
	}

	public void ftpLogin(String remotePath) {

		// the ftp client default is very small, you may want to increase this
		Settings.bufferSize = 16384;
		// Settings.

		// create a FtpConnection - note that it does *not* connect instantly
		// con =new FtpConnection(host);
		con = new FtpConnection(host, port, "");

		// set updatelistener, interface methods are below
		con.addConnectionListener(this);

		// set handler
		con.setConnectionHandler(handler);

		// connect and login. this is from where connectionFailed() may be
		// called for example
		con.login(user, passwd);
		con.chdir(con.getPWD() + remotePath);

	}

	public void ftpDisconnect() {
		con.disconnect();
	}

	// creates a FtpConnection and downloads a file
	// public byte[] downloadToBinary(String file,String currentDir)
	public long downloadToBinary(String file, String currentDir) throws Exception {

		long result = 0;
		InputStream is = null;
		FileOutputStream os = null;
		long current = System.currentTimeMillis();
		// System.out.println("1) "+(System.currentTimeMillis()-current)+"ms.");

		// login calls connectionInitialized() below which sets isThere to true
		while (!isThere) {
			try {
				Thread.sleep(10);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// get download input stream
		// byte[] bytes = null;
		try {
			is = con.getDownloadInputStream(file);
			java.io.File outfile = new java.io.File(currentDir + file);
			os = new FileOutputStream(outfile);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
				result = result + c;
			}
			// ByteArrayOutputStream bais = new ByteArrayOutputStream();
			// int bit = 0;
			// while((bit = is.read()) != -1){
			// bais.write(bit);
			// }
			// bytes = bais.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}

		}
		time = (System.currentTimeMillis() - current);

		System.out.println("Download took " + time + "ms.");

		return result;
	}

	// download welcome.msg from sourceforge or any other given file
	// public static void main(String argv[])
	// {
	// FtpDownload f = new FtpDownload("10.130.29.62", 21, "liyp", "qnsoft");
	// //f.ftpLogin();
	//
	// try{
	// long result =
	// f.downloadToBinary("SJZ-UCD_ENTERPRISE_DEVICE_DN-20151106.zip","/file");
	// System.out.println(result);
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	//
	//
	//
	// }

	// ------------------ needed by ConnectionListener interface
	// -----------------

	// called if the remote directory has changed
	public void updateRemoteDirectory(BasicConnection con) {
		System.out.println("new path is: " + con.getPWD());
	}

	// called if a connection has been established
	public void connectionInitialized(BasicConnection con) {
		isThere = true;
	}

	// called every few kb by DataConnection during the trnsfer (interval can be
	// changed in Settings)
	public void updateProgress(String file, String type, long bytes) {
	}

	// called if connection fails
	public void connectionFailed(BasicConnection con, String why) {
		System.out.println("connection failed!");
	}

	// up- or download has finished
	public void actionFinished(BasicConnection con) {
	}


	/*
	 * ��ȡ����ƽ̨ƽ̨FTP�ϵ�zip�ļ� info��ftp��Ϣ date����ȡĳ���ڵ��ļ�,���Ϊ�գ���������е�zip�ļ�������
	 */
	public void downloadFile(FTPInfo info, String date) {
		String url = info.getIp();
		int port = info.getPort();
		String userName = info.getUserName();
		String passWord = info.getPassword();
		try {
			// ��ftp������ѹ���ļ�
			List<String> fileList = ftpLink.downFile(url, port, userName, passWord, FTP_REMOTEPATH, FILEPATH, date);
			for (String name : fileList) {
				File file = new File(FILEPATH + name);
				System.out.println("file:"+file.getName());
				manageFile(info, file, date);
			}
			// File file = new File(FILEPATH);
			// if (!file.isDirectory()) {
			// logger.debug(file.getPath() + "����Ŀ���ļ��С���");
			// } else {
			// ��ȡ��ftp���ص������ļ�
			// File[] fs = file.listFiles(new FileFilter() {
			// public boolean accept(File f) {
			// if (f.isFile() && f.getName().indexOf(".zip") > -1&&f.isFile()) {
			// return true;
			// } else {
			// return false;
			// }
			// }
			// });
			// �����ļ�
			// for (File f : fs) {
			// if(fileList.contains(f.getName())){
			// manageFile(info,f,date);
			// }
			// }
			// }
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

	/**
	 * ���������������ļ� // ��ѹ���ļ�������ѹ���ɹ���ɾ��ftp�ϵĸ��ļ�����ֹ������أ� //
	 * ����ѹ�����ɹ�����ɾ�����ص�ѹ�������ȴ��´�ɨ����������أ�
	 *
	 * @param f
	 */
	public void manageFile(FTPInfo info, File f, String date) {
		String url = info.getIp();
		int port = info.getPort();
		String userName = info.getUserName();
		String passWord = info.getPassword();
		try {
			int length = f.getName().split("-").length;
			String fileDate = f.getName().split("-")[length - 1].substring(0,
					f.getName().split("-")[length - 1].lastIndexOf("."));
			// if(date.equals(fileDate)){
			System.out.println("fileDate:"+fileDate);
			ZipUtils.unZip(f.getAbsolutePath(), FILEPATH + fileDate);
		//	ftpLink.deleteFile(url, port, userName, passWord, FTP_REMOTEPATH, f.getName());
			// }
		} catch (Exception se) {

			boolean flag = f.delete();
			if (flag == true) {
				System.out.println("���ص�zip�ļ�" + f.getName() + "ɾ���ɹ���");
			} else {
				System.out.println("���ص�zip�ļ�" + f.getName() + "ɾ���ɹ���====");
			}

		}
	}


	public static void main(String[] args) {
		FTPInfo info = new FTPInfo();
		//59.172.106.170	12,007	omsp	qnsoft	�人����/ʯ��ׯ����/ͨ�����������/ͨ�ݲ��������/Ϋ����ϴ/Ϋ������/Ϋ����������	WHYG/SJZYG/TZCXYGC/TZSXYGC/WFQX/WFYG/WFXSYG
	    //221.192.131.33	21	hbftp	hbftpqnsoft	ʯ��ׯ	SJZ
		info.setIp("59.172.106.170");
		info.setUserName("omsp");
		info.setPassword("qnsoft");
		info.setPort(12007);
		FtpDownload ftp = new FtpDownload("59.172.106.170",12007,"omsp","qnsoft");
		ftp.downloadFile(info,"20180801");
	}
}