package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTuition {

    private String studentNo;
    private String studentName;
    private String schoolName;
    private String majorName;
    private String className;
    private Double money;
    private Double moneyPay;

}
