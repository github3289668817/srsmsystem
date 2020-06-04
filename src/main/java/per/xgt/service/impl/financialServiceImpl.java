package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.financialMapper;
import per.xgt.dto.*;
import per.xgt.pojo.Student;
import per.xgt.service.financialService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class financialServiceImpl implements financialService {

    @Resource
    private financialMapper financialMapper;


    @Override
    public Result getAllStudentByFilter(int schoolId, int majorId, int classId,int pageIndex,int pageSize) {
        List<Student> pojoStudents = financialMapper.getAllStudentByFilter(schoolId, majorId, classId);

        List<DtoFinancialDetails> financialDetails = new ArrayList<>();
        for (Student student:pojoStudents) {
            String studentNo = student.getStudentNo();
            String studentName = student.getStudentName();
            String schoolName = student.getSchoolName();
            String majorName = student.getMajorName();
            String className = student.getClassName();
            Double studentSemesterMoney = 0.0;

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int studentyear = Integer.parseInt(studentNo.substring(0,4));
            if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
                studentSemesterMoney = student.getStudentSemester1Money();
            }
            if (year - studentyear == 1 && month >= 3 && month <= 8) {
                studentSemesterMoney = student.getStudentSemester2Money();
            }
            if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
                studentSemesterMoney = student.getStudentSemester3Money();
            }
            if (year - studentyear == 2 && month >= 3 && month <= 8) {
                studentSemesterMoney = student.getStudentSemester4Money();
            }
            if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
                studentSemesterMoney = student.getStudentSemester5Money();
            }
            if (year - studentyear == 3 && month >= 3 && month <= 8) {
                studentSemesterMoney = student.getStudentSemester6Money();
            }
            if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
                studentSemesterMoney = student.getStudentSemester7Money();
            }
            if (year - studentyear == 4 && month >= 3 && month <= 8) {
                studentSemesterMoney = student.getStudentSemester8Money();
            }

            financialDetails.add(new DtoFinancialDetails(studentNo, studentName, schoolName, majorName, className, studentSemesterMoney));

        }

        int count = financialDetails.size();
        int pageStart = (pageIndex-1)*pageSize;
        int pageEnd = pageIndex*pageSize<count?pageIndex*pageSize:count;
        List<DtoFinancialDetails> newFinancialDetails = financialDetails.subList(pageStart, pageEnd);

        Result<DtoFinancialDetails> result = new Result<>(200, "成功", count, newFinancialDetails);

        return result;
    }

    @Override
    public ResultMessage paythetuition(String studentNo,double moneyPay) {
        ResultMessage resultMessage = null;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int studentyear = Integer.parseInt(studentNo.substring(0,4));
        String xueqi = "";
        if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
            xueqi = "studentSemester1Money";
        }
        if (year - studentyear == 1 && month >= 3 && month <= 8) {
            xueqi = "studentSemester2Money";
        }
        if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
            xueqi = "studentSemester3Money";
        }
        if (year - studentyear == 2 && month >= 3 && month <= 8) {
            xueqi = "studentSemester4Money";
        }
        if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
            xueqi = "studentSemester5Money";
        }
        if (year - studentyear == 3 && month >= 3 && month <= 8) {
            xueqi = "studentSemester6Money";
        }
        if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
            xueqi = "studentSemester7Money";
        }
        if (year - studentyear == 4 && month >= 3 && month <= 8) {
            xueqi = "studentSemester8Money";
        }
        int i = financialMapper.paythetuition(moneyPay,xueqi,studentNo);
        if (i > 0){
            resultMessage = new ResultMessage(200,"收费成功");
        }else {
            resultMessage = new ResultMessage(500,"服务器内部错误，请重试");
        }
        return resultMessage;
    }

    @Override
    public List<DtoTuitionTu> tuitionTu() {
        return financialMapper.tuitionTu();
    }

}
