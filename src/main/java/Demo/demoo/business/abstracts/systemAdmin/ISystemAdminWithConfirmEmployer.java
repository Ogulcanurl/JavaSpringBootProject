package Demo.demoo.business.abstracts.systemAdmin;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.entities.systemAdmin.SystemAdminWithConfirmEmployer;
import Demo.demoo.entities.dtos.responses.GetAllSystemAdminWithConfirmEmployerResponse;

import java.util.List;

public interface ISystemAdminWithConfirmEmployer {
    DataResult<List<SystemAdminWithConfirmEmployer>> confirm(int confirmId,int employerId, int systemAdminId, boolean approval);
    DataResult<List<GetAllSystemAdminWithConfirmEmployerResponse>> getAll();
}
