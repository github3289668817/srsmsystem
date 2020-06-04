package per.xgt.service;

import per.xgt.dto.*;

import java.util.List;


public interface studentService {

    public Result findAllStudentByFilter(int schoolId, int majorId, int classId, int register, int pageIndex, int pageSize);

    public DtoTuition getStudentByStudentNo(String studentNo);

    public ResultMessage getOneStudentByStudentNo(String studentNo);

    public DtoCheckStudent getAStudentByStudentNo(String studentNo);

    public ResultMessage isPay(String studentNo);

    public int payTuition(String studentNo, String total_amount);

    public int isCheck(String studentNo);

    public ResultMessage check(String studentNo);

    public List<DtoStudentTu> studentTu();
}
