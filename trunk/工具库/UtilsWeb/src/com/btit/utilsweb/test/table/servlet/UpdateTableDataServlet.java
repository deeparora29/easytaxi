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
 * Servlet implementation class UpdateTableDataServlet
 */
public class UpdateTableDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTableDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String staffId = request.getParameter("staffId");
		String staffName = request.getParameter("staffName");
		String departName = request.getParameter("departName");
		
		if(StringUtils.isBlank(staffId)){
			System.err.println("staffId 参数为空!");
		}
		StaffInfo newone=new StaffInfo(staffId,staffName,departName);
		//--Begin 修改DB中的内容
		HibernateEntityDao dao = new HibernateEntityDao();
		dao.updateObject(newone);
		//--End
		//Query From DB
		List staffList = dao.findAllObject(StaffInfo.class);
		dao.closeSessionFactory();
//		List staffList = (List)request.getSession().getAttribute("staffList");
//		if(staffList == null){
//			System.err.println("session 未获取 staffList对象!");
//		}
//		for(int i = 0; i < staffList.size(); i++){
//			StaffInfo staffInfo = (StaffInfo)staffList.get(i);
//			if(staffInfo.getStaffId().equals(staffId)){
//				staffInfo.setStaffName(staffName);
//				staffInfo.setDepartName(departName);
//				staffList.remove(i);
//				staffList.add(i, staffInfo);
//			}
//		}
		request.getSession().setAttribute("staffList", staffList);
		request.getSession().setAttribute(TableConstants.TOTAL_ROWS, new Integer(staffList.size()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
