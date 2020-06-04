package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Class;
import per.xgt.service.classService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class classController {

    @Resource
    private classService classService;

    @RequestMapping("/findClassByMajorId/{majorId}")
    @ResponseBody
    public List<Class> findClassByMajorId(@PathVariable("majorId") String majorId){
        return classService.findClassByMajorId(majorId);
    }

    @RequestMapping("/getClassDetails")
    @ResponseBody
    public Result getClassDetails(
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize
    ){
        return classService.getClassDetails(pageIndex, pageSize);
    }

    @RequestMapping("/addClass")
    @ResponseBody
    public ResultMessage addClass(String majorId,String instructorId,String className){
        int majorid = Integer.parseInt(majorId);
        return classService.addClass(className, majorid, instructorId);
    }

}
