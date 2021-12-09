package cn.mvc.controllers;

import cn.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public String session03(@SessionAttribute(value = "type",required = false) String type,Model model){
        System.out.println(type);
        return "main";
    }

    @RequestMapping("/ModelAttribut/session")
    public String session04(){
        session2.setAttribute("type","ModelAttribute-session");
        return "main";
    }

    @RequestMapping("/thread/test")
    public String session05(String name, HttpServletRequest request) throws InterruptedException {
        String username=name;
        Thread.sleep(3000);
        session2.setAttribute("type",username);
        return "main";
    }

//    通过@ModelAttribute获取Servlet api--session
//    解决：
//    1.自己定义mysql语句，只更新指定的那些字段
//    2.如果无法定制sq.l语句，可以在更新在前查询，只能在springmvc绑定请求参数之前查询，利用@ModelAttribute就可以在绑定参数之前查询

    HttpSession session2;
    @ModelAttribute
    //@ModelAttribute 写在方法上面 @ModelAttribute 会在当前处理器中处理所有的处理方法钱调用
    public void showModelAttribute(HttpSession session){
        System.out.println("showModelAttribute");
        this.session2=session;
    }

    @ModelAttribute
    public void initUser(Model model){
//        在数据库中查询user select * from User
        User user=new User();
        user.setId(1);
        user.setUsername("cc");
        user.setPassword("123456");
        model.addAttribute("user",user);
        //spring mvc在进行参数绑定之前，会将model中的根参数名符合拿出来并合并，新提交的就会覆盖，确实的字段就会保留
    }

    @RequestMapping("/update")
    public String update(User user) {
        //update user set id=?,username=?,password=? where id=?
        System.out.println(user);
        return "main";
    }

}
