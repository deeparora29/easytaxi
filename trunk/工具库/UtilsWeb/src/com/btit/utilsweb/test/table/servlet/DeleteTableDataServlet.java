package com.btit.utilsweb.test.table.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.TableConstants;

import com.btit.utilsweb.test.table.dao.HibernateEntityDao;
import com.btit.utilsweb.test.table.vo.StaffInfo;

/**
 * Servlet implementation class DeleteTableDataServlet
 */
public class DeleteTableDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTableDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String staffId = request.getParameter("staffId");
		String result = "";
		if(StringUtils.isBlank(staffId)){
			result = "错误！没有获取员工编号！";
		}else{
//			List staffList = (List)request.getSession().getAttribute("staffList");
//			if(staffList == null){
//				System.err.println("session 未获取 staffList对象!");
//			}
//			for(int i = 0; i < staffList.size(); i++){
//				StaffInfo staffInfo = (StaffInfo)staffList.get(i);
//				if(staffInfo.getStaffId().equals(staffId)){
//					staffList.remove(i);
//				}
//			}
			//--Begin 删除数据
			HibernateEntityDao dao = new HibernateEntityDao();
			StaffInfo staffInfo = (StaffInfo)dao.findById(StaffInfo.class, staffId);
			dao.deleteObject(staffInfo);
			List staffList = dao.findAllObject(StaffInfo.class);
			dao.closeSessionFactory();
			result = "删除员工[" + staffId + "]成功！";
			request.getSession().setAttribute("staffList", staffList);
			request.getSession().setAttribute(TableConstants.TOTAL_ROWS, new Integer(staffList.size()));
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

}
