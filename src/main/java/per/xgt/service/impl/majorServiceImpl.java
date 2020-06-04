package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.majorMapper;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Major;
import per.xgt.service.majorService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class majorServiceImpl implements majorService {

    @Resource
    private majorMapper majorMapper;

    @Override
    public List<Major> findMajorBySchoolId(String schoolId) {
        int id = Integer.parseInt(schoolId);
        return majorMapper.findMajorBySchoolId(id);
    }

    @Override
    public Result getMajorsBySchoolId(int schoolId, int pageIndex, int pageSize) {
        List<Major> majors = majorMapper.getMajorsBySchoolId(schoolId);
        int count = majors.size();
        int pageStart = (pageIndex-1)*pageSize;
        int pageEnd = pageIndex*pageSize<count ? pageIndex*pageSize : count;
        List<Major> major = majors.subList(pageStart, pageEnd);
        Result<Major> result = new Result<>(200, "成功", count, major);
        return result;
    }

    @Override
    public ResultMessage addMajor(int schoolId, String majorName) {
        ResultMessage resultMessage = null;
        int i = majorMapper.findMajorByMajorName(majorName);
        if (i>0){
            resultMessage = new ResultMessage(105, "此专业已开设");
            return resultMessage;
        }
        int count = majorMapper.addMajor(majorName, schoolId);
        if (count>0){
            resultMessage = new ResultMessage(200, "添加成功");
        }else {
            resultMessage = new ResultMessage(500, "服务器错误，请联系管理员操作");
        }

        return resultMessage;
    }

    @Override
    public List<Major> findAllMajor() {
        return majorMapper.findAllMajor();
    }

}
