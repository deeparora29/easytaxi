package com.easytaxi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProvideInitMapData
 */
public class ProvideInitMapData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvideInitMapData() {
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
		StringBuffer data = new StringBuffer("[\n");
		data.append("	{\n");
		data.append("		latLng : {lat : 30.658602, lng : 104.06486},\n");
		data.append("		taxiNo : '川A000001' ,\n");
		data.append("		driverNo : '100001' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区1'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.648602, lng : 104.06486},\n");
		data.append("		taxiNo : '川A000002' ,\n");
		data.append("		driverNo : '100002' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区2'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.638602, lng : 104.05486},\n");
		data.append("		taxiNo : '川A000003' ,\n");
		data.append("		driverNo : '100003' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区3'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.648602, lng : 104.05486},\n");
		data.append("		taxiNo : '川A000004' ,\n");
		data.append("		driverNo : '100004' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区4'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.678602, lng : 104.06486},\n");
		data.append("		taxiNo : '川A000005' ,\n");
		data.append("		driverNo : '100005' ,\n");
		data.append("		taxiStatus : '负载' ,\n");
		data.append("		taxiAddress : '成都市青羊区5' \n");
		data.append("	}");
		data.append("]");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(data.toString());
		response.getWriter().close();
	}

}
