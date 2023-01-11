package Demo.demoo.entities.dtos.requests;

import lombok.Data;


@Data
public class CreateEmployerRequest {

    private String companyName;
    private String phone;
    private String website;
    private String email;
    private String password;
    private String repeatPassword;
}
