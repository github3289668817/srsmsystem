package per.xgt.service.impl;

import org.springframework.stereotype.Service;

import per.xgt.dao.departmentMapper;
import per.xgt.dto.DtoDepartment;
import per.xgt.pojo.Department;
import per.xgt.service.departmentService;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class departmentServiceImpl implements departmentService {

    @Resource
    private departmentMapper departmentMapper;

    @Override
    public List<DtoDepartment> findAllDepartment() {
        List<Department> pojoDepartments = departmentMapper.findAllDepartment();
        List<DtoDepartment> dtoDepartments = new ArrayList<>();
        for (Department department:pojoDepartments) {
            dtoDepartments.add(new DtoDepartment(department.getDepartmentId(),department.getDepartmentName()));
        }
        return dtoDepartments;
    }

}
