package Demo.demoo.business.abstracts.employer;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateEmployerRequest;
import Demo.demoo.entities.dtos.responses.GetAllEmployerResponse;

import java.util.List;

public interface IEmployer {
    Result add(CreateEmployerRequest createEmployerRequest);

    DataResult<List<GetAllEmployerResponse>> getall();
}
