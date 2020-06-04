package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import per.xgt.dto.DtoAddEmp;
import per.xgt.pojo.Emp;

import java.util.List;

@Mapper
public interface empMapper {

    public Emp findOneByEmpNo(String username,String password);

    public List<Emp> findAllEmpByDepartmentId(@Param("empDepartmentId") int empDepartmentId);

    public List<Emp> findAllInstructor();

    public int updateEmpPassword(String userNo, String newPassword);

    public int getAddEmpCount(String year, String role, String department);

    public int addEmp(String empNo, String empPassword, String empName, int empRoleId, int empDepartmentId, String empGender,String empCardNo);

    public DtoAddEmp findOneEmpByEmpNo(String empNo);
}
