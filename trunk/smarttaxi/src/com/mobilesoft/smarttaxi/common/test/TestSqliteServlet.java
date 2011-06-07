
package com.mobilesoft.smarttaxi.common.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilesoft.smarttaxi.common.test.TestSqliteService.People;
import com.mobilesoft.smarttaxi.common.utils.BeanFactoryUtil;

/**
 * Servlet implementation class TestSqliteServlet
 */
public class TestSqliteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TestSqliteService testSqliteService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSqliteServlet() {
        super();
        testSqliteService = (TestSqliteService) BeanFactoryUtil.getBean("testSqliteService");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /***
         * original one
         */
        // JdbcTemplate jdbcTemplate = (JdbcTemplate) BeanFactoryUtil.getBean("jdbcTemplate");
        // if (jdbcTemplate == null)
        // throw new ServletException("Cann't get the jdbcTemplate");
        // jdbcTemplate.update("drop table if exists people;");
        // jdbcTemplate.update("create table people (name, occupation);");
        // // HashMap test = new HashMap();
        // // test.put("name", "renmian");
        // // test.put("occupation", "Developer");
        // // Map[] values = { test };
        // // jdbcTemplate.batchUpdate("insert into people values (?, ?);", values);
        // jdbcTemplate.update("insert into people values ('Ren Mian', 'Developer');");
        // jdbcTemplate.update("insert into people values ('Anne', 'Tester');");
        //
        // List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from people;");
        // if (result.size() == 0)
        // response.getWriter().print("There are no results in the Table<people>");
        // else {
        // for (int i = 0; i < result.size(); i++) {
        // response.getWriter().println(
        // "Name: " + result.get(i).get("name") + " occupation: " + result.get(i).get("occupation"));
        // }
        // }

        getTestSqliteService().createTable();
        List<People> result = getTestSqliteService().getAllPeople();
        response.getWriter().print(
            "<table align='center'><tr align='center'><td colspan='2'> Data in People </td></tr>");
        if (result == null || result.size() == 0) {
            response.getWriter().print("<tr><td colspan='2'>There are no results in the Table<people></td></tr>");
        } else {
            response.getWriter().print("<tr><td>Name</td><td>Occupation</td></tr>");
            for (int i = 0; i < result.size(); i++) {
                response.getWriter().print(
                    "<tr><td>" + result.get(i).getName() + "</td><td>" + result.get(i).getOccupation() + "</td></tr>");
            }
        }
        response.getWriter().print("</table>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException {
        String name = request.getParameter("name");
        String occupation = request.getParameter("occupation");
        if (name != null && occupation != null) {
            getTestSqliteService().insertTable(name.trim(), occupation.trim());
        }
        doGet(request, response);
    }

    public void setTestSqliteService(TestSqliteService testSqliteService) {
        this.testSqliteService = testSqliteService;
    }

    public TestSqliteService getTestSqliteService() {
        return testSqliteService;
    }

}
