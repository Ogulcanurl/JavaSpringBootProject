package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ILink;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.LinkDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.CandidateLink;
import Demo.demoo.entities.dtos.requests.CreateLinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LinkManager implements ILink {
    @Autowired
    LinkDao linkDao;
    @Autowired
    CvInfoDao cvInfoDao;
    @Override
    public Result add(CreateLinkRequest createLinkRequest) {
        try {
            CandidateLink link = new CandidateLink();
            CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createLinkRequest.getCvId()).get();
            if(createLinkRequest.getGithubLink() == null){
                link.setGithubLink("");
            } else if (createLinkRequest.getLinkedinLink() == null) {
                link.setLinkedinLink("");
            }
            link.setGithubLink(createLinkRequest.getGithubLink());
            link.setLinkedinLink(createLinkRequest.getLinkedinLink());
            candidateCvInfo.addLinks(link);
            cvInfoDao.save(candidateCvInfo);
            return new SuccessResult();
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<CandidateLink>> getAll() {
        return new SuccessDataResult<>(linkDao.findAll());
    }
}
