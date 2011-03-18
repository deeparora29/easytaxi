package com.btit.utilsweb.fileoperate.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

// Excel导入
public class ExcelImportAction extends  ActionSupport{
	private String fileContentType;
	private String fileFileName;
  private List message;
  private File file;
  private String savePath;
	public String getSavePath() {
	return savePath;
}
public void setSavePath(String savePath) {
	this.savePath = savePath;
}
	public List getMessage() {
	return message;
}
public void setMessage(List message) {
	this.message = message;
}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		InputStream is=null;
		List<String> message=new ArrayList<String>();
		try{is= new FileInputStream(file);}catch(Exception e){message.add("你没有上传任何文件！");this.setMessage(message);return SUCCESS;}
		
		Workbook workbook=null;
		try{ workbook = Workbook.getWorkbook(is); }catch(Exception e)
		{e.printStackTrace(); message.add("读取文件出错。请检查上传的是否是Excel文件!");this.setMessage(message);return INPUT;}
		
		Sheet sheet = workbook.getSheet(0); 
		
		int c=sheet.getColumns();
		int r=sheet.getRows();
		List<String> li=new ArrayList<String>();
		for(int j=1;j<r;j++){
		for(int i=0;i<c;i++){
			Cell c2 = sheet.getCell(i,j); 
			li.add(c2.getContents());}
	
		}
		 
		    int i=0;
		    do{if(isNumber(li.get(i))==false){
		    	
		    	String str="你的表格第A"+(i/c+2)+"单元格格式应该为数字型，请改正!";
		    	message.add(str);
		    	
		    	
		    }
		    	i=i+c;
		    }while(i<(r-1)*c);
		    
		    int j=2;
		    String dd=null;
		    do{if(isDate(li.get(j),dd)==false){
		    	String str="你的表格第C"+(j/c+2)+"单元格格式应该为日期型，请改正!";
		    	message.add(str);
		    	}
		    	j=j+c;
		    }while(j<(r-1)*c);
			
			
			List<Object1> l=new ArrayList<Object1>();
			if(message.size()==0){
				
				for(int k=0;k<(r-1)*c;k=k+c){
					Object1 ob=new Object1();
					ob.setNum(Double.parseDouble(li.get(k)));
					ob.setName(li.get(k+1));
					ob.setDepart(li.get(k+2));
					l.add(ob);
				}
				 message.add(0,"你上传的表格通过校验，已产生相关对像");
				
					String fileRealPath = ServletActionContext.getRequest().getRealPath(getSavePath());
					OutputStream os = new FileOutputStream(fileRealPath+"\\"+this.getFileFileName());
					byte[] buffer = new byte[400];

					int length = 0;

					while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					}
					is.close();
					os.close();
		         this.setMessage(message);
		         for(Object1 o:l){
		        	 System.out.println(o);
		         }
				return SUCCESS;
			}else{
		
		         message.add("你上传的表格有错。请改正后再上传");
		         this.setMessage(message);
		return INPUT;}
	}
	
	
	
	
	private boolean isNumber(String s){
		boolean flag = true;
		try{Long i=Long.parseLong(s);
		    
		}catch(Exception e){try{Double d=Double.parseDouble(s);
		return flag;
		}catch(Exception e1){flag=false;return flag;}}
		return flag;
	}
	private boolean isString(String s){
		if(s.trim().length()>=20){
			return false;
		}
		if(s.trim().length()==0){
			
			return false;
		}
		return true;
	}
	private  boolean isDate(String s,String format){  
		boolean flag=true;
        if(null == format || "".equals(format))   
        {   
            format = "mm/dd/yyyy";   
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);   
        Date date=null;   
        try {   
            date=sdf.parse(s);   
        } catch (ParseException e) {   
            // TODO Auto-generated catch block   
           flag=false;return flag;
        }   
        return flag;
    }
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}   



	
}
class Object1{
	private double num;
	private String name;
	private String depart;
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "num="+num+" name="+name+" depart="+depart;
	}
	
	
}
