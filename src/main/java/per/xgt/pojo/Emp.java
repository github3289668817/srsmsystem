package per.xgt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

  private String empNo;
  private String empPassword;
  private String empName;
  private Integer empRoleId;
  private Integer empDepartmentId;
  private String empGender;
  private String empCardNo;
  private String empBirthday;
  private String empPhone;
  private String empPhoto;
  private Integer empDel;
  private String departmentName;
  private String roleName;

}
