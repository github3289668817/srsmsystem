package per.xgt.service;

import per.xgt.dto.DtoAddStudent;
import per.xgt.dto.DtoDormitoryDetails;
import per.xgt.dto.Result;
import per.xgt.pojo.Dormitory;

import java.util.List;

public interface dormitoryService {

    public Result<DtoDormitoryDetails> getDormitoryDetails(int dormitoryType,int isFull,int pageIndex,int pageSize);

    public List<Dormitory> findAllDormitorysByGender(String studentGender);

    public Result<DtoAddStudent> findAllStudentByDormitoryId(String dormitoryId);

}
