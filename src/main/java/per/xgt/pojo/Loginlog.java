package per.xgt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loginlog {

  private Integer loginLogId;
  private String loginLogIp;
  private String loginLogNo;
  private String loginLogCreatetime;

}
