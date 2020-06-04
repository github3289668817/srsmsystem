package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.School;
import per.xgt.service.schoolService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class schoolController {

    @Resource
    private schoolService schoolService;


    @RequestMapping("/findAllSchool")
    @ResponseBody
    public List<School> findAllSchool(){
        return schoolService.findAllSchool();
    }

    @RequestMapping("/schoolDetails")
    @ResponseBody
    public Result schoolDetails(@RequestParam("page") int pageIndex, @RequestParam("limit") int pageSize){
        Result result = schoolService.SchoolDetails(pageIndex, pageSize);
        return result;
    }

    @RequestMapping("/addSchool")
    @ResponseBody
    public ResultMessage addSchool(HttpServletRequest request){
        String schoolName = request.getParameter("schoolName");
        ResultMessage message = schoolService.addSchool(schoolName);
        return message;
    }

}
