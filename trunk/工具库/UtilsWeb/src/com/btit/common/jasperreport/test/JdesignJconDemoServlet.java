/**
 * <p>Copyright ? 2009 BTIT International Group Co. Ltd. </p>
 * <p>Project            :</p>
 * <p>JDK version used  :jdk1.5.0</p>
 * <p>Comments         :JasperReport测试Servlet类;</p>
 * <p>Version          :2.0</p>
 * <p>Modification history:2009.06.18</p>
 * <p>1.2009.06.18 Yuxd new</p>
 **/
package com.btit.common.jasperreport.test;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btit.common.jasperreport.JasperReportHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * <p>Title: JasperReport测试Servlet类;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2009</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class JdesignJconDemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1477524488351182753L;

	/**
	 * Constructor of the object.
	 */
	public JdesignJconDemoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String reportType = request.getParameter("reportType");
		if(reportType == null || reportType.equals("")){
			response.setContentType("text/html;charset=utf-8;");
			PrintWriter out = response.getWriter();
			out
					.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("<br><form name=\"fm\" action=\"jdesignjcondemo\" method=\"post\">");
			out.println("请选择输出类型:");
			out.println("<select size=\"1\" name=\"reportType\">");
			out.println("<option value=\"EXCEL\">EXCEL</option>");
			out.println("<option value=\"HTML\">HTML</option>");
			out.println("<option value=\"PDF\">PDF</option>");
			out.println("</select>");
			out.println("<br><input type=\"button\" name=\"resubmit\" value=\"提交\" onclick=\"document.fm.submit();\">");
			out.println("</form>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			
			return;
	        
		}
		
		//导出EXCEL
		if(reportType.equals("EXCEL")){
			excelExport(request, response);
		}else if(reportType.equals("HTML")){//导出HTML
			htmlExport(request, response);
		}else if(reportType.equals("PDF")){//导出PDF
			pdfExport(request, response);
		}

		
	}
	
	private JasperDesign createJasperDesign(HttpServletRequest request) throws JRException{
		//新建一个设计器
		JasperDesign jasperDesign = new JasperDesign();
		jasperDesign.setName("销售数据列表");
		
		//设置SQL语句
		JRDesignQuery query = new JRDesignQuery();
		query.setText("SELECT COMM_BARCODE,NAME,AREA_CODE,COMM_SORT,SALE_TIME,UNIT,AMOUNT,PRICE,SUM_PRICE FROM \"ROLLUP_ENTERPRISE_COMMBARCODE.txt\"");
		jasperDesign.setQuery(query);
				
		JRDesignBand title = new JRDesignBand();
		title.setHeight(50);  
		
		//定义报表的Title
        JRDesignStaticText titleText = new JRDesignStaticText();   
        titleText.setText("销售数据列表");   
        titleText.setX(230);   
        titleText.setFontSize(20);   
        titleText.setHeight(50);   
        titleText.setWidth(300);  
        titleText.setFontName("宋体");
        titleText.setFontSize(14);
        titleText.setPdfFontName("STSong-Light");
        titleText.setPdfEncoding("UniGB-UCS2-H");
        titleText.setPdfEmbedded(true);
        title.addElement(titleText);   
        jasperDesign.setTitle(title);
        
        String[] headers = { "COMM_BARCODE", "NAME"
        		,  "AREA_CODE", "COMM_SORT"
        		, "SALE_TIME", "UNIT"
        		, "AMOUNT"
        		, "PRICE", "SUM_PRICE"};   
        String[] headersname = { "条码", "名称"
        		,  "地区编码", "种类编码"
        		, "销售时间", "单位"
        		, "数量"
        		, "价格", "总价"};   
        JRDesignBand columnHeader = new JRDesignBand();   
        columnHeader.setHeight(30);   
  
        JRDesignBand detail = new JRDesignBand();   
        detail.setHeight(30); 
        
        //定义一种显示边框的风格
        JRDesignStyle style = new JRDesignStyle();
        JRLineBox lineBox = style.getLineBox();
        lineBox.setPadding(1);
        
        JRPen pen = lineBox.getPen();
        pen.setLineColor(Color.BLUE);
        pen.setLineWidth(JRPen.LINE_WIDTH_1);
        pen.setLineStyle(JRPen.LINE_STYLE_SOLID);
        style.setName("style");
        style.setFontName("宋体");
        style.setFontSize(14);
        style.setPdfFontName("STSong-Light");
        style.setPdfEncoding("UniGB-UCS2-H");
        style.setPdfEmbedded(true);
        
        JRDesignImage image = new JRDesignImage(jasperDesign);
        
        
        image.setX(1);
        image.setY(1);
        image.setHeight(29);
        image.setWidth(250);
        image.setScaleImage(JRImage.SCALE_IMAGE_FILL_FRAME);
        image.setLazy(true);
        JRDesignExpression imageExpression = new JRDesignExpression();
        imageExpression.setValueClass(String.class);
        imageExpression.setText("\"http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/jasperreport/Sunset.jpg\"");
        image.setExpression(imageExpression);
        
        detail.addElement(image);
        
        //加入风格的设计中
        jasperDesign.addStyle(style);
  
        for (int i = 0; i < headers.length; i++) {   
            // add column headers   
            JRDesignStaticText staticText = new JRDesignStaticText();   
            staticText.setText(headersname[i]);   
            staticText.setFontSize(16);   
            staticText.setHeight(30);   
            staticText.setWidth(250);   
            staticText.setX(250 * i);  
            //引用风格style
            staticText.setStyleNameReference("style");
            columnHeader.addElement(staticText);   
  
            // define fields   
            JRDesignField field = new JRDesignField();   
            field.setName(headers[i]);   
            field.setValueClass(String.class);   
            jasperDesign.addField(field);   
  
            // add text fields for displaying fields   
            JRDesignTextField textField = new JRDesignTextField();   
            JRDesignExpression expression = new JRDesignExpression();   
            expression.setText("$F{" + headers[i] + "}");   
            expression.setValueClass(String.class);   
            textField.setExpression(expression);   
            textField.setFontSize(14);   
            textField.setHeight(30);   
            textField.setWidth(250);   
            textField.setX(250 * i);   
            //引用风格style
            textField.setStyleNameReference("style");
            detail.addElement(textField);   
        }   

        
        jasperDesign.setColumnHeader(columnHeader);   
        jasperDesign.setDetail(detail);   
        
		//jasperDesign.setPageHeight(12000);
		
		return jasperDesign;
	}
	
	/**
	 * 导出为Excel
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void excelExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//得到JasperDesign程序设计对象
			JasperDesign jasperDesign = createJasperDesign(request);
			
			//得到连接
			Connection connection = getConnection(request);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel;charset=utf-8;");

			//构造参数
			Map parameterMap = new HashMap();
			JasperReportHelper jasperReportHelper = new JasperReportHelper(jasperDesign, parameterMap, connection);
			
			jasperReportHelper.exportReportToXls(servletOutputStream);
						
	        servletOutputStream.flush();   
	        servletOutputStream.close(); 
	        
	        connection.close();
	        
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到连接
	 * @param request TODO
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		Class.forName("jstels.jdbc.csv.CsvDriver");

		Properties props = new java.util.Properties();
		 
		props.put("separator","|");              // separator is a bar
		props.put("suppressHeaders","false");    // column headers are on the first line
		props.put("fileExtension",".txt");       // file extension is .txt
		props.put("charset","UTF-8");       // file encoding is "ISO-8859-2"
		props.put("commentLine","--");           // string denoting comment line is "--"
//		 date/time format
		props.put("dateFormat","yyyy-MM-dd HH:mm | dd/MM/yyyy");       
		 
		
		//创建连接
		Connection connection = DriverManager.getConnection("jdbc:jstels:csv:http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/jasperreport/csvfiles", props);
		return connection;
	}

	/**
	 * 导出为Excel
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void htmlExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//得到JasperDesign程序设计对象
			JasperDesign jasperDesign = createJasperDesign(request);
			
			//得到连接
			Connection connection = getConnection(request);
			
			//得到应用的根目录路径
			String approot = getServletConfig().getServletContext().getRealPath("\\");
			//得到输出图片的绝对路径
			String imagesDirName = approot + "images\\jasperreport\\reportimages";
			//得到图片显示的URI
			String imagesUri = request.getContextPath() + "/images/jasperreport/reportimages/";
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("text/html;charset=utf-8;");

			//构造参数
			Map parameterMap = new HashMap();
			JasperReportHelper jasperReportHelper = new JasperReportHelper(jasperDesign, parameterMap, connection);
			
			jasperReportHelper.exportReportToHtml(servletOutputStream, imagesDirName, imagesUri);
						
	        servletOutputStream.flush();   
	        servletOutputStream.close(); 
	        
	        connection.close();
	        
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出为Excel
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void pdfExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//得到JasperDesign程序设计对象
			JasperDesign jasperDesign = createJasperDesign(request);
			
			//得到连接
			Connection connection = getConnection(request);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
	        response.setContentType("application/pdf;charset=utf-8;");
	        
	        //构造参数
			Map parameterMap = new HashMap();
			JasperReportHelper jasperReportHelper = new JasperReportHelper(jasperDesign, parameterMap, connection);
			
			jasperReportHelper.exportReportToPdf(servletOutputStream);
						
	        servletOutputStream.flush();   
	        servletOutputStream.close(); 
	        
	        connection.close();
	        
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
