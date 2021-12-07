package cn.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResponseController {

    @RequestMapping("/response1")
    public String response1(){
        return "index";
    }


}
