package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoSchoolDetails {

    private Integer schoolId;
    private String schoolName;
    private Integer majorCount;
    private Integer classCount;
    private Integer studentCount;

}
