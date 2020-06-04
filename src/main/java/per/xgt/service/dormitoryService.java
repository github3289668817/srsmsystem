package per.xgt.service;

import per.xgt.dto.DtoDormitoryDetails;
import per.xgt.dto.Result;

public interface dormitoryService {

    public Result<DtoDormitoryDetails> getDormitoryDetails(int dormitoryType,int isFull,int pageIndex,int pageSize);

}
