package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.dto.DtoTuitionTu;
import per.xgt.pojo.Student;

import java.util.List;

@Mapper
public interface financialMapper {

    //根据筛选条件查看学生缴费情况
    public List<Student> getAllStudentByFilter(int schoolId, int majorId, int classId);

    public int paythetuition(Double moneyPay, String xueqi, String studentNo);

    public List<DtoTuitionTu> tuitionTu();
}
