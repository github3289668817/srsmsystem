package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.pojo.Role;

import java.util.List;

@Mapper
public interface roleMapper {

    public List<Role> findAllRole();

}
