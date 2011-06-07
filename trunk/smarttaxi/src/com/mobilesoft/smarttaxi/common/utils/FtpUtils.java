package com.mobilesoft.smarttaxi.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import sun.net.TelnetOutputStream;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * FTP 工具类
 * @author renmian
 *
 */
public class FtpUtils {
	final static Log log = LogFactory.getLog(FtpUtils.class);

	// --------------------------------------------------------------------------------------------------------
	// 上传一个文件：
	public static void doUploadFile(String FileName, String host, String userID,
			String password, String CdPath, String LocalSavePath)
			throws IOException {
		/*
		 * host:FTP上传的主机IP userID：FTP上传的用户名 password：FTP上传的密码
		 * CdPath：FTP上传将要上传到的文件目录。（文件将要传到这个目录）
		 * LocalSavePath：FTP本地文件目录。（将要上传的文件所在的本地目录）
		 * 
		 */

		/*
		 * ftp_down_host="10.156.107.85"; ftp_down_userid="root";
		 * ftp_down_pwd="xzltadm"; ftp_down_cd_path="/root/ziyuanguanli";
		 * local_file_save_path="c:\\ftptest";
		 * 
		 */

		try {
			//test
//			host = "10.105.16.60";
//			userID = "csc";
//			password  = "csc";
//			CdPath = "/AttachedFiles/";
			FtpClient ftpClient = new FtpClient(host);
			ftpClient.login(userID, password);
			ftpClient.binary();
			ftpClient.cd(CdPath);
			
			log.debug("=====cd over ");
	
			String fileName = LocalSavePath + FileName;
			log.debug("path:" + fileName);
			File file = new File(fileName);
			TelnetOutputStream out = ftpClient.put(file.getName());
			FileInputStream in = new FileInputStream(file);
			for (int c = 0; (c = in.read()) != -1;) {
				out.write(c);
			}

			in.close();
			out.close();
			ftpClient.closeServer();
			//文件上传成功后，删除该文件
			if (file.exists() && file.isFile()) {
				System.out.println("File is Exists!Delete then.");
				file.delete();
			}
			return;
		}

		catch (IOException e) {
			System.out.println("-------" + e.toString());
			e.printStackTrace();
			throw e;

		}
	}
	
//	 上传一个文件，最后不删除文件
	public static void doUploadFileNoDel(String FileName, String host, String userID,
			String password, String CdPath, String LocalSavePath)
			throws IOException {

		try {
			FtpClient ftpClient = new FtpClient(host);
			ftpClient.login(userID, password);
			ftpClient.binary();
			ftpClient.cd(CdPath);
			
			log.debug("连接FTP");
	
			String fileName = LocalSavePath + FileName;
			log.debug("path:" + fileName);
			File file = new File(fileName);
			TelnetOutputStream out = ftpClient.put(file.getName());
			FileInputStream in = new FileInputStream(file);
			for (int c = 0; (c = in.read()) != -1;) {
				out.write(c);
			}

			in.close();
			out.close();
			ftpClient.closeServer();
			return;
		}

		catch (IOException e) {
			System.out.println("连接FTP失败：" + e.toString());
			e.printStackTrace();
			throw e;

		}
	}

	// ------------------------------------------------------------------------------------------------
	// 下载一个文件：
	// 同步执行
	public synchronized static void doDownLoadFile(String FileName, String host,
			String userID, String password, String CdPath, String outdir)
			throws IOException // 被下载的文件名（不包括目录名）
	{
		/*
		 * 
		 * host：FTP下载的服务器IP。 userID：FTP下载的用户名。 password：FTP下载的用户密码。
		 * CdPath：将要下载的文件在服务器上存放的目录。
		 */

		/*
		 * host="10.156.107.85"; userID="root"; password="xzltadm";
		 * CdPath="/root/ziyuanguanli";
		 */
		FtpClient ftpClient = new FtpClient();
		ftpClient.openServer(host);
		ftpClient.login(userID, password);
		ftpClient.cd(CdPath);
		ftpClient.binary();

		TelnetInputStream is = ftpClient.get(FileName);
		// System.out.println("output file:" + outdir);

		File dir_out = new File(outdir);
		if (dir_out.isDirectory()) {
			System.out.println("Directory is Exists!Leave now.");
		} else
			dir_out.mkdir();
		String fileOut = outdir + File.separator + FileName;
		File file_out = new File(fileOut);
		if (file_out.exists() && file_out.isFile()) {
			System.out.println("File is Exists!Delete then.");
			file_out.delete();
		}
		FileOutputStream os = new FileOutputStream(file_out);
		byte[] bytes = new byte[1024];
		int c;
		while ((c = is.read(bytes)) != -1) {
			os.write(bytes, 0, c);
		}
		is.close();
		os.close();

		ftpClient.closeServer();


	}

	// ------------------------------------------------------------------------------------------------
	// 删除CdPath下的所有文件：

	public static void doDeleteTheDirFiles(String host, String userID,
			String password, String CdPath) throws IOException // 被下载的文件名（不包括目录名）
	{
		/*
		 * 
		 * host：FTP下载的服务器IP。 userID：FTP下载的用户名。 password：FTP下载的用户密码。
		 * CdPath：将要删除的文件在服务器上存放的目录。
		 */

		/*
		 * host="10.156.107.85"; userID="root"; password="xzltadm";
		 * CdPath="/root/ziyuanguanli";
		 */

		FtpClient ftpClient = new FtpClient();
		ftpClient.openServer(host);
		ftpClient.login(userID, password);
		ftpClient.cd(CdPath);
		ftpClient.binary();

		// 获取CdPath下的所有文件：
		TelnetInputStream namestream = ftpClient.list();
		int d;
		String tempstr = "";
		String[] strArr;// 实为文件信息列表。
		String FileName;
		String cmd;// 命令字符串。
		while ((d = namestream.read()) != -1) {
			tempstr += (char) d;
		}
		strArr = tempstr.split("\r\n");

		for (int i = 0; i < strArr.length; i++) {
			System.out.print("strArr0[" + i + "]:" + strArr[i] + "\r\n");
			// 文件名称
			FileName = strArr[i].substring(56).trim();
			cmd = "DELE " + FileName + "\r\n";
			ftpClient.sendServer(cmd);

		}
		namestream.close();
		ftpClient.closeServer();

	}

	public static void main(String args[]) throws Exception {
		System.out
				.println("*****************************************************");
		System.out
				.println("*                                                   *");
		System.out
				.println("*             please wait                           *");
		System.out
				.println("*                                                   *");
		System.out
				.println("*****************************************************");

		try {
			/*
			 * DownLoadFile("10.156.107.85","root","xzltadm",
			 * "c:\\ftptest","/root/ziyuanguanli");
			 */

		} catch (Exception exception) {
			System.out.println("失败:原因：" + exception.getMessage());
		}
		System.out
				.println("*****************************************************");
		System.out
				.println("*                                                   *");
		System.out
				.println("*             programme over                        *");
		System.out
				.println("*                                                   *");
		System.out
				.println("*****************************************************");
	}

}
