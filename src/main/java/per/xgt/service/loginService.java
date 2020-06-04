package per.xgt.service;

import per.xgt.dto.ResultMessage;
import per.xgt.pojo.User;

public interface loginService {

    public User login(String username,String password,int logintype);

    public ResultMessage changePassword(String oldPassword, String newPassword, User user);
}
