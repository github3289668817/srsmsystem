package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.empMapper;
import per.xgt.dao.userMapper;
import per.xgt.dao.studentMapper;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Emp;
import per.xgt.pojo.Student;
import per.xgt.pojo.User;
import per.xgt.service.loginService;

import javax.annotation.Resource;

@Service
public class loginServiceImpl implements loginService {

    @Resource
    private userMapper userMapper;

    @Resource
    private studentMapper studentMapper;

    @Resource
    private empMapper empMapper;


    @Override
    public User login(String username, String password, int logintype) {
        User user = userMapper.findOneByUserNo(username, password);
        if (user != null) {
            if (logintype == 1) {
                Student student = studentMapper.findOneByStudentNo(username, password);
                if (student != null) {
                    return user;
                }
            } else if (logintype == 2) {
                Emp emp = empMapper.findOneByEmpNo(username, password);
                if (emp != null) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public ResultMessage changePassword(String oldPassword, String newPassword, User user) {
        ResultMessage resultMessage = null;
        String userNo = user.getUserNo();
        int userRoleId = user.getUserRoleId();
        int a = 0;
        int b = 0;
        User isUser = userMapper.findOneByUserNo(userNo, oldPassword);
        if (isUser == null) {
            resultMessage = new ResultMessage(404, "原密码输入错误，请重新输入");
        } else {
            a = userMapper.updateUserPassword(userNo, newPassword);

            if (userRoleId == 1) {
                b = studentMapper.updateStudentPassword(userNo, newPassword);
            } else {
                b = empMapper.updateEmpPassword(userNo, newPassword);
            }

            if (a > 0 && b > 0) {
                resultMessage = new ResultMessage(200, "密码修改成功");
            } else {
                resultMessage = new ResultMessage(405, "操作过于频繁，请稍后再试");
            }

        }
        return resultMessage;
    }
}
