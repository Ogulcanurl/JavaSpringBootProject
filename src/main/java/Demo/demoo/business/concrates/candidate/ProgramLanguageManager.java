package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.IProgramLanguage;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.ProgramLanguageDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.ProgramLanguage;
import Demo.demoo.entities.dtos.requests.CreateProgramLanguageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProgramLanguageManager implements IProgramLanguage {
    @Autowired
    ProgramLanguageDao programLanguageDao;
    @Autowired
    CvInfoDao cvInfoDao;

    @Override
    public Result add(CreateProgramLanguageRequest createProgramLanguageRequest) {

        try {
            ProgramLanguage programLanguage = new ProgramLanguage();
            CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createProgramLanguageRequest.getCvId()).get();
            if (createProgramLanguageRequest.getProgramLanguageName() == null){
                programLanguage.setProgramFrameworkName("");
                programLanguage.setProgramLanguageName("");
            } else if (createProgramLanguageRequest.getProgramFrameworkName() == null) {
                programLanguage.setProgramFrameworkName("");
            }
            programLanguage.setProgramLanguageName(createProgramLanguageRequest.getProgramLanguageName());
            programLanguage.setProgramFrameworkName(createProgramLanguageRequest.getProgramFrameworkName());
            candidateCvInfo.addProgramLanguage(programLanguage);
            cvInfoDao.save(candidateCvInfo);
            return new SuccessResult();
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<ProgramLanguage>> getAll() {
        return new SuccessDataResult<>(programLanguageDao.findAll());
    }
}
