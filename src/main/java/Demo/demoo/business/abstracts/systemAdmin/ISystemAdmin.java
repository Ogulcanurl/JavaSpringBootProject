package Demo.demoo.business.abstracts.systemAdmin;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateSystemAdminRequest;
import Demo.demoo.entities.dtos.responses.GetAllSystemAdminResponse;

import java.util.List;

public interface ISystemAdmin {
    Result add(CreateSystemAdminRequest createSystemAdminRequest);
    DataResult<List<GetAllSystemAdminResponse>> getall();
}
