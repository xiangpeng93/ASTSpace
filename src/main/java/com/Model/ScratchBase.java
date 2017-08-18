package com.Model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Created by xiangpeng on 2017/8/18.
 */
public class ScratchBase  extends  HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astsapce.org>");

        String responseMsg = "<div class=\"thumbnail\" id=\"className\" name=\"Scratch:猫咪寻宝之旅\">\n" +
                "            <img src=\"img/ScratchBase.png\" alt=\"logo\">\n" +
                "            <div class=\"caption\">\n" +
                "                <a herf=\"#\">\n" +
                "                <h3>【魔抓传奇】第一期：猫咪寻宝之旅</h3>\n" +
                "                </a>\n" +
                "                <p><ul>\n" +
                "<li>初遇公主：Scratch入门，了解编程环境、积木块的概念和创建程序的方法</li>\n" +
                "<li>保卫西瓜：数字与逻辑运算、随机数、侦测、添加声音</li>\n" +
                "<li>搜集钱袋：动作与画笔模块的积木、图章使用绘制几何图形、计时器</li>\n" +
                "<li接住苹果：角色克隆、神奇的重复执行</li>\n" +
                "<li>燃放烟花：外观特效、声效、克隆</li>\n"+
                "<li>建造房屋：消息的广播与接收、参数、创建自己的积木块</li>\n"+
                "<li>痛打老鼠：获得用户输入、读取字符、执行算数运算</li>\n"+
                "<li>森林大战：综合运用</li>\n"+
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
