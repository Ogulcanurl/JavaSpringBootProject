package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ISchoolInfo;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.SchoolInfoDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.SchoolInfo;
import Demo.demoo.entities.dtos.requests.CreateSchoolInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        try {
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
                return new SuccessResult();
            }if (!iValidationRules.cannotBeEmptyWithSchool(createSchoolInfoRequest.getSchoolName(), createSchoolInfoRequest.getDepartment(), createSchoolInfoRequest.getYerOfEducation())){
                return new ErrorResult("ERR_CANDIDATE_SCHOOL_INFO_01");
            }
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
        return new ErrorResult("ERR_CANDIDATE_SCHOOL_INFO_00");
    }

    @Override
    public DataResult<List<SchoolInfo>> getAll() {
        return new SuccessDataResult<>(schoolInfoDao.findAll());
    }
}
