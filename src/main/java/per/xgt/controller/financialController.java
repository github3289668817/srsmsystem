package per.xgt.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import per.xgt.dto.DtoTuition;
import per.xgt.dto.DtoTuitionTu;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.service.financialService;
import per.xgt.service.studentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class financialController {

    @Resource
    private financialService financialService;

    @Resource
    private studentService studentService;

    @RequestMapping("/getAllStudentByFilter/{schoolId}/{majorId}/{classId}")
    @ResponseBody
    public Result getAllStudentByFilter(
            @PathVariable("schoolId") int schoolId,
            @PathVariable("majorId") int majorId,
            @PathVariable("classId") int classId,
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize
    ){
        return financialService.getAllStudentByFilter(schoolId, majorId, classId, pageIndex, pageSize);
    }

    @RequestMapping("/tuition/{studentNo}")
    public ModelAndView getStudentByStudentNo(
        @PathVariable("studentNo") String studentNo
    ){
        DtoTuition student = studentService.getStudentByStudentNo(studentNo);
        ModelAndView modelAndView = new ModelAndView("tuition");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @RequestMapping("getOneStudentByStudentNo")
    @ResponseBody
    public ResultMessage getOneStudentByStudentNo(String studentNo){
        return studentService.getOneStudentByStudentNo(studentNo);
    }

    @RequestMapping("/paythetuition")
    @ResponseBody
    public ResultMessage paythetuition(String studentNo,String moneyPay){
        double money = Double.parseDouble(moneyPay);
        return financialService.paythetuition(studentNo,money);
    }

    @RequestMapping("/getTuitionTu")
    @ResponseBody
    public List<DtoTuitionTu> tuitionTu(){
        List<DtoTuitionTu> tuition = financialService.tuitionTu();
        return tuition;
    }

}
