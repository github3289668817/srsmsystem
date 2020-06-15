package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.dto.Result;
import per.xgt.pojo.Dormitory;
import per.xgt.service.dormitoryService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class dormitoryController {

    @Resource
    private dormitoryService dormitoryService;

    @RequestMapping("/getdormitorys/{dormitoryType}/{isFull}")
    @ResponseBody
    public Result getdormitoryDetails(
            @PathVariable("dormitoryType") int dormitoryType,
            @PathVariable("isFull") int isFull,
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize
    ){
        return dormitoryService.getDormitoryDetails(dormitoryType, isFull, pageIndex, pageSize);
    }

    @RequestMapping("/findAllDormitorysByGender/{studentGender}")
    public List<Dormitory> findAllDormitorysByGender(
            @PathVariable("studentGender") String studentGender
    ){
        return dormitoryService.findAllDormitorysByGender(studentGender);
    }

}
