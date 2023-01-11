package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.SchoolInfo;
import lombok.Data;

import java.util.Set;

@Data
public class GetAllSchoolInfoResponse {
    private int id;
    private String schoolName;
    private String department;
    private String yearOfEducation;
    private String graduated;
    private Set<CandidateCvInfo> candidateCvInfoId;
    public GetAllSchoolInfoResponse(SchoolInfo schoolInfo){
        this.id = schoolInfo.getSchoolInfoId();
        this.schoolName = schoolInfo.getSchoolName();
        this.department = schoolInfo.getDepartment();
        this.yearOfEducation = schoolInfo.getYearOfEducation();
        this.graduated = schoolInfo.getGraduated();
//        this.candidateCvInfoId = schoolInfo.getCandidateCvInfo();
    }
}
