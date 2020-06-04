package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.dto.DtoDepartment;
import per.xgt.service.departmentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class departmentController {

    @Resource
    private departmentService departmentService;

    @RequestMapping("/findAllDepartment")
    @ResponseBody
    public List<DtoDepartment> findAllDepartment(){
        return departmentService.findAllDepartment();
    }

}
