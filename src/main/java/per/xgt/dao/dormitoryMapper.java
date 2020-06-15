package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import per.xgt.dto.DtoDormitoryDetails;
import per.xgt.pojo.Dormitory;

import java.util.List;

@Mapper
public interface dormitoryMapper {

    public List<DtoDormitoryDetails> getDormitoryDetails(int dormitoryType,int isFull);

    public List<Dormitory> findAllDormitorysByGender(String studentGender);

}
