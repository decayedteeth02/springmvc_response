package cn.mvc.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class DTVController {

//    @RequestMapping("/servletAPI")
//    public String servletAPI(HttpServletReq){
//
//
//    }

   // 使用model方式传输到视图
    @RequestMapping("/model")
    public String model(Model model){
        model.addAttribute("type","model");
        return "main";
    }

    @RequestMapping("/modelMap")
    public String model(ModelMap modelMap){
        modelMap.addAttribute("type","model");
        return "main";
    }

    @RequestMapping("/Map")
    public String map(Map map){
        map.put("type","model");
        return "main";
    }
}
