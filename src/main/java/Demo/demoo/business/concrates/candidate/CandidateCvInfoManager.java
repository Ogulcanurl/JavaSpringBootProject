package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidateCvInfo;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.ErrorResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.entities.Candidate;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.dtos.requests.CreateCvInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CandidateCvInfoManager implements ICandidateCvInfo {

    @Autowired
    CvInfoDao cvInfoDao;
    @Autowired
    CandidateDao candidateDao;

    @Override
    public Result add(CreateCvInfoRequest cvInfo) {
       try {
           CandidateCvInfo candidateCvInfo = cvInfoDao.findByCandidateId_Id(cvInfo.getCandidateId());
           Candidate candidate = candidateDao.findById(cvInfo.getCandidateId()).get();
           if (!candidateCvInfo.getCoverLetter().isEmpty() && candidateCvInfo.getCandidateId().getId() != candidate.getId()){
               candidateCvInfo.setCandidateId(candidate);
               candidateCvInfo.setCoverLetter(cvInfo.getCoverLetter());
               return new SuccessDataResult<>(cvInfoDao.save(candidateCvInfo));
           }else if (candidateCvInfo.getCandidateId().getId() == candidate.getId()){
               return new ErrorResult("ERR_CANDIDATE_CV_01");
           }else if (candidateCvInfo.getCoverLetter() == null){
               return new ErrorResult("ERR_CANDIDATE_CV_02");
           }
       }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
           return new ErrorResult("ERR_CANDIDATE_CV_03");
       }
       return new ErrorResult("ERR_CANDIDATE_CV_00");
    }

    @Override
    public DataResult<List<CandidateCvInfo>> getAll() {
        return new SuccessDataResult<>(cvInfoDao.findAll());
    }

    @Override
    public DataResult<CandidateCvInfo> getById(int id) {
        return new SuccessDataResult<>(cvInfoDao.findById(id).get());
    }
}
