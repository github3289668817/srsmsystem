package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudent {

    private String studentNo;
    private String studentName;
    private String studentGender;
    private String className;
    private String studentSemester;

}
