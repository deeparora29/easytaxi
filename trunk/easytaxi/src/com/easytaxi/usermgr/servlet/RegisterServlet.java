package com.easytaxi.usermgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.usermgr.service.LoginService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private LoginService loginService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
        String userid = "";
        if (type.equals("passenger")) {
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String nickName = request.getParameter("nickName");
            String gender = request.getParameter("gender");
            Passenger passenger = new Passenger("", "", "", password, phone, email, nickName, gender,
                "Passenger Web Register","","");
            passenger.setAgreement("yes");
            userid = doRegisterPassenger(passenger);
        } else {
            String cab = request.getParameter("cab");
            String password = request.getParameter("password");
            String company = request.getParameter("company");
            String email = request.getParameter("email");
            String driver0 = request.getParameter("driver0");
            String phone0 = request.getParameter("phone0");
            Taxi taxi = new Taxi(cab, password, "", company, email, "", "", "Taxi web register");
            taxi.setDriver0(driver0);
            taxi.setPhone0(phone0);
            userid = doRegisterTaxi(taxi);
        }
        if (!userid.equals("")) {
            request.getSession().setAttribute(SystemPara.SESSION_USERID, userid);
            response.sendRedirect("welcome.jsp?userId=" + userid);
        } else {
            response.sendRedirect("register.jsp");
        }
    }


    private String doRegisterPassenger(Passenger passenger) {
        return loginService.registerPassenger(passenger) ? passenger.getUserid() : "";
    }

    private String doRegisterTaxi(Taxi taxi) {
        return loginService.registerTaxi(taxi) ? taxi.getUserid() : "";
	}

}
