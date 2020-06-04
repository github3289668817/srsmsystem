package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.pojo.Loginlog;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.User;
import per.xgt.service.loginlogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class loginlogController {

    @Resource
    private loginlogService loginlogService;

    @RequestMapping("/insertLoginmsg")
    @ResponseBody
    public ResultMessage insertLoginlog(String ip, HttpSession session){
        User user = (User)session.getAttribute("user");
        String userNo = user.getUserNo();
        Date date = new Date();
        String sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Loginlog loginlog = new Loginlog(null, ip, userNo, sdf);
        int i = loginlogService.insertLoginlog(loginlog);
        if (i>0){
            ResultMessage resultMessage = new ResultMessage(200, "");
            return resultMessage;
        }else {
            ResultMessage resultMessage = new ResultMessage(400, "登录异常，请联系管理员");
            return resultMessage;
        }
    }
    
    @ResponseBody
    @RequestMapping("/getAllLoginlogs")
    public List<Loginlog> getAllLoginlogs(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userNo = user.getUserNo();
        List<Loginlog> logs = loginlogService.getAllLoginlogs(userNo);
        return logs;
    }

}
