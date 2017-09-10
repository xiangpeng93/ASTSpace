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
public class active_add extends HttpServlet {
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

            String insertSql;
            insertSql = String.format(
                            "insert into active_info (\n" +
                            "active_name,active_cost,\n" +
                            "active_time,active_msg,\n" +
                            "active_teacher,active_pic,\n" +
                            "active_modify_time\n" +
                            ") values(\n" +
                            "'%s',\n" +
                            "'%s',\n" +
                            "'%s',\n" +
                            "'%s',\n" +
                            "'%s',\n" +
                            "'%s',\n" +
                            "'%s'\n" +
                    ");",
                    request.getParameter("active_name"),
                    request.getParameter("active_cost"),
                    request.getParameter("active_time"),
                    request.getParameter("active_msg"),
                    request.getParameter("active_teacher"),
                    request.getParameter("active_pic"),

                    dateNow.toString());

            System.out.println(insertSql);
            //执行并得到结果
            int result = stmt.executeUpdate(insertSql);
            System.out.println(result);
            if(result != 0)
            {
                out.println("<br> <p class=\"container\"> 活动添加成功。</p> <br> " +
                        "<div class=\"modal-footer\">\n" +
                        "                        <button type=\"button\" id=\"submit_dianzi\" data-dismiss=\"modal\" class=\"btn btn-primary\"  onclick=\"test()\">完成</button>\n" +
                        "                    </div>");

            }

            String querySql;
            querySql = String.format(
                    "select id from active_info where active_name='%s' and active_modify_time='%s';",request.getParameter("active_name"),dateNow.toString());

            System.out.println(querySql);
            //执行并得到结果
            ResultSet rs = stmt.executeQuery(querySql);
            while (rs.next()) {
               int id = rs.getInt("id");
               String update_request_url = String.format("update active_info set active_request_url='%s' where id=%d","http://astspace.org/active_show.html?id="+id,id);
               System.out.println(update_request_url);
               stmt.executeUpdate(update_request_url);
                if(result != 0) {
                    System.out.println("更新数据成功");
                }
            }
            rs.close();
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
