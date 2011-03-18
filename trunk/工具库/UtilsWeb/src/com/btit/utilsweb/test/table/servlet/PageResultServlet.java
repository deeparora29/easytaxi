package com.btit.utilsweb.test.table.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.extremecomponents.table.core.TableConstants;
import org.springframework.web.util.WebUtils;

import com.btit.utilsweb.test.table.dao.HibernateEntityDao;
import com.btit.utilsweb.test.table.vo.PageSortModel;
import com.btit.utilsweb.test.table.vo.RollupEnterpriseCommbarcode;

/**
 * Servlet implementation class for Servlet: PageReultServlet
 *
 */
 public class PageResultServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PageResultServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String tableId="rollupTableId";//default is "ec"

		//Map map = WebUtils.getParametersStartingWith(request,"search_");

		PageSortModel psm=new PageSortModel(request,null);
		HibernateEntityDao dao = new HibernateEntityDao();
		List resultList=dao.find(RollupEnterpriseCommbarcode.class, psm);
		request.setAttribute("rollupList", resultList);
		request.setAttribute(TableConstants.TOTAL_ROWS, psm.getTotalRows());
		request.getRequestDispatcher("table/rollupListTable.jsp").forward(request, response);
		//response.sendRedirect("table/rollupListTable.jsp");
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}   	  	    
}