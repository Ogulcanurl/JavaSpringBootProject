package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ILink;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.CandidateLink;
import Demo.demoo.entities.dtos.requests.CreateLinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/link/")
public class CandidateLinkController {
    @Autowired
    ILink iLink;
    @PostMapping("add")
    public Result add(CreateLinkRequest createLinkRequest){
        return iLink.add(createLinkRequest);
    }
    @GetMapping("getall")
    public DataResult<List<CandidateLink>> getAll(){
        return iLink.getAll();
    }

}
