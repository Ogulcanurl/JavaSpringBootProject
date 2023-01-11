package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.IJobExperience;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.JobExperienceDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.JobExperience;
import Demo.demoo.entities.dtos.requests.CreateCandidateJobExperienceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateJobExperienceManager implements IJobExperience {

    @Autowired
    JobExperienceDao jobExperienceDao;
    @Autowired
    CvInfoDao cvInfoDao;
    @Override
    public Result add(CreateCandidateJobExperienceRequest createCandidateJobExperienceRequest) {
        JobExperience jobExperience = new JobExperience();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createCandidateJobExperienceRequest.getCvId()).get();
        jobExperience.setPosition(createCandidateJobExperienceRequest.getPosition());
        jobExperience.setQuitWork(createCandidateJobExperienceRequest.getQuitWork());
        jobExperience.setStartingWork(createCandidateJobExperienceRequest.getStartingWork());
        jobExperience.setWorkplaceName(createCandidateJobExperienceRequest.getWorkplaceName());
        if (jobExperience.getPosition() == null && jobExperience.getWorkplaceName() == null
                && jobExperience.getQuitWork() == null && jobExperience.getStartingWork() == null){
            jobExperience.setWorkplaceName("İş deneyimi yok");
            jobExperience.setPosition("İş deneyimi yok");
            jobExperience.setStartingWork("İş deneyimi yok");
            jobExperience.setQuitWork("İş deneyimi yok");
        }
        candidateCvInfo.addJobExperience(jobExperience);
        cvInfoDao.save(candidateCvInfo);
        return new SuccessDataResult<>("Bilgiler Kaydedildi.");
    }

    @Override
    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<>(jobExperienceDao.findAll());
    }
}
