package per.xgt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import per.xgt.dto.DtoSchoolDetails;
import per.xgt.pojo.School;

import java.util.List;
import java.util.Map;

@Mapper
public interface schoolMapper {

    public List<School> findAllSchool();

    public List<DtoSchoolDetails> SchoolDetails();

    public int addSchool(@Param("schoolName") String schoolName);

    public int findSchoolBySchoolName(@Param("schoolName") String schoolName);

    public int findSchoolIdBySchoolName(String schoolName);

}
