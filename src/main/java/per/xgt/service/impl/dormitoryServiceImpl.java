package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.dormitoryMapper;
import per.xgt.dto.DtoAddStudent;
import per.xgt.dto.DtoDormitoryDetails;
import per.xgt.dto.Result;
import per.xgt.pojo.Dormitory;
import per.xgt.service.dormitoryService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class dormitoryServiceImpl implements dormitoryService {

    @Resource
    private dormitoryMapper dormitoryMapper;

    @Override
    public Result<DtoDormitoryDetails> getDormitoryDetails(int dormitoryType, int isFull, int pageIndex, int pageSize) {

        int pageStart = 0;
        int pageEnd = 0;
        List<DtoDormitoryDetails> dormitoryDetails = dormitoryMapper.getDormitoryDetails(dormitoryType, isFull);
        int count = dormitoryDetails.size();
        pageStart = (pageIndex-1)*pageSize;
        if (pageIndex*pageSize<count){
            pageEnd = pageIndex*pageSize;
        }else {
            pageEnd = count;
        }
        List<DtoDormitoryDetails> dormitorys = dormitoryDetails.subList(pageStart, pageEnd);
        Result<DtoDormitoryDetails> result = new Result<>(200,"成功",count,dormitorys);

        return result;
    }

    @Override
    public List<Dormitory> findAllDormitorysByGender(String studentGender) {
        return dormitoryMapper.findAllDormitorysByGender(studentGender);
    }

    @Override
    public Result<DtoAddStudent> findAllStudentByDormitoryId(String dormitoryId) {
        List<DtoAddStudent> students = dormitoryMapper.findAllStudentByDormitoryId(dormitoryId);
        int count = students.size();
        Result<DtoAddStudent> retult = new Result<>(200, "成功", count, students);
        return retult;
    }
}
