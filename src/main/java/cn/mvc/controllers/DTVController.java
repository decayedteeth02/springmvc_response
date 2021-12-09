package cn.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
//从model中获取指定的属性写入session中
//这种方式依赖model
@SessionAttributes("type")
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

//    通过modelAndView传输数据到视图
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(){
        ModelAndView mv=new ModelAndView("main");
        mv.addObject("type","modelAndView");
        return mv;
    }


//    通过参数绑定的方式获取servletapi-session
    @RequestMapping("/servletapi/session")
    public String session01(HttpSession session){
        session.setAttribute("type","servletapi-session");
        return "main";
    }

    @Autowired
    private HttpSession session;//推荐使用
//    通过自动注入的方式获取servlet api
    @RequestMapping("/autowired/session")
    public String session02(){
        session.setAttribute("type","autowired-session");
        return "main";
    }

//    @SessionAttribute 获取session
//    required 用来设置session中某个属性必须存在，否则就会报错
//    用来映射你上一个addAttribute的值
    @RequestMapping("/annotation/session")
    public String session03(@SessionAttribute(value = "type",required = false) String type){
        System.out.println(type);
        return "main";
    }

}
