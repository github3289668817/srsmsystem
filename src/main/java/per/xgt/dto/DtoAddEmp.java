package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAddEmp {

    private String empNo;
    private String empName;
    private String empGender;
    private String empCardNo;
    private String roleName;
    private String departmentName;

}
