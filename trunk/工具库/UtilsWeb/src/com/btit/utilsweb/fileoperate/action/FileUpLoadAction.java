package com.btit.utilsweb.fileoperate.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//文件上传
public class FileUpLoadAction extends ActionSupport {
	/**
	 * 文件上传处理
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private File[] file;
	 //上传文件的类型数组
	private String[] fileContentType;
	// 上传文件文件名
	private String[] fileFileName;

	
	//存储上传文件的目录
    private String savePath;

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@SuppressWarnings("deprecation")
	
	 public String upload() throws Exception
	{       File[] files;
		try{ files = getFile();}catch(Exception e){message="你没有上传任何文件！";return INPUT;}
		FileOutputStream fos=null;
		FileInputStream fis=null;
		if(files==null||files.length==0){message="你没有上传任何文件！";return INPUT;}
		for (int i = 0 ; i < files.length ; i++)
		{
			//以服务器的文件保存地址和原文件名建立上传文件输出流
			String realFilePath = ServletActionContext.getRequest().getRealPath(getSavePath());
			 fos = new FileOutputStream(realFilePath + "\\" + getFileFileName()[i]);
			try{ fis = new FileInputStream(files[i]);}catch(Exception e){message="你没有上传任何文件！";return INPUT;}
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer , 0 , len);
			}
		}
         fos.close();// 注意：流应当关闭。
         fis.close();
         message="上传成功";
       return SUCCESS;
	}

	

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}



	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
