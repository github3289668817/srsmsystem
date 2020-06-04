package per.xgt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major {

  private Integer majorId;
  private String majorName;
  private Integer majorSchoolId;
  private Integer majorDel;
  private String schoolName;

}
