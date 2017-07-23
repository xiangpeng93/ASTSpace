package com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

@Controller
public class myControl {
    @RequestMapping("test")  // 请求url地址映射，类似Struts的action-mapping
    public String testLogin() {
        return "index";
    }

}

