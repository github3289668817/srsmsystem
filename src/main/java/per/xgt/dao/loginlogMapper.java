package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.pojo.Loginlog;

import java.util.List;

@Mapper
public interface loginlogMapper {

    public int insertLoginLog(Loginlog loginlog);

    public List<Loginlog> getAllLoginlogs(String userNo);

}
