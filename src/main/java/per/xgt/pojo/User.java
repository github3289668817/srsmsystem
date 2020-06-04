package per.xgt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private String userNo;
  private String userPassword;
  private String userName;
  private Integer userRoleId;
  private String userGender;
  private String userPhone;
  private String userPhoto;
  private Integer userDel;
  private String userBirthday;


}
