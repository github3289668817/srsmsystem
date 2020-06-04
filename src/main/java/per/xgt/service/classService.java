package per.xgt.service;

import org.apache.ibatis.annotations.Param;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Class;

import java.util.List;

public interface classService {

    public List<Class> findClassByMajorId(String majorId);

    public Result getClassDetails(int pageIndex,int pageSize);

    public ResultMessage addClass(String className,int majorId,String instructorId);

}
