package per.xgt.service;

import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;
import per.xgt.pojo.School;

import java.util.List;

public interface schoolService {

    public List<School> findAllSchool();

    public Result SchoolDetails(int pageIndex,int pageSize);

    public ResultMessage addSchool(String schoolName);

}
