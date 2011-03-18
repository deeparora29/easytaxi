package com.btit.utilsweb.test.table.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.TableConstants;

import com.btit.utilsweb.test.table.dao.HibernateEntityDao;
import com.btit.utilsweb.test.table.vo.StaffInfo;

/**
 * Servlet implementation class for Servlet: QueryStaffInfoServlet
 *
 */
 public class QueryStaffInfoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public QueryStaffInfoServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("staffList") == null){
			HibernateEntityDao dao = new HibernateEntityDao();
			List<StaffInfo> staffList = dao.findAllObject(StaffInfo.class);
			request.getSession().setAttribute("staffList", staffList);
			request.getSession().setAttribute(TableConstants.TOTAL_ROWS, new Integer(staffList.size()));
		}
		String queryStr = request.getParameter("query");
		if(StringUtils.isBlank(queryStr))
			queryStr = "true";
		if(queryStr.equals("true"))
			request.getRequestDispatcher("table/listTable.jsp").forward(request, response);
		else
			request.getRequestDispatcher("table/editTable.jsp").forward(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}   	  	    
}