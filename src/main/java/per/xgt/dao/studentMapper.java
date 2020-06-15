package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.dto.DtoStudentTu;
import per.xgt.pojo.Student;

import java.util.List;

@Mapper
public interface studentMapper {

    //根据学号密码查找一个学生
    public Student findOneByStudentNo(String username,String password);

    //根据筛选条件查询学生报到情况
    public List<Student> findAllStudentByFilter(int schoolId,int majorId,int classId);

    //根据学号查询一个学生
    public Student getStudentByStudentNo(String studentNo);

    //学生缴费
    public int payTuition(String studentNo,String xueqi,double money);

    //学生报到
     public int check(String studentNo, String xueqi);

     //查询男女学生人数
    public List<DtoStudentTu> studentTu();

    //修改密码
    public int updateStudentPassword(String userNo, String newPassword);

    //添加一个学生
    public int addOneStudent(String studentNo, String studentPassword, int i, String studentName, String studentGender, String studentCardNo, int studentSchoolId, int studentMajorId, int studentClassId, String studentDormitoryId);
}
