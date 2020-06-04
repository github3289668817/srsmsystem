package per.xgt.service;

import per.xgt.dto.DtoAddEmp;
import per.xgt.dto.Result;
import per.xgt.pojo.Emp;

import java.util.List;


public interface empService {

    public Result findAllEmpByDepartmentId(int departmentId, int pageIndex, int pageSize);

    public List<Emp> findAllInstructor();

    public String addEmp(String empName, int empRoleId, int empDepartmentId, String empGender, String empCdardNo);

    public DtoAddEmp findOneEmpByEmpNo(String empNo);

}
