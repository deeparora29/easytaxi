package com.btit.common.utils.ajax.test;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: InitParamServlet
 *
 */
 public class InitParamServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//初始化数据
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("1", "张三");
		dataMap.put("2", "李四");
		dataMap.put("3", "王麻子");
		dataMap.put("4", "小a");
		dataMap.put("5", "小b");
		
		getServletContext().setAttribute("Data", dataMap);
		
		System.out.println("初始化数据！");
	}

static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InitParamServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}