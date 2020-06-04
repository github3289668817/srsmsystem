package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import per.xgt.dto.DtoAddEmp;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Emp;
import per.xgt.service.empService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class empController {

    @Resource
    private empService empService;

    @RequestMapping("/findAllEmpByDepartmentId/{departmentId}")
    @ResponseBody
    public Result findAllEmpByDepartmentId(
            @PathVariable("departmentId") int departmentId,
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize
    ){
        Result emps = empService.findAllEmpByDepartmentId(departmentId, pageIndex, pageSize);
        return emps;
    }

    @RequestMapping("/findAllInstructor")
    @ResponseBody
    public List<Emp> findAllInstructor(){
        return empService.findAllInstructor();
    }

    @RequestMapping("/addEmp")
    @ResponseBody
    public DtoAddEmp addEmp(String empName, int empRoleId, int empDepartmentId, String empGender, String empCdardNo){
        String empNo = empService.addEmp(empName,empRoleId,empDepartmentId,empGender,empCdardNo);
        DtoAddEmp emp = empService.findOneEmpByEmpNo(empNo);
        return emp;
    }

    @RequestMapping("/showaddemp/{empNo}")
    public ModelAndView showAddEmp(
            @PathVariable("empNo") String empNo
    ){
        DtoAddEmp emp = empService.findOneEmpByEmpNo(empNo);
        ModelAndView modelAndView = new ModelAndView("showaddemp");
        modelAndView.addObject("emp", emp);
        return modelAndView;
    }

}
