package com.searchMsg;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
public class searchClassMsg extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/astspace";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        Statement stmt = null;

        String responseMsg = "";
        try {
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行 SQL 查询
            stmt = conn.createStatement();
            String querySql;
            querySql = String.format("SELECT  * from kc_bm order by time_bm desc;");
            System.out.println(querySql);

            ResultSet rs = stmt.executeQuery(querySql);


            String trType[]={"active","success","warning","danger","info"};

            int typeStatus = 0;
            // 展开结果集数据库
            while (rs.next()) {
                String strUserName = rs.getString("userName");
                String strPhoneNum = rs.getString("phoneNum");
                String strAgeNum = rs.getString("ageNum");
                String strKcName = rs.getString("className");
                String timeMsg = rs.getString("time_bm");

                responseMsg += String.format("<tr class=\"%s\">\n" +
                        "\t\t\t\t\t\t\t  <td>\n" +
                        "\t\t\t\t\t\t\t\t  %s\n" +
                        "\t\t\t\t\t\t\t  </td>\n" +
                        "\t\t\t\t\t\t\t  <td>\n" +
                        "\t\t\t\t\t\t\t\t  %s\n" +
                        "\t\t\t\t\t\t\t  </td>\n" +
                        "\t\t\t\t\t\t\t  <td>\n" +
                        "\t\t\t\t\t\t\t\t  %s\n" +
                        "\t\t\t\t\t\t\t  </td>\n" +
                        "\t\t\t\t\t\t\t  <td>\n" +
                        "\t\t\t\t\t\t\t\t  %s\n" +
                        "\t\t\t\t\t\t\t  </td>\n" +
                        "\t\t\t\t\t\t\t  <td>\n" +
                        "\t\t\t\t\t\t\t\t  %s\n" +
                        "\t\t\t\t\t\t\t  </td>\n" +
                        "\t\t\t\t\t\t  </tr>",trType[typeStatus],strUserName,strPhoneNum,strAgeNum,strKcName,timeMsg);
                System.out.println(responseMsg);
                typeStatus++;
                if(typeStatus >= 5)
                {
                    typeStatus = 0;
                }
            }
        }catch(SQLException se) {
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
        out.print(responseMsg);
    }
}
