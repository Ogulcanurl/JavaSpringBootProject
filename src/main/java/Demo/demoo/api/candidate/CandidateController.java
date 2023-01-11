package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidate;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;

import Demo.demoo.entities.dtos.requests.CreateCandidateRequest;
import Demo.demoo.entities.dtos.responses.GetAllCandidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/candidate/")
public class CandidateController {
    @Autowired
    ICandidate iCandidate;
    @GetMapping("getall")
    public DataResult<List<GetAllCandidateResponse>> getall(){
        return iCandidate.getAll();
    }
    @PostMapping("add")
    public Result add(@RequestBody CreateCandidateRequest createCandidateRequest){
        return iCandidate.add(createCandidateRequest);
    }
}