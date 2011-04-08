package com.easytaxi.usermgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.usermgr.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private LoginService loginService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        loginService = (LoginService) BeanFactoryUtil.getBean("loginService");
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
        String type = request.getParameter("type");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String userid = loginService.getValidUserid(type, account, password);

        if (!userid.equals("") || true) {
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }

	}

}
