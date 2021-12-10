package cn.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResponseController {

    @RequestMapping("/response1")
    public String response1(){
        return "index";
    }


//    return默认是转发
//    forward需要自己返回完整的视图逻辑名路径
    @RequestMapping("/response2")
    public String response2(){
        return "forward:login.jsp";
    }


//    redirect 重定向必须加上 不参与视图解析器
//    springmvc会在底层给我们加上项目名
    @RequestMapping("/response3")
    public String response3(){
        return "redirect:/login.jsp";
    }
}
