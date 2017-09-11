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
 * Created by Administrator on 2017/9/11 0011.
 */
public class query_by_className extends HttpServlet {
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
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");
        // 实际的逻辑是在这里
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
            String querySql;
            querySql = String.format("SELECT id, userName,ageNum, phoneNum,className FROM kc_bm where className='%s';",request.getParameter("className"));
            System.out.println(querySql);

            ResultSet rs = stmt.executeQuery(querySql);
            boolean bHaveData = false;
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String strUserName = rs.getString("userName");
                String strPhoneNum = rs.getString("phoneNum");

                String strAgeNum = rs.getString("ageNum");
                String strKcName = rs.getString("className");

                // 输出数据
                String responseDatabaseMsg;
                responseDatabaseMsg = String.format("<table class=\"table table-striped table-bordered table-hover table-condensed\">\n" +
                        "\t\t<thead>\n" +
                        "\t\t\t<tr>\n" +
                        "\t\t\t\t<th>%s</th>\n" +
                        "\t\t\t\t<th>%s</th>\n" +
                        "\t\t\t\t<th>%s</th>\n" +
                        "\t\t\t\t<th>%s</th>\n" +
                        "\t\t\t</tr>\n" +
                        "\t\t</thead>\n" +
                        "\t\t<tbody>\n" +
                        "\t\t\t<tr>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t</tr>\n" +
                        "\t\t</tbody>\n" +
                        "\t</table>","姓名","电话号码","年龄","课程名称",strUserName,strPhoneNum,strAgeNum,strKcName);

                out.println(responseDatabaseMsg);

                System.out.println(responseDatabaseMsg);
            }
            // 完成后关闭
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
