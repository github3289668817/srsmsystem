package per.xgt.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Major;
import per.xgt.service.majorService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class majorController {

    @Resource
    private majorService majorService;

    @RequestMapping("/findMajorBySchoolId/{schoolId}")
    @ResponseBody
    public List<Major> findMajorBySchoolId(
            @PathVariable("schoolId") String schoolId
    ){
        return majorService.findMajorBySchoolId(schoolId);
    }

    @RequestMapping("/getMajorsBySchoolId/{schoolId}")
    @ResponseBody
    public Result getMajorsBySchoolId(
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize,
            @PathVariable("schoolId") int schoolId
    ){
        Result result = majorService.getMajorsBySchoolId(schoolId, pageIndex, pageSize);
        return result;
    }

    @RequestMapping("/addMajor")
    @ResponseBody
    public ResultMessage addMajor(int schoolId, String majorName){
        return majorService.addMajor(schoolId, majorName);
    }

    @RequestMapping("/findAllMajor")
    @ResponseBody
    public List<Major> findAllMajor(){
        return majorService.findAllMajor();
    }

}
