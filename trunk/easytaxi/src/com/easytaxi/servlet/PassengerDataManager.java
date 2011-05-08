package com.easytaxi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.location.service.PassengerDataService;

/**
 * Servlet implementation class PassengerDataManager
 */
public class PassengerDataManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengerDataManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
        // if(data!=null){
        // data = new String(data.getBytes("ISO-8859-1"), "UTF-8");
        // }
		PassengerDataService instance = (PassengerDataService)BeanFactoryUtil.getBean("passengerDataService");
		String returnMessage = instance.offer( data );
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print( returnMessage );
		response.getWriter().close();
	}

}
