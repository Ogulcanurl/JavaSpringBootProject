package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.IJobExperience;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.JobExperience;
import Demo.demoo.entities.dtos.requests.CreateCandidateJobExperienceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/experience/")
public class CandidateJobExperienceController {
    @Autowired
    IJobExperience jobExperience;
    @PostMapping("add")
    public Result add(CreateCandidateJobExperienceRequest createCandidateJobExperienceRequest){
        return jobExperience.add(createCandidateJobExperienceRequest);
    }
    @GetMapping("getall")
    public DataResult<List<JobExperience>> getAll(){
        return jobExperience.getAll();
    }
}
