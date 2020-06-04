package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.classMapper;
import per.xgt.dto.DtoClassDetails;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Class;
import per.xgt.service.classService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class classServiceImpl implements classService {

    @Resource
    private classMapper classMapper;

    @Override
    public List<Class> findClassByMajorId(String majorId) {
        int id = Integer.parseInt(majorId);
        return classMapper.findClassByMajorId(id);
    }

    @Override
    public Result getClassDetails(int pageIndex,int pageSize) {
        List<DtoClassDetails> classes = classMapper.getClassDetails();
        int count = classes.size();
        int pageStart = (pageIndex-1)*pageSize;
        int pageEnd = pageIndex*pageSize<count ? pageIndex*pageSize : count;
        List<DtoClassDetails> classDetails = classes.subList(pageStart, pageEnd);
        Result<DtoClassDetails> result = new Result<>(200, "成功", count, classDetails);
        return result;
    }

    @Override
    public ResultMessage addClass(String className, int majorId, String instructorId) {
        ResultMessage resultMessage = null;
        int count = classMapper.getClassByClassName(className);
        if (count>0){
            resultMessage = new ResultMessage(105,"班级已存在");
        }else {
            int i = classMapper.addClass(className, majorId, instructorId);
            if (i>0){
                resultMessage = new ResultMessage(200,"新增班级成功");
            }else {
                resultMessage = new ResultMessage(500,"服务器内部错误，请联系管理员");
            }
        }
        return resultMessage;
    }

}
