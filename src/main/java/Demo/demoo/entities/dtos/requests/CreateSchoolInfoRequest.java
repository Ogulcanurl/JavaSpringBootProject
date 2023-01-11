package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateSchoolInfoRequest {
    private int cvInfoId;
    private String schoolName;
    private String department;
    private String yerOfEducation;
    private String graduated;
}
