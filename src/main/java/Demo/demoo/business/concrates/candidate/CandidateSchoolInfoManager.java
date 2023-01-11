package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ISchoolInfo;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
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
    IValidationRules iValidationRules;
    @Override
    public Result add(CreateSchoolInfoRequest createSchoolInfoRequest) {
        SchoolInfo schoolInfo = new SchoolInfo();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createSchoolInfoRequest.getCvInfoId()).get();
        if (iValidationRules.cannotBeEmptyWithSchool(createSchoolInfoRequest.getSchoolName(), createSchoolInfoRequest.getDepartment(), createSchoolInfoRequest.getYerOfEducation())) {
            schoolInfo.setSchoolName(createSchoolInfoRequest.getSchoolName());
            schoolInfo.setGraduated(createSchoolInfoRequest.getGraduated());
            if (schoolInfo.getGraduated() == null) {
                schoolInfo.setGraduated("devam ediyor");
            }
            schoolInfo.setDepartment(createSchoolInfoRequest.getDepartment());
            schoolInfo.setYearOfEducation(createSchoolInfoRequest.getYerOfEducation());
            candidateCvInfo.addSchool(schoolInfo);
            cvInfoDao.save(candidateCvInfo);
            return new SuccessResult("Deneme");
        }if (!iValidationRules.cannotBeEmptyWithSchool(createSchoolInfoRequest.getSchoolName(), createSchoolInfoRequest.getDepartment(), createSchoolInfoRequest.getYerOfEducation())){
            return new ErrorResult("Okul adı, Departman adı ve giriş tarihi boş geçilemez");
        }
        return new ErrorResult("beklenmedik bir hata oluştu");
    }

    @Override
    public DataResult<List<SchoolInfo>> getAll() {
        return new SuccessDataResult<>(schoolInfoDao.findAll());
    }
}
