/**
 * <p>Copyright ? 2009 BTIT International Group Co. Ltd. </p>
 * <p>Project            :</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :JasperReport帮助类;</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2009.06.15</p>
 * <p>1.2009.06.15 Yuxd new</p>
 **/
package com.btit.common.jasperreport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * <p>Title: JasperReport帮助类;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2009</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class JasperReportHelper {
	//当不为空时，表示报表设计布局来源于JasperDesign程序设计
	private JasperDesign jasperDesign;
	//当不为空进，表示报表设计布局来源于Ireport设计器产生的jasper文件
	private String jasperFileName;
	//填充参数Map
	private Map parameterMap;
	//当不为空时，表示填充报表时使用JDBC的connection连接作为数据源
	private Connection connection;
	//当不为空时，表示填充报表时使用数组作为数据源
	private Object[] beanArray;
	//当不为空时，表示填充报表时使用集合作为数据源
	private Collection beanCollection;
	
	private JasperPrint jasperPrint;
	
	/**
	 * 报表布局来源：JasperDesign程序设计
	 * 报表参数：传入Map
	 * 报表数据源：JDBC的connection连接
	 * @param jasperDesign 报表布局来源
	 * @param parameterMap 报表参数
	 * @param connection 报表数据源
	 */
	public JasperReportHelper(JasperDesign jasperDesign,
			Map parameterMap,
			Connection connection){
		this.jasperDesign = jasperDesign;
		this.parameterMap = parameterMap;
		this.connection = connection;
	}

	/**
	 * 报表布局来源：Ireport设计器产生的jasper文件
	 * 报表参数：传入Map
	 * 报表数据源：JDBC的connection连接
	 * @param jasperFileName 报表布局来源
	 * @param parameterMap 报表参数
	 * @param connection 报表数据源
	 */
	public JasperReportHelper(String jasperFileName,
			Map parameterMap,
			Connection connection){
		this.jasperFileName = jasperFileName;
		this.parameterMap = parameterMap;
		this.connection = connection;
	}
	
	/**
	 * 报表布局来源：JasperDesign程序设计
	 * 报表参数：传入Map
	 * 报表数据源：数组
	 * @param jasperDesign 报表布局来源
	 * @param parameterMap 报表参数
	 * @param beanArray 报表数据源
	 */
	public JasperReportHelper(JasperDesign jasperDesign,
			Map parameterMap,
			Object[] beanArray){
		this.jasperDesign = jasperDesign;
		this.parameterMap = parameterMap;
		this.beanArray = beanArray;
	}

	/**
	 * 报表布局来源：Ireport设计器产生的jasper文件
	 * 报表参数：传入Map
	 * 报表数据源：数组
	 * @param jasperFileName 报表布局来源
	 * @param parameterMap 报表参数
	 * @param beanArray 报表数据源
	 */
	public JasperReportHelper(String jasperFileName,
			Map parameterMap,
			Object[] beanArray){
		this.jasperFileName = jasperFileName;
		this.parameterMap = parameterMap;
		this.beanArray = beanArray;
	}
	
	/**
	 * 报表布局来源：JasperDesign程序设计
	 * 报表参数：传入Map
	 * 报表数据源：集合
	 * @param jasperDesign 报表布局来源
	 * @param parameterMap 报表参数
	 * @param beanCollection 报表数据源
	 */
	public JasperReportHelper(JasperDesign jasperDesign,
			Map parameterMap,
			Collection beanCollection){
		this.jasperDesign = jasperDesign;
		this.parameterMap = parameterMap;
		this.beanCollection = beanCollection;
	}

	/**
	 * 报表布局来源：Ireport设计器产生的jasper文件
	 * 报表参数：传入Map
	 * 报表数据源：集合
	 * @param jasperFileName 报表布局来源
	 * @param parameterMap 报表参数
	 * @param beanCollection 报表数据源
	 */
	public JasperReportHelper(String jasperFileName,
			Map parameterMap,
			Collection beanCollection){
		this.jasperFileName = jasperFileName;
		this.parameterMap = parameterMap;
		this.beanCollection = beanCollection;
	}

	/**
	 * 导出为HTML格式的字节码
	 * @return
	 */
	public byte[] exportReportToHtml(String imagesDirName, String imagesUri) throws JRException{
		//填充报表
		fillExport();
		
		//Html格式导出器
		JRExporter exporter = new JRHtmlExporter();

		//字节码输出流
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();

		//设置输出图片到指定目录
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
				Boolean.TRUE);
		//设置图片输出目录
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
				imagesDirName);
		//设置输出图片显示URI
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				imagesUri);
		//设置编码
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
		exporter.exportReport();
		byte[] bytes = oStream.toByteArray();
		return bytes;
	}

	/**
	 * 导出为HTML格式的字节流
	 * 
	 * @param outputStream
	 */
	public void exportReportToHtml(OutputStream outputStream, String imagesDirName, String imagesUri) throws JRException{
		//填充报表
		fillExport();

		//Html格式导出器
		JRExporter exporter = new JRHtmlExporter();

		//设置输出图片到指定目录
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
				Boolean.TRUE);
		//设置图片输出目录
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
				imagesDirName);
		//设置输出图片显示URI
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				imagesUri);
		//设置编码
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}
	
	/**
	 * 导出为PDF格式的字节码
	 * @return
	 */
	public byte[] exportReportToPdf() throws JRException{
		//填充报表
		fillExport();
		
		//Pdf格式导出器
		JRExporter exporter = new JRPdfExporter();

		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
		exporter.exportReport();
		byte[] bytes = oStream.toByteArray();
		return bytes;
	}
	
	/**
	 * 导出为PDF格式的字节流
	 * @param outputStream
	 */
	public void exportReportToPdf(OutputStream outputStream) throws JRException{
		//填充报表
		fillExport();

		//Pdf格式导出器
		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}
		
	/**
	 * 导出为XLS格式的字节码
	 * @return
	 */
	public byte[] exportReportToXls() throws JRException{
		//填充报表
		fillExport();

		//Xls格式导出器
		JRExporter exporter = new JExcelApiExporter();

		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.exportReport();

		byte[] bytes = oStream.toByteArray();
		return bytes;
	}
	
	/**
	 * 导出为XLS格式的字节流
	 * 
	 * @param outputStream
	 */
	public void exportReportToXls(OutputStream outputStream) throws JRException{
		//填充报表
		fillExport();

		//Xls格式导出器
		JRExporter exporter = new JExcelApiExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.exportReport();
	}
		
	private void fillExport() throws JRException{
		JasperReport jasperReport = null;
		if(jasperDesign != null){ //JasperDesign程序设计
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
		}else if(jasperFileName != null){ //Ireport设计器产生的jasper文件
			File sourceFile = new File(jasperFileName);
			jasperReport = (JasperReport)JRLoader.loadObject(sourceFile);
		}else{ //错误的来源
			throw new JRException("报表设计布局来源不正确，必须为JasperDesign程序设计、Ireport设计器产生的jasper文件之一");
		}
		
		if(connection != null){ //JDBC的connection连接
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, connection);
		}else if(beanArray != null){ //数组
			JRDataSource dataSource = new JRBeanArrayDataSource(beanArray);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, dataSource);
		}else if(beanCollection != null){ //集合
			JRDataSource dataSource = new JRBeanCollectionDataSource(beanCollection);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, dataSource);
		}else{ //错误
			throw new JRException("填充数据源不正确，必须为JDBC的connection连接、数组、集合之一");
		}
	}
}
