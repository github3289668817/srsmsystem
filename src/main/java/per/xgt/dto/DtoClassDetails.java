package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoClassDetails {

    private int classId;
    private String className;
    private String schoolName;
    private String majorName;
    private int studentCount;

}
