package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidateCvInfo;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.LinkDao;
import Demo.demoo.entities.Candidate;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.dtos.requests.CreateCvInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CandidateCvInfoManager implements ICandidateCvInfo {

    @Autowired
    CvInfoDao cvInfoDao;
    @Autowired
    CandidateDao candidateDao;
    @Override
    public Result add(CreateCvInfoRequest cvInfo) {
        CandidateCvInfo candidateCvInfo = new CandidateCvInfo();
        Candidate candidate = candidateDao.findById(cvInfo.getCandidateId()).get();
        candidateCvInfo.setCandidateId(candidate);
        candidateCvInfo.setCoverLetter(cvInfo.getCoverLetter());
        return new SuccessDataResult<>(cvInfoDao.save(candidateCvInfo));
    }

    @Override
    public DataResult<List<CandidateCvInfo>> getAll() {
        return new SuccessDataResult<>(cvInfoDao.findAll());
    }

    @Override
    public DataResult<CandidateCvInfo> getById(int id) {
        return new SuccessDataResult<>(cvInfoDao.findById(id).get());
    }

    @Override
    public DataResult<List<CandidateCvInfo>> getByJobExperienceAsc() {
//        return new SuccessDataResult<>(cvInfoDao.getByJobExperience_StartingWorkAsc());
        return null;
    }
}
