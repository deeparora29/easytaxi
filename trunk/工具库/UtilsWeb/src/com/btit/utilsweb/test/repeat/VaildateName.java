package com.btit.utilsweb.test.repeat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btit.utilsweb.test.table.dao.HibernateEntityDao;
import com.btit.utilsweb.test.table.vo.StaffInfo;


public class VaildateName extends HttpServlet {
	public VaildateName(){
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		java.util.List<String> list = new java.util.ArrayList<String>();
//		list.add("a");
//		list.add("s");
//		list.add("d");
//		list.add("f");
//		list.add("q");
		HibernateEntityDao dao = new HibernateEntityDao();
		List<StaffInfo> list = dao.findAllObject(StaffInfo.class);
		
		String loginName=request.getParameter("loginName").toString();
	
		String result="false";
			response.setContentType("text/html");
		         for (StaffInfo staffInfo:list){
					if(staffInfo.getStaffName().equals(loginName)){
						result="true";						
						break;	
					}
				}
		        
	response.getWriter().write(result);
	
}

	

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response); 
		
	}

}
