package com.project.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String user = "springstudent";
        String password = "springstudent";
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?userSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        try{

            PrintWriter out = response.getWriter();

            out.println("Connecting to database: " + jdbcUrl);

            Class.forName(driver);

            try{
                Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
                out.println("Done.");
                connection.close();
            }catch (SQLException e){
                out.println("Access denied.");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}