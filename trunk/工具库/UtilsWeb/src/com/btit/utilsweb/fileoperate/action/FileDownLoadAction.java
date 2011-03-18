package com.btit.utilsweb.fileoperate.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream; 
import java.io.UnsupportedEncodingException; 



import org.apache.struts2.ServletActionContext; 

import com.opensymphony.xwork2.ActionSupport;
 
public class FileDownLoadAction extends ActionSupport { 
    private static final long serialVersionUID = 6329383258366253255L; 
 
    private String fileName; 
    
    private String savePath;
    
    private FileInputStream downloadFile;
 
//    public void setFileName(){ 
//        //得到请求下载的文件名 
//        String fname=ServletActionContext.getRequest().getParameter("name");  
//        try { 
//        /* 
//         * 对fname参数进行UTF-8解码,注意:实际进行UTF-8解码时会使用本地编码，本机为GBK。 
//         * 这里使用request.setCharacterEncoding解码无效. 
//         * 只有解码了getDownloadFile()方法才能在下载目录下正确找到请求的文件 
//         * */   
//              fname = new String(fname.getBytes("ISO-8859-1"), "UTF-8"); 
// 
//      } catch (Exception e) { 
//            e.printStackTrace(); 
//       }   
//       this.fileName=fname;   
//      
//   } 
 
      /* 
       * @getFileName 
       * 此方法对应的是struts.xml文件中的： 
       * <param name="contentDisposition">attachment;filename="${fileName}"</param> 
       * 这个属性设置的是下载工具下载文件时显示的文件名， 
       * 要想正确的显示中文文件名，我们需要对fileName再次编码 
       * 否则中文名文件将出现乱码，或无法下载的情况 
       * */ 
//     public String getFileName() throws UnsupportedEncodingException { 
//  
//         fileName=new String(fileName.getBytes(),"ISO-8859-1"); 
//  
//         return fileName; 
//     } 
 
    /* 
      * @getDownloadFile 
      * 此方法对应的是struts.xml文件中的： 
      * <param name="inputName">downloadFile</param> 
      * 返回下载文件的流，可以参看struts2的源码 
      * */ 
//     public InputStream getDownloadFile() { 
//  
//        this.setFileName(); 
// 
//        return ServletActionContext.getServletContext().getResourceAsStream(getSavePath() + fileName); 
//     } 
 
    @Override 
     public String execute() throws Exception { 
    	 String realFilePath = ServletActionContext.getRequest().getRealPath(getSavePath());
    	 File file = new File(realFilePath + "\\" + this.fileName);
    	 this.downloadFile = new FileInputStream(file);
    	 //解决中文下载乱码的问题
    	 this.fileName = new String(fileName.getBytes(),"ISO-8859-1"); 
         return SUCCESS; 
    }

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public FileInputStream getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(FileInputStream downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 





	
}
