package per.xgt.service;

import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.Major;

import java.util.List;

public interface majorService {

    public List<Major> findMajorBySchoolId(String schoolId);

    public Result getMajorsBySchoolId(int schoolId,int pageIndex,int pageSize);

    public ResultMessage addMajor(int schoolId,String majorName);

    public List<Major> findAllMajor();

}
