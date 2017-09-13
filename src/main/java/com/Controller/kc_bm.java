package com.Controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class kc_bm extends HttpServlet {

    private String message;
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/astspace";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    public void init() throws ServletException
    {
        // 执行必需的初始化
        message = "恭喜，报名成功，请准时参加活动，若有问题，请联系工作人员，长按图片进行咨询。";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://ast-space.b0.upaiyun.com");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");
        String responseMsg = "<p>" + message + "</p>";
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
            querySql = String.format("SELECT id, userName,ageNum, phoneNum,className FROM kc_bm where username='%s' and phoneNum='%s' and className='%s';",request.getParameter("name"),request.getParameter("phoneNum"),request.getParameter("className"));
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
                        "\t\t\t</tr>\n" +
                        "\t\t</thead>\n" +
                        "\t\t<tbody>\n" +
                        "\t\t\t<tr>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t</tr>\n" +
                        "\t\t\t<tr>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t</tr>\n" +
                        "\t\t\t<tr>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t\t<td>%s</td>\n" +
                        "\t\t\t</tr>\n" +
                        "\t\t</tbody>\n" +
                        "\t</table>","姓名",strUserName,"电话号码",strPhoneNum,"年龄",strAgeNum,"课程名称",strKcName);


                out.println( "<p>" + "您已经报名成功，请确认报名信息" + "</p>" );
                out.println(responseDatabaseMsg + "<div class=\"modal-footer\">\n" +
                        "                        <button type=\"button\" id=\"submit_dianzi\" data-dismiss=\"modal\" class=\"btn btn-primary\">完成</button>\n" +
                        "                    </div>");
                bHaveData = true;
            }
            if(!bHaveData)
            {
                if(request.getParameter("className").equals("Arduino 入门") ||
                        request.getParameter("className").equals("Scratch:猫咪寻宝之旅"))
                {
                    responseMsg+="<div><img  class=\"img-responsive center-block\"   src=\"img/yjm.jpeg\" ></img></div>";
                }
                else if(request.getParameter("className").equals("生物黑客之小鸡孵化") )
                {
                    responseMsg+="<div><img  class=\"img-responsive center-block\"  src=\"img/hxx.jpeg\" ></img></div>";
                }
                else
                {
                    responseMsg+="<div><img  class=\"img-responsive center-block\"   src=\"img/yjm.jpeg\" ></img></div>";
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String dateNow = df.format(new Date());
                System.out.println(dateNow);// new Date()为获取当前系统时间

                String strClassName = request.getParameter("className").toString();

                String insertSql = String.format("insert into kc_bm (userName,phoneNum,ageNum,className,time_bm,chooseTime) VALUES('%s','%s','%s','%s','%s','%s')",
                        request.getParameter("name").toString(),
                        request.getParameter("phoneNum").toString(),
                        request.getParameter("ageNum").toString(),
                        strClassName.toString(),
                        dateNow.toString(),
                        request.getParameter("chooseTime").toString());
                System.out.println(insertSql);

                out.println(responseMsg + " <div class=\"modal-footer\">\n" +
                        "                        <button type=\"button\" id=\"submit_dianzi\" data-dismiss=\"modal\" class=\"btn btn-primary\">完成</button>\n" +
                        "                    </div>");

                stmt.execute(insertSql);


                String queryStudentNumSql = String.format("select active_student_num from active_info where active_name='%s';",strClassName.toString());
                System.out.println(queryStudentNumSql);

                ResultSet numRs =  stmt.executeQuery(queryStudentNumSql);

                while(numRs.next()){
                    String studentNum = numRs.getString("active_student_num");
                    System.out.println(studentNum);

                    int iStdentNum = 1;
                    if(studentNum != null) {
                        iStdentNum+= Integer.parseInt(studentNum);
                    }
                    String updateSql = String.format("update active_info set active_student_num='%d' where active_name='%s'",iStdentNum,strClassName.toString());
                    System.out.println(updateSql);

                    stmt.execute(updateSql);

                    break;
                }

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
        System.out.println(responseMsg);
    }

    public void destroy()
    {
        // 什么也不做
    }
}
