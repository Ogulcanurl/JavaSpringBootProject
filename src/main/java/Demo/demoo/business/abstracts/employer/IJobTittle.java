package Demo.demoo.business.abstracts.employer;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateJobTittleRequest;
import Demo.demoo.entities.dtos.responses.GetAllJobTittleResponse;

import java.util.List;

public interface IJobTittle {

    Result add(CreateJobTittleRequest createJobTittleRequest);
    DataResult<List<GetAllJobTittleResponse>> getall();
}
