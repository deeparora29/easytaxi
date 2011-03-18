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
public class AddTableDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTableDataServlet() {
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
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		String staffId = request.getParameter("staffId");
		String staffName = request.getParameter("staffName");
		String departName = request.getParameter("departName");
		//--Begin插入数据库中
		StaffInfo newone=new StaffInfo(staffId,staffName,departName);
		HibernateEntityDao dao = new HibernateEntityDao();
		dao.addObject(newone);
		//--End
		//Query From DB
		List staffList = dao.findAllObject(StaffInfo.class);
		dao.closeSessionFactory();
//		List staffList = (List)request.getSession().getAttribute("staffList");
//		staffList.add(newone);
		request.getSession().setAttribute("staffList", staffList);
		request.getSession().setAttribute(TableConstants.TOTAL_ROWS, new Integer(staffList.size()));
	}

}
