package Demo.demoo.api.employer;


import Demo.demoo.business.abstracts.employer.IJobTittle;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateJobTittleRequest;
import Demo.demoo.entities.dtos.responses.GetAllJobTittleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jobtittle/")
public class JobTittleController {
    @Autowired
    IJobTittle jobTittle;
    @PostMapping("add")
    public Result add(CreateJobTittleRequest createJobTittleRequest){
        return jobTittle.add(createJobTittleRequest);
    }
    @GetMapping("getall")
    public DataResult<List<GetAllJobTittleResponse>> getaAll(){
        return jobTittle.getall();
    }
}
