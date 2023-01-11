package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ISchoolInfo;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.core.utitilies.results.SuccessResult;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.SchoolInfoDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.SchoolInfo;
import Demo.demoo.entities.dtos.requests.CreateSchoolInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateSchoolInfoManager implements ISchoolInfo {

    @Autowired
    SchoolInfoDao schoolInfoDao;
    @Autowired
    CvInfoDao cvInfoDao;

    @Autowired
    CandidateDao candidateDao;

    @Override
    public Result add(CreateSchoolInfoRequest createSchoolInfoRequest) {
        SchoolInfo schoolInfo = new SchoolInfo();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createSchoolInfoRequest.getCvInfoId()).get();
        schoolInfo.setSchoolName(createSchoolInfoRequest.getSchoolName());
        schoolInfo.setGraduated(createSchoolInfoRequest.getGraduated());
        if(schoolInfo.getGraduated().isEmpty()){
            schoolInfo.setGraduated("devam ediyor");
        }
        schoolInfo.setDepartment(createSchoolInfoRequest.getDepartment());
        schoolInfo.setYearOfEducation(createSchoolInfoRequest.getYerOfEducation());
        candidateCvInfo.addSchool(schoolInfo);
        cvInfoDao.save(candidateCvInfo);
        return new SuccessResult("Deneme");
    }

    @Override
    public DataResult<List<SchoolInfo>> getAll() {
        return new SuccessDataResult<>(schoolInfoDao.findAll());
    }
}
