package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.pojo.Department;

import java.util.List;

@Mapper
public interface departmentMapper {

    public List<Department> findAllDepartment();

}
