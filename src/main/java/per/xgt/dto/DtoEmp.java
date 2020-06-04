package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoEmp {

    private String empNo;
    private String empName;
    private String empGender;
    private String empPhone;
    private String departmentName;
    private String roleName;

}
