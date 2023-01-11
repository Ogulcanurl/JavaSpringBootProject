package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ILink;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.core.utitilies.results.SuccessResult;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.dataAccess.candidate.LinkDao;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.CandidateLink;
import Demo.demoo.entities.dtos.requests.CreateLinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LinkManager implements ILink {
    @Autowired
    LinkDao linkDao;
    @Autowired
    CvInfoDao cvInfoDao;
    @Override
    public Result add(CreateLinkRequest createLinkRequest) {
        CandidateLink link = new CandidateLink();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(createLinkRequest.getCvId()).get();
        link.setGithubLink(createLinkRequest.getGithubLink());
        link.setLinkedinLink(createLinkRequest.getLinkedinLink());
        candidateCvInfo.addLinks(link);
        cvInfoDao.save(candidateCvInfo);
        return new SuccessResult("Linkler Cvye eklendi");
    }

    @Override
    public DataResult<List<CandidateLink>> getAll() {
        return new SuccessDataResult<>(linkDao.findAll());
    }
}
