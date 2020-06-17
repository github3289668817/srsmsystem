package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.pojo.Student;
import per.xgt.pojo.User;

@Mapper
public interface userMapper {

    //根据用户账号密码查找一个用户
    public User findOneByUserNo(String username,String password);

    //根据账号查找一个用户
    public User findAUserByUserNo(String userNo);

    //修改用户密码
    public int updateUserPassword(String userNo, String newPassword);

    public int addUser(String userNo, String userPassword, String userName, int userRoleId, String userGender);

    public void addOneOfListStudent(Student student);
}
