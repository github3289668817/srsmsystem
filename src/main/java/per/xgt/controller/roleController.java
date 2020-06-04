package per.xgt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xgt.pojo.Role;
import per.xgt.service.roleService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class roleController {

    @Resource
    private roleService roleService;

    @RequestMapping("/findAllRole")
    @ResponseBody
    public List<Role> findAllRole(){
        return roleService.findAllRole();
    }


}
