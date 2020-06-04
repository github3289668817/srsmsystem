package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.roleMapper;
import per.xgt.pojo.Role;
import per.xgt.service.roleService;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class roleServiceImpl implements roleService {

    @Resource
    private roleMapper roleMapper;

    @Override
    public List<Role> findAllRole() {
        List<Role> allRole = roleMapper.findAllRole();
        Iterator<Role> iterator = allRole.iterator();
        while(iterator.hasNext()){
            if (iterator.next().getRoleId()==1){
                iterator.remove();
            }
        }
        return allRole;
    }
}
