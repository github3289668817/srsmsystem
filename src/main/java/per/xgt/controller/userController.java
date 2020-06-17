package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.User;
import per.xgt.service.loginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class userController {

    @Resource
    private loginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultMessage login(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int logintype = Integer.parseInt(request.getParameter("logintype"));
        User user = loginService.login(username, password, logintype);
        if (user != null){
            session.setAttribute("user", user);
            return new ResultMessage(200,"登陆成功");
        }
        return new ResultMessage(400,"账号或密码错误，请重试");
    }

    @RequestMapping("/service")
    public String service(){
        return "main";
    }

    @RequestMapping("/toChangePassword")
    public ModelAndView toChangePassword(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("changePassword");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public ResultMessage changePassword(String oldPassword,String newPassword,HttpSession session){
        User user = (User) session.getAttribute("user");
        return loginService.changePassword(oldPassword,newPassword,user);
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

}
