package per.xgt.service;

import per.xgt.dto.DtoTuitionTu;
import per.xgt.dto.Result;
import per.xgt.dto.ResultMessage;

import java.util.List;

public interface financialService {

    public Result getAllStudentByFilter(int schoolId, int majorId, int classId,int pageIndex,int pageSize);

    public ResultMessage paythetuition(String studentNo,double moneyPay);

    public List<DtoTuitionTu> tuitionTu();
}
