package com.easytaxi.common.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.easytaxi.common.utils.BeanFactoryUtil;

/**
 * Servlet implementation class TestSqliteServlet
 */
public class TestSqliteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSqliteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleJdbcTemplate jdbcTemplate = (SimpleJdbcTemplate) BeanFactoryUtil.getBean("jdbcTemplate");
        if (jdbcTemplate == null)
            throw new ServletException("Cann't get the jdbcTemplate");
        jdbcTemplate.update("drop table if exists people;");
        jdbcTemplate.update("create table people (name, occupation);");
        // HashMap test = new HashMap();
        // test.put("name", "renmian");
        // test.put("occupation", "Developer");
        // Map[] values = { test };
        // jdbcTemplate.batchUpdate("insert into people values (?, ?);", values);
        jdbcTemplate.update("insert into people values ('Ren Mian', 'Developer');");
        jdbcTemplate.update("insert into people values ('Anne', 'Tester');");

        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from people;");
        if (result.size() == 0)
            response.getWriter().print("There are no results in the Table<people>");
        else {
            for (int i = 0; i < result.size(); i++) {
                response.getWriter().println(
                    "Name: " + result.get(i).get("name") + " occupation: " + result.get(i).get("occupation"));
            }
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}

}
