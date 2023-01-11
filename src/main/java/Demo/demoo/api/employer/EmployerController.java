package Demo.demoo.api.employer;

import Demo.demoo.business.abstracts.employer.IEmployer;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateEmployerRequest;
import Demo.demoo.entities.dtos.responses.GetAllEmployerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employer/")
public class EmployerController {
    @Autowired
    IEmployer iEmployer;
    @PostMapping("add")
    public Result add(@RequestBody CreateEmployerRequest createEmployerRequest){
        return iEmployer.add(createEmployerRequest);
    }
    @GetMapping("getall")
    public DataResult<List<GetAllEmployerResponse>> getall(){
        return iEmployer.getall();
    }
}
