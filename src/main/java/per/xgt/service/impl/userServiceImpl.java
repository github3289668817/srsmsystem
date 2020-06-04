package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.userMapper;
import per.xgt.pojo.User;
import per.xgt.service.userService;

import javax.annotation.Resource;

@Service
public class userServiceImpl implements userService {

    @Resource
    private userMapper userMapper;

    @Override
    public User findAUserByUserNo(String userNo) {
        return userMapper.findAUserByUserNo(userNo);
    }

}
