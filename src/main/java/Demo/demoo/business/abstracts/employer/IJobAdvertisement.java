package Demo.demoo.business.abstracts.employer;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.employer.JobAdvertisement;
import Demo.demoo.entities.dtos.requests.CreateJobAdvertisement;
import Demo.demoo.entities.dtos.responses.GetTableJobAdvertisementResponse;

import java.util.List;

public interface IJobAdvertisement{
    Result add(CreateJobAdvertisement createJobAdvertisement);
    DataResult<List<JobAdvertisement>> getAll();

    //DataResult<Integer> deActivateJobAdvertisement(int id);
    DataResult<List<GetTableJobAdvertisementResponse>> activateJobAdvertisement(int employerId, int jobAdvertisementId,boolean activate);
    DataResult<List<GetTableJobAdvertisementResponse>> getByActivated();
    DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueOrderByApplicationDeadlineAsc();

    DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueAndEmployer_CompanyName(String companyName);

    DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueOrderByReleaseDateAsc();
}
