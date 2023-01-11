package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.Employer;
import Demo.demoo.entities.User;
import lombok.Data;

@Data
public class GetAllEmployerResponse {

    private int id;
    private String companyName;
    private String phone;
    private String website;
    private User user;


    public GetAllEmployerResponse(Employer employer) {
        this.id = employer.getId();
        this.companyName = employer.getCompanyName();
        this.website = employer.getWebsite();
        this.phone = employer.getPhoneNumber();
        this.user = employer.getUser();
    }
}
