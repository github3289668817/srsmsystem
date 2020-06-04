package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.schoolMapper;
import per.xgt.dto.DtoSchoolDetails;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.School;
import per.xgt.service.schoolService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class schoolServiceImpl implements schoolService {

    @Resource
    private schoolMapper schoolMapper;

    @Override
    public List<School> findAllSchool() {
        return schoolMapper.findAllSchool();
    }

    @Override
    public Result SchoolDetails(int pageIndex,int pageSize) {
        List<DtoSchoolDetails> dtoSchoolDetails = schoolMapper.SchoolDetails();

        int count = dtoSchoolDetails.size();
        int pageStart = (pageIndex-1)*pageSize;
        int pageEnd;
        if (pageIndex*pageSize<count){
            pageEnd = pageIndex*pageSize;
        }else {
            pageEnd = count;
        }
        List<DtoSchoolDetails> schools = dtoSchoolDetails.subList(pageStart, pageEnd);
        Result<DtoSchoolDetails> schoolDetails = new Result<>(200, "成功", count, schools);
        return schoolDetails;
    }

    @Override
    public ResultMessage addSchool(String schoolName) {
        int count = schoolMapper.findSchoolBySchoolName(schoolName);
        if (count>0){
            return new ResultMessage(105,"学院名不可重复");
        }
        if ("".equals(schoolName)){
            return new ResultMessage(100,"学院名不能为空");
        }
        int i = schoolMapper.addSchool(schoolName);
        if (i > 0){
            return new ResultMessage(200,"添加成功");
        }
        return new ResultMessage(500,"服务器出现错误，请重试");
    }

}
