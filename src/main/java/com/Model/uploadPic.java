package com.Model;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Created by xiangpeng on 2017/8/18.
 */
public class uploadPic  extends  HttpServlet{
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
                "                <h3>魔抓传奇-猫咪寻宝之旅</h3>\n" +
                "                </a>\n" +
                "                <p><ul>\n" +
                "<li>开课时间暂定为9月16日。</li>\n" +
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
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");  //设置编码

        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
        String path = request.getRealPath("/upload");

        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
        /**
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到 对应目录的硬盘上
         */
        factory.setRepository(new File(path));
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024*1024) ;

        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);


        try {
            //可以上传多个文件
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);

            for(FileItem item : list)
            {
                //获取表单的属性名字
                String name = item.getFieldName();

                //如果获取的 表单信息是普通的 文本 信息
                if(item.isFormField())
                {
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                    String value = item.getString() ;

                    request.setAttribute(name, value);
                }
                //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
                else
                {
                    /**
                     * 以下三步，主要获取 上传文件的名字
                     */
                    //获取路径名
                    String value = item.getName() ;
                    //索引到最后一个反斜杠
                    int start = value.lastIndexOf("\\");
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
                    String filename = value.substring(start+1);

                    request.setAttribute(name, filename);

                    //真正写到磁盘上
                    //它抛出的异常 用exception 捕捉

                    //item.write( new File(path,filename) );//第三方提供的

                    //手动写的
                    OutputStream out = new FileOutputStream(new File(path,filename));

                    InputStream in = item.getInputStream() ;

                    int length = 0 ;
                    byte [] buf = new byte[1024] ;

                    System.out.println("获取上传文件的总共的容量："+item.getSize());

                    // in.read(buf) 每次读到的数据存放在   buf 数组中
                    while( (length = in.read(buf) ) != -1)
                    {
                        //在   buf 数组中 取出数据 写到 （输出流）磁盘上
                        out.write(buf, 0, length);

                    }

                    in.close();
                    out.close();
                }
            }



        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        request.getRequestDispatcher("index.html").forward(request, response);


    }

}
