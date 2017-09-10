package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/9 0009.
 */
public class active_query extends HttpServlet {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/astspace";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");

        PrintWriter out = response.getWriter();
        String ResponseMsg = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");
            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            stmt = conn.createStatement();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateNow = df.format(new Date());

            String querySql;
            querySql = String.format(
                    "select * from active_info;");

            System.out.println(querySql);
            //执行并得到结果
            ResultSet result = stmt.executeQuery(querySql);
            System.out.println(result);
            while (result.next()) {

                // 输出数据
                String responseDatabaseMsg;
                responseDatabaseMsg = String.format("  <tr> <td>\n" +
                                " %s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td>\n" +
                                "<td>\n" +
                                "%s\n" +
                                "</td> </tr>\n",
                        result.getString("active_name"),
                        result.getString("active_cost"),
                        result.getString("active_time"),
                        result.getString("active_msg"),
                        result.getString("active_teacher"),
                        result.getString("active_pic"),
                        result.getString("active_request_url"),
                        result.getString("active_student_num")
                );
                ResponseMsg += responseDatabaseMsg;
            }
            result.close();
            out.println(ResponseMsg);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 最后是用于关闭资源的块

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
