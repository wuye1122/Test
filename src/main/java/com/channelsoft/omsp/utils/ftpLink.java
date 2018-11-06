package com.channelsoft.omsp.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class ftpLink {
	public static final String FILEPATH = "/file";//ftp.remotePath=/file


	/**
	 * Description: 向FTP服务器上传文件 @Version1.0
	 *
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static String uploadFile(String url, int port, String username, String password, String path,
									String filename, InputStream input) {
		String success = "上传失败";
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.setControlEncoding("GBK");
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			//查看返回值
			reply = ftp.getReplyCode();
			//如果不是(reply >= 200 && reply < 300)，则表明登陆失败
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			path = FILEPATH.split("/")[1];
			FTPFile[] fs = ftp.listFiles();
			int i = 0;
			//遍历根目录，判断是否存在file文件夹
			for (FTPFile ff : fs) {
				if (ff.getName().equals(path)) {
					i++;
				}
			}
			path = FILEPATH;
			//window
//			String ftpPath = "./" + path;
			//linux
			String ftpPath = "." + File.separator + path;
			if (i == 0) {
				boolean directory = ftp.makeDirectory(ftpPath);
				System.out.println("创建目录：" + ftpPath + "创建结果：" + directory);
			}
			// ftp.changeWorkingDirectory(File.separator+path);
			ftp.enterLocalPassiveMode();
			ftp.changeWorkingDirectory(ftpPath);
			boolean storeFile = ftp.storeFile(filename, input);
			if (storeFile) {
				success = "上传成功";
			} else {
				// 失败重传
				for (int m = 0; m < 5; m++) {
					storeFile = ftp.storeFile(filename, input);
					while (storeFile) {
						break;
					}
				}
				if (storeFile) {
					success = "上传成功";
				} else {
					success = "上传失败";
				}
			}
			input.close();
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * Description: 从FTP服务器下载文件 @Version1.0
	 *
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 * @throws ServerException
	 */
	public static List<String> downFile(String url, int port, String username, String password, String remotePath,
								 String localPath, String date) throws ServerException {
		List<String> fileList = new ArrayList<String>();
		FTPClient ftp = new FTPClient();
		FtpDownload f = new FtpDownload(url, port, username, password);
		// ftp.setBufferSize(1024);
		try {
			int reply;
			ftp.setControlEncoding("GBK");
			ftp.connect(url, port);

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			f.ftpLogin(remotePath);

			// ftp client告诉ftp server开通一个端口来传输数据。为什么要这样做呢，
			// 因为ftp server可能每次开启不同的端口来传输数据，但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
			ftp.enterLocalPassiveMode();
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				f.ftpDisconnect();
				throw new ServerException("连接失败");
			}
			ftp.changeWorkingDirectory(ftp.printWorkingDirectory() + remotePath);
			FTPFile[] fs = ftp.listFiles();
			System.out.println("ftp:"+url+","+ftp.printWorkingDirectory() + "路径下的文件个数：" + fs.length);
			for (FTPFile ff : fs) {

				System.out.println(ff);
				try {
					if (ff.getName().contains("-")||ff.getName().endsWith(date + ".zip") || isHourTable(ff.getName(), date)) {
						File localFile = new File(localPath + "/" + ff.getName());
						if (!localFile.getParentFile().exists()) {
							localFile.getParentFile().mkdirs();
						}
						// OutputStream is = new FileOutputStream(localFile);
						// ftp.enterLocalPassiveMode();
						// System.out.println("----------开始下载-----------");
						// ftp.retrieveFile(ff.getName(), is);
						// System.out.println("----------结束下载-----------");
						// is.close();
						System.out.println("----------开始下载-----------");
						long result = f.downloadToBinary(ff.getName(), localPath);
						System.out.println("----------结束下载-----------");
						System.out.println(ff.getName() + "result:" + result);
						fileList.add(ff.getName());
					}

				} catch (Exception e) {
					e.printStackTrace();
					// String platName=ff.getName().split("-")[0];
					// errorInfoMonitor.addErrorInfo("10002",
					// ff.getName()+"-"+url, "解析服务");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		} finally {
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

	// 删除文件至FTP通用方法
	public static void deleteFile(String url, int port, String userName, String passWord, String remotePath, String fileName) {
		FTPClient ftpClient = null;
		// String platName=fileName.split("-")[0];
		try {
			int reply;
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("GBK");
			ftpClient.connect(url, port);
			ftpClient.login(userName, passWord);
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}
			ftpClient.changeWorkingDirectory(ftpClient.printWorkingDirectory() + remotePath);// 转移到FTP服务器目录

			boolean f = ftpClient.deleteFile(fileName);
			ftpClient.logout();
			if (f) {
				System.out.println(url + "上的文件" + remotePath + fileName + "删除成功！");
			} else {
				System.out.println("10007:" + url + "上的文件" + remotePath + fileName + "删除失败！");
			}

		} catch (Exception e) {
			System.out.println("10007:" + url + "上的文件" + remotePath + fileName + "删除失败！" );
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}

	public static boolean isHourTable(String fileName, String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dateToday = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToday);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String today = sdf.format(calendar.getTime());
		List<String> hourTableList = new ArrayList<String>();
		String tableName = fileName.split("-")[1];

		int length = fileName.split("-").length;
		String fileDate = fileName.split("-")[length - 1].substring(0,
				fileName.split("-")[length - 1].lastIndexOf("."));

		hourTableList.add("UCD_ENTERPRISE_AGENT_INFO_LOG");
		hourTableList.add("UCD_ENTERPRISE_DEVICE_DN_LOG");
		hourTableList.add("GLS_OPERATION_LOG");
		hourTableList.add("GLS_OPERATION_LOG_DETAIL");

		if (hourTableList.contains(tableName) && today.equals(fileDate)) {
			return true;
		}

		return false;

	}
}
