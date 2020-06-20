package per.xgt.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import per.xgt.dto.*;
import per.xgt.pojo.Student;
import per.xgt.pojo.User;
import per.xgt.service.studentService;
import per.xgt.service.userService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
public class studentController {

    @Resource
    private studentService studentService;

    @Resource
    private userService userService;

    @RequestMapping("/findAllStudentByFilter/{schoolId}/{majorId}/{classId}/{register}")
    @ResponseBody
    public Result<DtoStudent> findAllStudentByFilter(
            @PathVariable("schoolId") int schoolId,
            @PathVariable("majorId") int majorId,
            @PathVariable("classId") int classId,
            @PathVariable("register") int register,
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize){
        Result result = studentService.findAllStudentByFilter(schoolId, majorId, classId, register, pageIndex, pageSize);
        return result;
    }

    @RequestMapping("/checkin")
    public ModelAndView checkin(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userNo = user.getUserNo();
        DtoCheckStudent student = studentService.getAStudentByStudentNo(userNo);
        ModelAndView modelAndView = new ModelAndView("checkin");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @RequestMapping("/doPay/{studentNo}")
    public void doPay (HttpServletRequest httpRequest,
                            HttpServletResponse httpResponse,
                            @PathVariable("studentNo") String studentNo
    )   throws ServletException, IOException {
        Date date = new Date();
        long time = date.getTime();
        String out_trade_no = studentNo+time;
        String total_amount = "1.0";
        AlipayClient alipayClient =  new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016102300742690",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQD21Q1nL5mSEjHmT2NxkCJgPiVY1VcmpFhkNOZo4hRkTGk/nNXKyO+Bki/xkueEQBKyJE5edXq0CXklVzZcDM8ZyoiWqVrTQcyVe9J+IG008keNX7Mp/zQRSoxQ/gzoL4XBD1s1+lXo5rEfQjSwfpoa1OKQIBPQbqjZZJFcfHEQAbP5fhankxP6oBMYJEtjmdVI+ODeRZ9ygTc9I8FbjBKX/KguU5IO1sRYMtFNarvU2FJWWI95PFYpB393pgC7uqee3+zA466SrCS+mOEbM2rEY+NcIUgwCSIGQJzEMqUrwmFSzW8uQzQlwrfNpWLw3IkS2TvUMEVMvMvFishnMonXAgMBAAECggEAD5TJYM8faZoebkRqqZuIn/ysTNxCwRy0u7afKNXEpcQxDKPw1vkyLUK2JhC47p9Azt/91naiBUVn0VSdCNiCJkWjs6cDJbzMlk8XrYCMhEAbBjCZVbhXaBQFoYM3+1wsnFBE2LZxv974JVn/i3iib7kqiCCOP6UMoZR4nJb6aCEjp1PwJpd1dFzoWeyfzTJ/mIR7U2m1FOiCclZ81VPD3WL0m0YOF2+X1EJPbyPWYe/sXpf6Ls9zLnFN44FM9ygT1FYem6qrG5CbDAX9DoYZVvjkdOu5E1rXYoDpUHU+iY+zyvp9fby+6VnN3W3E5rm2kMlevWUDMF2vsakxwMkPKQKBgQD+C0RSvar272bsAqkKn3qCyzYAh7pC4hDCBSHKkxDwGk11a/JweZ20UE1R3qrG0DZ5v96SB0Pn8GUU7B9fU6hMTKxfuj0RqlhKzR/oDRfW7CSqCrUFLgkOhuq8nwVt4blNTq62lx9oRdPCgcY3pV0RFKM/terUtqM5PjZpD6u/5QKBgQD4u5IZ5Rqqwuyfwu8sG+mPkQBVXFvuMWp+0djNhS4FBsLQmj1nSrz5w1L7yOSetWf3/bvYRKJ6mp+Ml1VYLGqJlQkarxKbx3yXwQSSDdm+uTr9TjGpOs/TTk7FRCTsnIHouCzM/zVUkCAEIh//4tHgK0OQ5MystfnixCpEnFlvCwKBgQDXKwvPWL35MIPn2cj7KmBFb4BpqovkIj03V8lrHmz2MBDzJ17MTPV5x5n1gbb7dVbuf8gvvt+RD1dOBT/9KYoeNcK5wa//ylvF3z7q+Ev8yT20mCUCIh+hojwnTjj2/HxKMh5goFcFpj7ZO7l7YCAXW3d5esdPKvHMhhjnm1JI0QKBgFBboQ72oo/qm7Hds5GHjwSP7xCk3xpsIqDIQQfc8Ao1rfjg69eUIMt8XDH2LE5OA0HuWqK7QdbkX4l3Vt4+s0ixuphBWXy0jT8l38kj6mYgx7sQaSDoXD5DPVWj913uDlZWMjskdGFWktFjCP2NlQqcT57fHHTr8cheYd16ubYTAoGAUsiUpAvkfs4MRbYOrukIk+Q+H43IBH+HbBNkVkkLrROqLAF87fDW7sIubKwU5qRtOHXo9hf1unG4+KHHi4c+Kus3pvBUG9TCXan9/+uUlF/Du1q392K8nZP7OwKEn9vo9CzDnugw84iPPj2MIXknCURATCfZoOsF7zE+B4vKsXY=",
                "JSON",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAix5kPubCZFVjXYHG2uoh+WMIDS13PelCS8V3yZCnpw3cBW2lu30geZ4wdSHetzbV9e9aQcGMz1NKNvxoeLtJtxOrR+mvhvrBQzxmMWiWEa5aIG6sYn2bUamJ8q9MGHQVLtrJ5xN3SxT2Os56IHqA0h2vxRtga/NyHeukHTTrYHc6aczIG8kynjwYhzrJekEY9UVygQ2fGh2k2ea5im+0vxBct9JFcWiTob7hdDgeY4YvGbM20DXkaawfU70tnFl9A8XbRAX42pxPuFoYfLjaj0EzooYn7Fseji9nUVXxS8yqszY23B7K0ljFaLmEjnx+0OjtuweUZsdcUpA1EC9qKQIDAQAB",
                "RSA2");
        //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "http://127.0.0.1:80/paymentsuccessful/"+studentNo );
        alipayRequest.setNotifyUrl( "http://g45xpt.natappfree.cc/paySuccess/"+studentNo+"/"+ out_trade_no+"/"+total_amount); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+out_trade_no+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":1.0,"  +
                "    \"subject\":\"学费缴纳\","  +
                "    \"body\":\"学费缴纳\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset=utf-8");
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping("/paymentsuccessful/{studentNo}")
    public String paymentsuccessful(@PathVariable("studentNo") String studentNo,HttpSession session){

        User user = (User)session.getAttribute("user");
        if (user == null){
            User student = userService.findAUserByUserNo(studentNo);
            session.setAttribute("user", student);
        }
        return "main";
    }

