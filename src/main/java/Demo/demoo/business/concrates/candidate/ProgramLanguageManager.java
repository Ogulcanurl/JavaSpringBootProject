package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.IProgramLanguage;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.core.utitilies.results.SuccessResult;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.ProgramLanguageDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.ProgramLanguage;
import Demo.demoo.entities.dtos.requests.CreateProgramLanguageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProgramLanguageManager implements IProgramLanguage {
    @Autowired
    ProgramLanguageDao programLanguageDao;
    @Autowired
    CvInfoDao cvInfoDao;

    @Override
    public Result add(CreateProgramLanguageRequest createProgramLanguageRequest) {
        ProgramLanguage programLanguage = new ProgramLanguage();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createProgramLanguageRequest.getCvId()).get();
        programLanguage.setProgramLanguageName(createProgramLanguageRequest.getProgramLanguageName());
        programLanguage.setProgramFrameworkName(createProgramLanguageRequest.getProgramFrameworkName());
        candidateCvInfo.addProgramLanguage(programLanguage);
        cvInfoDao.save(candidateCvInfo);
        return new SuccessResult("Programlama Dili Cv'ye eklendi");
    }

    @Override
    public DataResult<List<ProgramLanguage>> getAll() {
        return new SuccessDataResult<>(programLanguageDao.findAll());
    }
}
