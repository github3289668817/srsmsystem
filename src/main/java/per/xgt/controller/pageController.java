package per.xgt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class pageController {

    @RequestMapping("/loginmsg")
    public String loginmsg() {
        return "loginmsg";
    }


    @RequestMapping("/backlogin")
    public String backlogin(){
        return "index";
    }


    @RequestMapping("/studentstatistics")
    public String sstatistics(){
        return "studentstatistics";
    }

    @RequestMapping("/empstatistics")
    public String empstatistics(){
        return "empstatistics";
    }

    @RequestMapping("/schoolstatistics")
    public String schoolstatistics(){
        return "schoolstatistics";
    }

    @RequestMapping("/addschool")
    public String addschool(){
        return "addschool";
    }

    @RequestMapping("/majorstatistics")
    public String majorstatistics(){
        return "majorstatistics";
    }

    @RequestMapping("/addmajor")
    public String addmajor(){
        return "addmajor";
    }

    @RequestMapping("/classstatistics")
    public String classstatistics(){
        return "classstatistics";
    }

    @RequestMapping("/addclass")
    public String addclass(){
        return "addclass";
    }

    @RequestMapping("/financialstatistics")
    public String financialstatistics(){
        return "financialstatistics";
    }

    @RequestMapping("/tuitioncollection")
    public String tuitioncollection(){
        return "tuitioncollection";
    }

    @RequestMapping("/toCheck")
    public String toCheck(){
        return "check";
    }

    @RequestMapping("/tuitiontu")
    public String tuitiontu(){
        return "tuitiontu";
    }

    @RequestMapping("/studentTu")
    public String studentTu(){
        return "studentTu";
    }

    @RequestMapping("/toAddEmp")
    public String toAddEmp(){
        return "addemp";
    }

    @RequestMapping("/dormitorydetails")
    public String dormitorydetails(){
        return "dormitorydetails";
    }

    @RequestMapping("/addstudentPage")
    public String addstudent(){
        return "addstudent";
    }

    @RequestMapping("/studentbatch")
    public String studentbatch(){
        return "studentbatch";
    }

}
