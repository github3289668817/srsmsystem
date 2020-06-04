package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import per.xgt.pojo.Major;

import java.util.List;

@Mapper
public interface majorMapper {

    public List<Major> findMajorBySchoolId(int schoolId);

    public List<Major> getMajorsBySchoolId(@Param("schoolId") int schoolId);

    public int findMajorByMajorName(String majorName);

    public int addMajor(String majorName,int schoolId);

    public List<Major> findAllMajor();

}
