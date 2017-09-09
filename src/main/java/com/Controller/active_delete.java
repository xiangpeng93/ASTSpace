package com.Controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by xiangpeng on 2017/9/9.
 */

public class active_delete extends HttpServlet {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/astspace";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://ast-space.b0.upaiyun.com");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");

        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");
            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行 SQL 查询
            stmt = conn.createStatement();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateNow = df.format(new Date());

            String deleteSql;
            deleteSql = String.format("delete from active_info where active_name='%s' \n" +
                            " and active_cost='%s' \n" +
                            " and active_modify_time='%s' \n" +
                            " and active_msg='%s' \n" +
                            " and active_teacher='%s' \n" +
                            " and active_pic='%s';",
                    request.getParameter("active_name"),
                    request.getParameter("active_cost"),
                    request.getParameter("active_time"),
                    request.getParameter("active_msg"),
                    request.getParameter("active_teacher"),
                    request.getParameter("active_pic"));

            System.out.println(deleteSql);
            //执行并得到结果
            int result = stmt.executeUpdate(deleteSql);
            System.out.println(result);
            if(result != 0)
            {
                out.println("删除成功");
            }
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 最后是用于关闭资源的块

            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
}
