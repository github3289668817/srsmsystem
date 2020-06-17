package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import per.xgt.dto.DtoClassDetails;
import per.xgt.pojo.Class;

import java.util.List;

@Mapper
public interface classMapper {

    public List<Class> findClassByMajorId(int majorId);

    public List<DtoClassDetails> getClassDetails();

    public int getClassByClassName(@Param("className") String className);

    public int addClass(@Param("className") String className,@Param("majorId") int majorId,@Param("instructorId") String instructorId);

    public int getCountByClassId(int studentClassId);

    public int findClassIdByMajorName(int studentMajorId, String year);

}
