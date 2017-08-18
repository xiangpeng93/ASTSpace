package com.Model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Created by xiangpeng on 2017/8/18.
 */
public class BIOBase extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");

        String responseMsg = "<div class=\"thumbnail\" id=\"className\" name=\"生物黑客之小鸡孵化\">\n" +
                "            <img src=\"img/bio_base1.jpg\" alt=\"arduino_logo\">\n" +
                "            <div class=\"caption\">\n" +
                "                <a herf=\"#\">\n" +
                "                <h3>【生物黑客】从0开始，孵化小鸡</h3>\n" +
                "                </a>\n" +
                "                <p><ul>\n" +
                "           <li>观察鸡蛋孵化过程中胚胎发育变化。</li>\n" +
                "<li>记录实验数据。</li>\n" +
                "<li>小鸡孵化出来后，提取DNA，复制性别基因，鉴定小鸡性别。</li>\n" +
                "<li>整理数据，撰写论文。</li>\n" +
                "<li>制作PPT，整理研究思路，向公众讲述整个研究过程。</li>\n"+
                "</ul>" +
                "</p>\n" +
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
}
