package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ILanguageLevel;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.LanguageLevelDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.LanguageLevel;
import Demo.demoo.entities.dtos.requests.CreateLanguageLevelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LanguageLevelManager implements ILanguageLevel {
    @Autowired
    LanguageLevelDao languageLevelDao;
    @Autowired
    CvInfoDao cvInfoDao;
    @Override
    public Result add(CreateLanguageLevelRequest createLanguageLevelRequest) {
        try {
            LanguageLevel level = new LanguageLevel();
            CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createLanguageLevelRequest.getCvId()).get();
            if (level.getLanguageName() != null){
                if (level.getLevel() == null){
                    level.setLevel("");
                }
                level.setLevel(createLanguageLevelRequest.getLevel());
                level.setLanguageName(createLanguageLevelRequest.getLanguageName());
                candidateCvInfo.addLanguageLevels(level);
                cvInfoDao.save(candidateCvInfo);
                return new SuccessResult("");
            }else if (level.getLanguageName() == null){
                level.setLanguageName("");
                level.setLevel("");
            }
            return new ErrorResult("a");
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<LanguageLevel>> getAll() {
        return new SuccessDataResult<>(languageLevelDao.findAll());
    }
}
