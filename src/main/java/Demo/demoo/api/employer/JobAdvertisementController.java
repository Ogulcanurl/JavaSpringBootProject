package Demo.demoo.api.employer;

import Demo.demoo.business.abstracts.employer.IJobAdvertisement;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.employer.JobAdvertisement;
import Demo.demoo.entities.dtos.requests.CreateJobAdvertisement;
import Demo.demoo.entities.dtos.responses.GetTableJobAdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobadvertisement/")
public class JobAdvertisementController {
    @Autowired
    IJobAdvertisement jobAdvertisement;
    @PostMapping("add")
    public Result add(CreateJobAdvertisement createJobAdvertisement){
        return jobAdvertisement.add(createJobAdvertisement);
    }
    @GetMapping("getAll")
    public DataResult<List<JobAdvertisement>> getall(){
        return jobAdvertisement.getAll();
    }
    @GetMapping("getByActivateTrue")
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrue(){
        return jobAdvertisement.getByActivated();
    }
    @GetMapping("getByActivateTrueOrderByApplicationDeadlineAsc")
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueOrderByApplicationDeadlineAsc(){
        return jobAdvertisement.getByActivateTrueOrderByApplicationDeadlineAsc();
    }
    @GetMapping("getByActivateTrueOrderByReleaseDateAsc")
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueOrderByReleaseDateAsc(){
        return jobAdvertisement.getByActivateTrueOrderByReleaseDateAsc();
    }
    @GetMapping("getByActivateTrueAndEmployer_CompanyName")
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueAndEmployer_CompanyName(String companyName){
        return jobAdvertisement.getByActivateTrueAndEmployer_CompanyName(companyName);
    }
    @GetMapping("activateJobAdvertisement")
    public DataResult<List<GetTableJobAdvertisementResponse>> activateJobAdvertisement(int employerId, int jobAdvertisementId,boolean activate){
        return jobAdvertisement.activateJobAdvertisement(employerId,jobAdvertisementId,activate);
    }
  /*
    @PostMapping("deactiveate/{id}")
    public DataResult<Integer> deActivateJobAdvertisement(@PathVariable(value = "id") int id) {
        return jobAdvertisement.deActivateJobAdvertisement(id);
    } ü
  */
}
