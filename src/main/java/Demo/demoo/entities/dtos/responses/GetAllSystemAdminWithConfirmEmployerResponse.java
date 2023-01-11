package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.Employer;
import Demo.demoo.entities.SystemAdmin;
import Demo.demoo.entities.systemAdmin.SystemAdminWithConfirmEmployer;
import lombok.Data;

@Data
public class GetAllSystemAdminWithConfirmEmployerResponse {
    private SystemAdmin systemAdminId;
    private Employer employerId;
    private boolean approval;

    public GetAllSystemAdminWithConfirmEmployerResponse(SystemAdminWithConfirmEmployer systemAdminWithConfirmEmployer){
        this.systemAdminId = systemAdminWithConfirmEmployer.getSystemAdmin();
        this.employerId = systemAdminWithConfirmEmployer.getEmployer();
        this.approval = systemAdminWithConfirmEmployer.isApproval();
    }
}
