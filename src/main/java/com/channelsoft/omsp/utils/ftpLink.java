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
	 * Description: ��FTP�������ϴ��ļ� @Version1.0
	 *
	 * @param url
	 *            FTP������hostname
	 * @param port
	 *            FTP�������˿�
	 * @param username
	 *            FTP��¼�˺�
	 * @param password
	 *            FTP��¼����
	 * @param path
	 *            FTP����������Ŀ¼
	 * @param filename
	 *            �ϴ���FTP�������ϵ��ļ���
	 * @param input
	 *            ������
	 * @return �ɹ�����true�����򷵻�false
	 */
	public static String uploadFile(String url, int port, String username, String password, String path,
									String filename, InputStream input) {
		String success = "�ϴ�ʧ��";
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.setControlEncoding("GBK");
			ftp.connect(url, port);// ����FTP������
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.login(username, password);// ��¼
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			//�鿴����ֵ
			reply = ftp.getReplyCode();
			//�������(reply >= 200 && reply < 300)���������½ʧ��
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			path = FILEPATH.split("/")[1];
			FTPFile[] fs = ftp.listFiles();
			int i = 0;
			//������Ŀ¼���ж��Ƿ����file�ļ���
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
				System.out.println("����Ŀ¼��" + ftpPath + "���������" + directory);
			}
			// ftp.changeWorkingDirectory(File.separator+path);
			ftp.enterLocalPassiveMode();
			ftp.changeWorkingDirectory(ftpPath);
			boolean storeFile = ftp.storeFile(filename, input);
			if (storeFile) {
				success = "�ϴ��ɹ�";
			} else {
				// ʧ���ش�
				for (int m = 0; m < 5; m++) {
					storeFile = ftp.storeFile(filename, input);
					while (storeFile) {
						break;
					}
				}
				if (storeFile) {
					success = "�ϴ��ɹ�";
				} else {
					success = "�ϴ�ʧ��";
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
	 * Description: ��FTP�����������ļ� @Version1.0
	 *
	 * @param url
	 *            FTP������hostname
	 * @param port
	 *            FTP�������˿�
	 * @param username
	 *            FTP��¼�˺�
	 * @param password
	 *            FTP��¼����
	 * @param remotePath
	 *            FTP�������ϵ����·��
	 *            Ҫ���ص��ļ���
	 * @param localPath
	 *            ���غ󱣴浽���ص�·��
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

			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.login(username, password);// ��¼
			f.ftpLogin(remotePath);

			// ftp client����ftp server��ͨһ���˿����������ݡ�ΪʲôҪ�������أ�
			// ��Ϊftp server����ÿ�ο�����ͬ�Ķ˿����������ݣ�������linux�ϣ����ڰ�ȫ���ƣ�����ĳЩ�˿�û�п��������Ծͳ�������
			ftp.enterLocalPassiveMode();
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				f.ftpDisconnect();
				throw new ServerException("����ʧ��");
			}
			ftp.changeWorkingDirectory(ftp.printWorkingDirectory() + remotePath);
			FTPFile[] fs = ftp.listFiles();
			System.out.println("ftp:"+url+","+ftp.printWorkingDirectory() + "·���µ��ļ�������" + fs.length);
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
						// System.out.println("----------��ʼ����-----------");
						// ftp.retrieveFile(ff.getName(), is);
						// System.out.println("----------��������-----------");
						// is.close();
						System.out.println("----------��ʼ����-----------");
						long result = f.downloadToBinary(ff.getName(), localPath);
						System.out.println("----------��������-----------");
						System.out.println(ff.getName() + "result:" + result);
						fileList.add(ff.getName());
					}

				} catch (Exception e) {
					e.printStackTrace();
					// String platName=ff.getName().split("-")[0];
					// errorInfoMonitor.addErrorInfo("10002",
					// ff.getName()+"-"+url, "��������");
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

	// ɾ���ļ���FTPͨ�÷���
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
			ftpClient.changeWorkingDirectory(ftpClient.printWorkingDirectory() + remotePath);// ת�Ƶ�FTP������Ŀ¼

			boolean f = ftpClient.deleteFile(fileName);
			ftpClient.logout();
			if (f) {
				System.out.println(url + "�ϵ��ļ�" + remotePath + fileName + "ɾ���ɹ���");
			} else {
				System.out.println("10007:" + url + "�ϵ��ļ�" + remotePath + fileName + "ɾ��ʧ�ܣ�");
			}

		} catch (Exception e) {
			System.out.println("10007:" + url + "�ϵ��ļ�" + remotePath + fileName + "ɾ��ʧ�ܣ�" );
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
