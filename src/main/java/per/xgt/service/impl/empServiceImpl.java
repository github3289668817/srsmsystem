package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.empMapper;
import per.xgt.dao.userMapper;
import per.xgt.dto.DtoAddEmp;
import per.xgt.dto.DtoEmp;
import per.xgt.dto.Result;
import per.xgt.pojo.Emp;
import per.xgt.service.empService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class empServiceImpl implements empService {

    @Resource
    private empMapper empMapper;

    @Resource
    private userMapper userMapper;


    @Override
    public Result findAllEmpByDepartmentId(int empDepartmentId, int pageIndex, int pageSize) {
        ArrayList<DtoEmp> dtoEmps = new ArrayList<>();
        List<Emp> pojoeEmps = empMapper.findAllEmpByDepartmentId(empDepartmentId);
        for (Emp emp:pojoeEmps) {
            dtoEmps.add(new DtoEmp(emp.getEmpNo(),emp.getEmpName(),emp.getEmpGender(),emp.getEmpPhone(),emp.getDepartmentName(),emp.getRoleName()));
        }
        int count = dtoEmps.size();
        int pageStart = (pageIndex-1)*pageSize;
        int pageEnd;
        if (pageIndex*pageSize<count){
            pageEnd = pageIndex*pageSize;
        }else {
            pageEnd = count;
        }
        List<DtoEmp> newEmps = dtoEmps.subList(pageStart, pageEnd);
        Result<DtoEmp> emps = new Result<>(200, "成功", count, newEmps);

        return emps;
    }

    @Override
    public List<Emp> findAllInstructor() {
        return empMapper.findAllInstructor();
    }

    @Override
    public String addEmp(String empName, int empRoleId, int empDepartmentId, String empGender, String empCdardNo) {
        Calendar instance = Calendar.getInstance();
        String empNo;
        String empPassword;
        String addEmpNo = "";
        String year = String.valueOf(instance.get(Calendar.YEAR));
        String role = String.format("%02d", empRoleId);
        String department = String.format("%02d", empDepartmentId);
        String count = String.format("%04d",empMapper.getAddEmpCount(year,role,department)+1);
        empNo = year+role+department+count;
        empPassword = empCdardNo.substring(empCdardNo.length()-6);
        int i = empMapper.addEmp(empNo,empPassword,empName,empRoleId,empDepartmentId,empGender,empCdardNo);
        int j = userMapper.addUser(empNo,empPassword,empName,empRoleId,empGender);
        if (i > 0 && j > 0){
            addEmpNo = empNo;
        }
        return addEmpNo;
    }

    @Override
    public DtoAddEmp findOneEmpByEmpNo(String empNo) {
        return empMapper.findOneEmpByEmpNo(empNo);
    }


}
