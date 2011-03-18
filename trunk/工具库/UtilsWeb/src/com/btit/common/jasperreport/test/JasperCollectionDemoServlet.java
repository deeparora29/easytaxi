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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btit.common.jasperreport.JasperReportHelper;

import net.sf.jasperreports.engine.JRException;

/**
 * <p>Title: JasperReport测试Servlet类;</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ? 2009</p>
 * <p>Company: BTIT</p>
 * @author :Yuxd
 * @version 2.0
 */
public class JasperCollectionDemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3736042187619224324L;

	/**
	 * Constructor of the object.
	 */
	public JasperCollectionDemoServlet() {
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
			out.println("<br><form name=\"fm\" action=\"jaspercolldemo\" method=\"post\">");
			out.println("请选择输出类型:");
			out.println("<select size=\"1\" name=\"reportType\">");
			out.println("<option value=\"HTML\">HTML</option>");
			out.println("<option value=\"EXCEL\">EXCEL</option>");
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
		
		String reportName = "collection\\collectionreport";
		
		//导出EXCEL
		if(reportType.equals("EXCEL")){
			excelExport(request, response, reportName);
		}else if(reportType.equals("HTML")){//导出HTML
			htmlExport(request, response, reportName);
		}else if(reportType.equals("PDF")){//导出PDF
			pdfExport(request, response, reportName);
		}
		
	}

	/**
	 * 导出为Excel
	 * @param request
	 * @param response
	 * @param reportName
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void excelExport(HttpServletRequest request, HttpServletResponse response, String reportName) throws IOException {
		try {
			//构造集合数据
			Collection coll = createColl();
			
			//得到应用的根目录路径
			String approot = getServletConfig().getServletContext().getRealPath("\\");
			//得到报表的绝对路径
			String jasperFileName = approot + "jasperreport\\report\\" + reportName + ".jasper";
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel;charset=utf-8;");

			//构造参数
			Map parameterMap = new HashMap();
			
			JasperReportHelper jasperReportHelper = new JasperReportHelper(jasperFileName, parameterMap, coll);
			
			jasperReportHelper.exportReportToXls(servletOutputStream);
						
	        servletOutputStream.flush();   
	        servletOutputStream.close(); 
	        
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出为Excel
	 * @param request
	 * @param response
	 * @param reportName
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void htmlExport(HttpServletRequest request, HttpServletResponse response, String reportName) throws IOException {
		try {
			//构造集合数据
			Collection coll = createColl();
			
			//得到应用的根目录路径
			String approot = getServletConfig().getServletContext().getRealPath("\\");
			//得到报表的绝对路径
			String jasperFileName = approot + "jasperreport\\report\\" + reportName + ".jasper";
			//得到输出图片的绝对路径
			String imagesDirName = approot + "images\\jasperreport\\reportimages";
			//得到图片显示的URI
			String imagesUri = request.getContextPath() + "/images/jasperreport/reportimages/";
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("text/html;charset=utf-8;");

			//构造参数
			Map parameterMap = new HashMap();
			
			JasperReportHelper jasperReportHelper = new JasperReportHelper(jasperFileName, parameterMap, coll);
			
			jasperReportHelper.exportReportToHtml(servletOutputStream, imagesDirName, imagesUri);
						
	        servletOutputStream.flush();   
	        servletOutputStream.close(); 
	        
	        
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出为Excel
	 * @param request
	 * @param response
	 * @param reportName
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void pdfExport(HttpServletRequest request, HttpServletResponse response, String reportName) throws IOException {
		try {
			//构造集合数据
			Collection coll = createColl();
			
			//得到应用的根目录路径
			String approot = getServletConfig().getServletContext().getRealPath("\\");
			//得到报表的绝对路径
			String jasperFileName = approot + "jasperreport\\report\\" + reportName + ".jasper";
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
	        response.setContentType("application/pdf;charset=utf-8;");
	        
	        //构造参数
			Map parameterMap = new HashMap();

			JasperReportHelper jasperReportHelper = new JasperReportHelper(jasperFileName, parameterMap, coll);
			
			jasperReportHelper.exportReportToPdf(servletOutputStream);
						
	        servletOutputStream.flush();   
	        servletOutputStream.close(); 
	        
	        
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Collection createColl() {
		Collection coll = new ArrayList();
		DataBean dataBean = new DataBean();
		dataBean.setName("测试1");
		dataBean.setDesc("描述1");
		coll.add(dataBean);
		
		dataBean = new DataBean();
		dataBean.setName("测试2");
		dataBean.setDesc("描述2");
		coll.add(dataBean);
		
		dataBean = new DataBean();
		dataBean.setName("测试3");
		dataBean.setDesc("描述3");
		coll.add(dataBean);
		return coll;
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