    @RequestMapping("/isPay")
    @ResponseBody
    public ResultMessage isPay(String studentNo){
        ResultMessage message = studentService.isPay(studentNo);
        return message;
    }

    @RequestMapping("/paySuccess/{studentNo}/{dingdanhao}/{total_amount}")
    @ResponseBody
    public void paySuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("studentNo") String studentNo,
            @PathVariable("dingdanhao") String dingdanhao,
            @PathVariable("total_amount") String total_amount
    ) throws AlipayApiException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();  //将异步通知中收到的所有参数都存放到 map 中
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String[] values = entry.getValue();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < values.length-1; i++) {
                sb.append(values[i]+",");
            }
            sb.append(values[values.length-1]);
            paramsMap.put(entry.getKey(), sb.toString());
        }

        boolean  signVerified = AlipaySignature.rsaCheckV1(
                paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAix5kPubCZFVjXYHG2uoh+WMIDS13PelCS8V3yZCnpw3cBW2lu30geZ4wdSHetzbV9e9aQcGMz1NKNvxoeLtJtxOrR+mvhvrBQzxmMWiWEa5aIG6sYn2bUamJ8q9MGHQVLtrJ5xN3SxT2Os56IHqA0h2vxRtga/NyHeukHTTrYHc6aczIG8kynjwYhzrJekEY9UVygQ2fGh2k2ea5im+0vxBct9JFcWiTob7hdDgeY4YvGbM20DXkaawfU70tnFl9A8XbRAX42pxPuFoYfLjaj0EzooYn7Fseji9nUVXxS8yqszY23B7K0ljFaLmEjnx+0OjtuweUZsdcUpA1EC9qKQIDAQAB",
                "utf-8",
                "RSA2"
        );  //调用SDK验证签名
        if (signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if (paramsMap.get("out_trade_no").equals(dingdanhao)){
                int i = studentService.payTuition(studentNo,total_amount);
                if (i > 0){
                    response.getWriter().write("Success");
                }else {
                    response.getWriter().write("Service Currently Unavailable");
                }
            }
        } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            response.getWriter().write("Service Currently Unavailable");
        }
    }

    @RequestMapping("/isCheck/{studentNo}")
    @ResponseBody
    public ResultMessage isCheck(@PathVariable("studentNo") String studentNo){
        int flag = studentService.isCheck(studentNo);
        ResultMessage resultMessage = null;
        if (flag == 0){
            resultMessage = new ResultMessage(407,"请先缴纳学费");
        }else if (flag == 2){
            resultMessage = new ResultMessage(408,"请勿重复报到");
        }else {
            resultMessage = new ResultMessage(200,"请报到");
        }
        return resultMessage;
    }

    @RequestMapping("/check")
    @ResponseBody
    public ResultMessage check(HttpSession session){
        User user = (User) session.getAttribute("user");
        String studentNo = user.getUserNo();
        return studentService.check(studentNo);
    }

    @RequestMapping("/showStudentTu")
    @ResponseBody
    public List<DtoStudentTu> showStudentTu(){
        return studentService.studentTu();
    }

    @RequestMapping("/addOneStudent")
    @ResponseBody
    public String addOneStudent(
            String studentName,
            String studentGender,
            String studentCardNo,
            int studentSchoolId,
            int studentMajorId,
            int studentClassId,
            String studentDormitoryId
    ){
        return studentService.addOneStudent(
                studentName,
                studentGender,
                studentCardNo,
                studentSchoolId,
                studentMajorId,
                studentClassId,
                studentDormitoryId
        );
    }

    @RequestMapping("/showaddstudent/{studentNo}")
    public ModelAndView getAstudentByNewStudentNo(
            @PathVariable("studentNo") String studentNo
    ){
        ModelAndView modelAndView = new ModelAndView("showaddstudent");
        DtoAddStudent student = studentService.getAstudentByNewStudentNo(studentNo);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @RequestMapping("/findAllStudentsByClassId/{classId}")
    @ResponseBody
    public Result<DtoStudentByClass> findAllStudentsByClassId(
            @RequestParam("page") int pageIndex,
            @RequestParam("limit") int pageSize,
            @PathVariable("classId") int classId
    ){
        return studentService.findAllStudentsByClassId(classId,pageIndex,pageSize);
    }

    @RequestMapping("/importExcel")
    public String importExcel(MultipartFile mFile){
        List<Student> students = new ArrayList<>();
        InputStream inputStream = null;
        Student s  = null;
        try {
            inputStream = mFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                s = new Student();
                HSSFRow row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                String ss = "";
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    HSSFCell cell = row.getCell(j);
                    if (cell==null){
                        ss = "";
                    }else if (cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
                        ss = String.valueOf(cell.getBooleanCellValue());
                    }else if (cell.getCellType()== HSSFCell.CELL_TYPE_NUMERIC){
                        ss = cell.getNumericCellValue() + "";
                    }else {
                        ss = cell.getStringCellValue();
                    }
                    if (j == 0){
                        s.setStudentName(ss);
                    }else if (j == 1){
                        s.setStudentGender(ss);
                    }else if (j == 2){
                        s.setStudentCardNo(ss);
                    }else if (j == 3){
                        s.setSchoolName(ss);
                    }else if (j == 4){
                        s.setMajorName(ss);
                    }
                }
                students.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        studentService.addStudents(students);
        return "studentstatistics";
    }
}
