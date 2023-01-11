package Demo.demoo.business.abstracts.employer;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateCityRequest;
import Demo.demoo.entities.dtos.responses.GetAllCityResponse;

import java.util.List;

public interface ICity {
    Result add(CreateCityRequest createCityRequest);
    DataResult<List<GetAllCityResponse>> getAll();
}
