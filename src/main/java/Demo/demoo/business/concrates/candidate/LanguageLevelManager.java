package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ILanguageLevel;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.core.utitilies.results.SuccessResult;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.LanguageLevelDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.LanguageLevel;
import Demo.demoo.entities.dtos.requests.CreateLanguageLevelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageLevelManager implements ILanguageLevel {
    @Autowired
    LanguageLevelDao languageLevelDao;
    @Autowired
    CvInfoDao cvInfoDao;
    @Override
    public Result add(CreateLanguageLevelRequest createLanguageLevelRequest) {
        LanguageLevel level = new LanguageLevel();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createLanguageLevelRequest.getCvId()).get();
        level.setLevel(createLanguageLevelRequest.getLevel());
        level.setLanguageName(createLanguageLevelRequest.getLanguageName());
        candidateCvInfo.addLanguageLevels(level);
        cvInfoDao.save(candidateCvInfo);
        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<List<LanguageLevel>> getAll() {
        return new SuccessDataResult<>(languageLevelDao.findAll());
    }
}
