package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.CandidateImage;
import Demo.demoo.entities.candidate.SchoolInfo;

import java.util.Set;

public class GetAllCvInfoResponse {
    public int candidateId;
    public String coverLetter;
    public CandidateImage candidateImage;
    public Set<SchoolInfo> schoolInfoSet;

    public GetAllCvInfoResponse(CandidateCvInfo candidateCvInfo){
        this.candidateId = candidateCvInfo.getCandidateId().getId();
        this.schoolInfoSet = candidateCvInfo.getSchoolInfo();
        this.coverLetter = candidateCvInfo.getCoverLetter();
        this.candidateImage = candidateCvInfo.getCandidateImage();
    }
}
