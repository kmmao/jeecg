package org.jeecgframework.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Properties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传下载工具类
 */
public class FtpUtil {
	 public static String ftpUrl;
	 public static Integer port;
	 public static String userName;
	 public static String passWord;
	 public static String basePath;
	 public static String projectName;
	 public static Boolean isStart = false;
     
     public static void getFtpConf(){
		 PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
		 Properties prop = ftpConfig.getProperties();
		 ftpUrl=prop.getProperty("ftp.url");
		 String portStr =prop.getProperty("ftp.port");
		 if(portStr !=null && !"".equals(portStr)){
			 port= Integer.valueOf(portStr);
		 }
		 userName=prop.getProperty("ftp.userName");
		 passWord=prop.getProperty("ftp.passWord");
		 basePath=prop.getProperty("ftp.basePath");
		 projectName=prop.getProperty("ftp.projectName");
		 if("true".equals(prop.getProperty("ftp.isStart"))){
			 isStart = true;
		 }
     }
     
     public static Boolean isStart(){
    	 PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
		 Properties prop = ftpConfig.getProperties();
		 if("true".equals(prop.getProperty("ftp.isStart"))){
			 isStart = true;
		 }
		 return isStart;
     }
     
     public static String getFtpUrl(){
    	 PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
		 Properties prop = ftpConfig.getProperties();
		 ftpUrl=prop.getProperty("ftp.url");
		 return ftpUrl;
     }
     public static String getProjectName(){
    	 PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
    	 Properties prop = ftpConfig.getProperties();
    	 projectName=prop.getProperty("ftp.projectName");
    	 return projectName;
     }
 	/** 
 	 * Description: 向FTP服务器上传文件 
 	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
 	 * @param filename 上传到FTP服务器上的文件名 
 	 * @param input 输入流 
 	 * @return 成功返回true，否则返回false 
 	 */
	 public static boolean uploadFile(String filePath, String filename, InputStream input) {
		 getFtpConf();
		 return uploadFile(ftpUrl,  port,  userName, passWord,  basePath,filePath,  filename,  input);
	 }
	 
	 /** 
	 * Description: 从FTP服务器下载文件 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return true 成功,false 失败
	 */
	public static boolean downloadFile(String fileName, String localPath) {
		 getFtpConf();
		return  downloadFile( ftpUrl,  port,  userName,  passWord,  basePath, fileName,  localPath);
	}
	
	/**
	 * ftp删除文件
	 * @param path ftp服务器文件存放路径
	 * @return true 成功,false 失败
	 */
	public static boolean delFtpFile(String path){
		getFtpConf();
		return delFtpFile(ftpUrl, port, userName,  passWord, path);
	}
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */
	 public static boolean uploadFile(String host, int port, String username, String password, String basePath,
			String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			//切换到上传目录
			if (!ftp.changeWorkingDirectory(basePath+filePath)) {
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//上传文件
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
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
		return result;
	}
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */
	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());


					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}


			ftp.logout();
			result = true;
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
		return result;
	}
	
	/**
	 * ftp删除文件
	 * @param url ftp地址
	 * @param port ftp端口
	 * @param userName 登录名
	 * @param passWord 密码
	 * @param path ftp服务器文件存放路径
	 * @return true 成功,false 失败
	 */
	@SuppressWarnings("unused")
	private static boolean delFtpFile(String url,int port,String userName, String passWord,String path){
		boolean success=false;
		FTPClient ftp=new FTPClient();
		try {
			ftp.setControlEncoding("UTF-8");
			ftp.connect(url, port);//连接
			ftp.login(userName, passWord);//登录
			int replyCode = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(replyCode)){
				ftp.disconnect();
				return success;
			}
			String fileName=path.substring(path.lastIndexOf("/")+1);
			ftp.changeWorkingDirectory(path.substring(0, path.lastIndexOf("/")));
			ftp.deleteFile(new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
			ftp.logout();
			success=true;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ftp.isConnected()){
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	/*public static void main(String[] args) {
		try {
			FileInputStream in=new FileInputStream(new File("E:/a.jpg"));
			//boolean flag = uploadFile("192.168.8.118", 21, "ftpuser", "Password", "/home/ftpuser/","/", "abc1.png", in);
			//boolean flag = uploadFile("192.168.8.118", 21, "ftpuser", "Password", "/data/ftp/pub","/aaa/bbb", "abc1.png", in);
			boolean flag = uploadFile("/aaa/bbb", "abc1.png", in);
			//delFtpFile("/aaa");
			System.out.println(flag);
			} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}*/
}
