package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.classMapper;
import per.xgt.dao.studentMapper;
import per.xgt.dao.userMapper;
import per.xgt.dto.*;
import per.xgt.pojo.Student;
import per.xgt.service.studentService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
public class studentServiceImpl implements studentService {

    @Resource
    private studentMapper studentmapper;

    @Resource
    private classMapper classMapper;

    @Resource
    private userMapper userMapper;

    @Override
    public Result findAllStudentByFilter(int schoolId, int majorId, int classId, int register, int pageIndex, int pageSize) {
        int pageStart = 0;
        int pageEnd = 0;
        List<Student> pojostudents = studentmapper.findAllStudentByFilter(schoolId,majorId,classId);
        List<DtoStudent> students = new ArrayList<>();
        Result<DtoStudent> dtoStudents = null;
        for (Student student : pojostudents) {
            String studentNo =  student.getStudentNo();
            String studentName = student.getStudentName();
            String studentGender = student.getStudentGender();
            String className = student.getClassName();
            int flag = 0;
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int studentyear = Integer.parseInt(studentNo.substring(0, 4));
            if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
                flag = student.getStudentSemester1();
            }
            if (year - studentyear == 1 && month >= 3 && month <= 8) {
                flag = student.getStudentSemester2();
            }
            if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
                flag = student.getStudentSemester3();
            }
            if (year - studentyear == 2 && month >= 3 && month <= 8) {
                flag = student.getStudentSemester4();
            }
            if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
                flag = student.getStudentSemester5();
            }
            if (year - studentyear == 3 && month >= 3 && month <= 8) {
                flag = student.getStudentSemester6();
            }
            if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
                flag = student.getStudentSemester7();
            }
            if (year - studentyear == 4 && month >= 3 && month <= 8) {
                flag = student.getStudentSemester8();
            }
            students.add(new DtoStudent(studentNo, studentName, studentGender, className, flag == 1 ? "已报到" : "未报到"));
            if (register == 0) {
                Iterator<DtoStudent> iterator = students.iterator();
                while (iterator.hasNext()){
                    DtoStudent dtostudent = iterator.next();
                    if (dtostudent.getStudentSemester().equals("已报到")){
                        iterator.remove();
                    }
                }
            } else if (register == 1) {
                Iterator<DtoStudent> iterator = students.iterator();
                while (iterator.hasNext()){
                    DtoStudent dtostudent = iterator.next();
                    if (dtostudent.getStudentSemester().equals("未报到")){
                        iterator.remove();
                    }
                }
            }
        }
        int count = students.size();
        pageStart = (pageIndex-1)*pageSize;
        if (pageIndex*pageSize<count){
            pageEnd = pageIndex*pageSize;
        }else {
            pageEnd = count;
        }
        List<DtoStudent> newStudents = students.subList(pageStart, pageEnd);
        dtoStudents = new Result<>(200,"成功",count,newStudents);
        return dtoStudents;
    }

    @Override
    public DtoTuition getStudentByStudentNo(String studentNo) {
        Student student = studentmapper.getStudentByStudentNo(studentNo);
        String studentName = student.getStudentName();
        String schoolName = student.getSchoolName();
        String majorName = student.getMajorName();
        String className = student.getClassName();
        Double Money = 1.0;
        DtoTuition tuition = new DtoTuition(studentNo,studentName,schoolName,majorName,className,Money,null);
        return tuition;
    }

    @Override
    public ResultMessage getOneStudentByStudentNo(String studentNo) {
        ResultMessage resultMessage = null;
        Student student = studentmapper.getStudentByStudentNo(studentNo);
        if (student == null){
            resultMessage = new ResultMessage(402,"没有此学生，请确认");
        }else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int studentyear = Integer.parseInt(studentNo.substring(0, 4));
            double money = 0.0;
            if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
                money = student.getStudentSemester1Money();
            }
            if (year - studentyear == 1 && month >= 3 && month <= 8) {
                money = student.getStudentSemester2Money();
            }
            if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
                money = student.getStudentSemester3Money();
            }
            if (year - studentyear == 2 && month >= 3 && month <= 8) {
                money = student.getStudentSemester4Money();
            }
            if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
                money = student.getStudentSemester5Money();
            }
            if (year - studentyear == 3 && month >= 3 && month <= 8) {
                money = student.getStudentSemester6Money();
            }
            if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
                money = student.getStudentSemester7Money();
            }
            if (year - studentyear == 4 && month >= 3 && month <= 8) {
                money = student.getStudentSemester8Money();
            }
            if (money != 0){
                resultMessage = new ResultMessage(105,"学生已缴费");
            }else {
                resultMessage = new ResultMessage(200,"成功");
            }
        }
        return resultMessage;
    }

    @Override
    public DtoCheckStudent getAStudentByStudentNo(String studentNo) {
        Student pojoStudent = studentmapper.getStudentByStudentNo(studentNo);
        DtoCheckStudent student = new DtoCheckStudent();
        student.setStudentNo(pojoStudent.getStudentNo());
        student.setStudentName(pojoStudent.getStudentName());
        student.setSchoolName(pojoStudent.getSchoolName());
        student.setMajorName(pojoStudent.getMajorName());
        student.setClassName(pojoStudent.getClassName());
        return student;
    }

    @Override
    public ResultMessage isPay(String studentNo) {
        Student student = studentmapper.getStudentByStudentNo(studentNo);
        ResultMessage message = null;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int studentyear = Integer.parseInt(studentNo.substring(0, 4));
        double money = 0.0;
        if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
            money = student.getStudentSemester1Money();
        }
        if (year - studentyear == 1 && month >= 3 && month <= 8) {
            money = student.getStudentSemester2Money();
        }
        if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
            money = student.getStudentSemester3Money();
        }
        if (year - studentyear == 2 && month >= 3 && month <= 8) {
            money = student.getStudentSemester4Money();
        }
        if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
            money = student.getStudentSemester5Money();
        }
        if (year - studentyear == 3 && month >= 3 && month <= 8) {
            money = student.getStudentSemester6Money();
        }
        if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
            money = student.getStudentSemester7Money();
        }
        if (year - studentyear == 4 && month >= 3 && month <= 8) {
            money = student.getStudentSemester8Money();
        }
        if (money == 0.0){
            message = new ResultMessage(200,"没有缴费");
        }else if (money > 0){
            message = new ResultMessage(410,"您已缴费，请勿重复缴费");
        }else {
            message = new ResultMessage(420,"系统错误，请联系管理员");
        }
        return message;
    }

    @Override
    public int payTuition(String studentNo, String total_amount) {
        double money = Double.parseDouble(total_amount);
        String xueqi = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int studentyear = Integer.parseInt(studentNo.substring(0, 4));
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
        return studentmapper.payTuition(studentNo,xueqi,money);
    }

    @Override
    public int isCheck(String studentNo) {
        Student student = studentmapper.getStudentByStudentNo(studentNo);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int studentyear = Integer.parseInt(studentNo.substring(0, 4));
        int flag = 0;
        double money = 0.0;
        int status;
        if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
            flag = student.getStudentSemester1();
            money = student.getStudentSemester1Money();
        }
        if (year - studentyear == 1 && month >= 3 && month <= 8) {
            flag = student.getStudentSemester2();
            money = student.getStudentSemester2Money();
        }
        if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
            flag = student.getStudentSemester3();
            money = student.getStudentSemester3Money();
        }
        if (year - studentyear == 2 && month >= 3 && month <= 8) {
            flag = student.getStudentSemester4();
            money = student.getStudentSemester4Money();
        }
        if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
            flag = student.getStudentSemester5();
            money = student.getStudentSemester5Money();
        }
        if (year - studentyear == 3 && month >= 3 && month <= 8) {
            flag = student.getStudentSemester6();
            money = student.getStudentSemester6Money();
        }
        if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
            flag = student.getStudentSemester7();
            money = student.getStudentSemester7Money();
        }
        if (year - studentyear == 4 && month >= 3 && month <= 8) {
            flag = student.getStudentSemester8();
            money = student.getStudentSemester8Money();
        }
        if (flag == 0 && money ==0){
            status = 0;
        }else if (flag == 0 && money > 0){
            status = 1;
        }else {
            status = 2;
        }
        return status;
    }

    @Override
    public ResultMessage check(String studentNo) {
        ResultMessage resultMessage = null;
        String xueqi = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int studentyear = Integer.parseInt(studentNo.substring(0, 4));
        if ((year - studentyear == 0) || (year - studentyear == 1 && month < 3)) {
            xueqi = "studentSemester1";
        }
        if (year - studentyear == 1 && month >= 3 && month <= 8) {
            xueqi = "studentSemester2";
        }
        if ((year - studentyear == 1 && month > 8) || (year - studentyear == 2 && month < 3)) {
            xueqi = "studentSemester3";
        }
        if (year - studentyear == 2 && month >= 3 && month <= 8) {
            xueqi = "studentSemester4";
        }
        if ((year - studentyear == 2 && month > 8) || (year - studentyear == 3 && month < 3)) {
            xueqi = "studentSemester5";
        }
        if (year - studentyear == 3 && month >= 3 && month <= 8) {
            xueqi = "studentSemester6";
        }
        if ((year - studentyear == 3 && month > 8) || (year - studentyear == 4 && month < 3)) {
            xueqi = "studentSemester7";
        }
        if (year - studentyear == 4 && month >= 3 && month <= 8) {
            xueqi = "studentSemester8";
        }
        int i = studentmapper.check(studentNo,xueqi);
        if (i > 0){
            resultMessage = new ResultMessage(200,"报到成功");
        }else {
            resultMessage = new ResultMessage(500,"服务器发生故障，请联系管理员操作");
        }
        return resultMessage;
    }

    @Override
    public List<DtoStudentTu> studentTu(){
        return studentmapper.studentTu();
    }

    @Override
    public String addOneStudent(String studentName, String studentGender, String studentCardNo, int studentSchoolId, int studentMajorId, int studentClassId, String studentDormitoryId) {
        ResultMessage resultMessage = null;
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        String major = String.format("%02d", studentMajorId);
        String HClass = String.format("%02d", studentClassId);
        int num = classMapper.getCountByClassId(studentClassId);
        String count = String.format("%02d", num+1);
        String studentNo = year+major+HClass+count;
        String studentPassword = studentCardNo.substring(studentCardNo.length()-6);
        int a = userMapper.addUser(studentNo, studentPassword, studentName, 1, studentGender);
        int b = studentmapper.addOneStudent(studentNo,studentPassword,1,studentName,studentGender,studentCardNo,studentSchoolId,studentMajorId,studentClassId,studentDormitoryId);
        if (a>0&&b>0){
            return studentNo;
        }else {
            return null;
        }
    }

    @Override
    public DtoAddStudent getAstudentByNewStudentNo(String studentNo) {
        return studentmapper.getAstudentByNewStudentNo(studentNo);
    }


}
