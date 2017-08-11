package com.Controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class dianzi_kc_1 extends HttpServlet {

    private String message;

    public void init() throws ServletException
    {
        // 执行必需的初始化
        message = "报名成功，工作人员确认后会有短信通知。";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String responseMsg = "<p>" + message + "</p>";
        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println(responseMsg );
        System.out.print(responseMsg);
        System.out.print(message);
    }

    public void destroy()
    {
        // 什么也不做
    }
}
