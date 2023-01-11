package Demo.demoo.entities.dtos.requests;

import Demo.demoo.entities.Employer;
import Demo.demoo.entities.SystemAdmin;
import lombok.Data;

@Data
public class CreateSystemAdminWithConfirmEmployerRequest {

    private SystemAdmin systemAdminId;
    private Employer employerId;
    private boolean approval;
}
