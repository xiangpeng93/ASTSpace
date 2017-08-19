package com.Model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by xiangpeng on 2017/8/18.
 */
public class ArduinoBase extends HttpServlet  {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");
        String responseMsg = "<div class=\"thumbnail\" id=\"className\" name=\"Arduino 入门\">\n" +
                "            <img src=\"img/arduino_logo.jpg\" alt=\"arduino_logo\">\n" +
                "            <div class=\"caption\">\n" +
                "                <a herf=\"#\">\n" +
                "                <h3>Arduino编程系列课程之入门篇</h3>\n" +
                "                </a>\n" +
                "                <p><ul>\n" +

                "<li>开课时间暂定为9月10日。</li>\n" +
                "           <li>预备课：了解软件Scratch以及智能硬件Arduino；熟悉电子元器件和基础电路，制作云朵灯。</li>\n" +
                "           <li>点亮LED：从点亮一盏小灯开始，学习数字量并实现延迟灯，过渡到控制多盏小灯，完成联机打地鼠。</li>\n" +
                "<li>玩转LED：引入模拟量，实现呼吸灯，配合电位器学习映射，制作可调控台灯。</li>\n" +
                "<li>DIY时钟：学习了解数码管，从一位数码管过渡到多位数码管，制作电子时钟。</li>\n" +
                "<li>创客时间：综合设计课程，学习电路设计、焊接、搭建。</li>\n" +
                "</ul>" +
                "                <p>\n" +
                "                    <!-- Button trigger modal -->\n" +
                "                    <button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" data-target=\"#myModal\">\n" +
                "                        报名入口\n" +
                "                    </button>\n" +
                "                </p>\n" +
                "            </div>\n" +
                "        </div>";
        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println(responseMsg);
        System.out.println(responseMsg);
    }

    public void destroy()
    {
        // 什么也不做
    }
}
