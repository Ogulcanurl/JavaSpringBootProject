package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateSystemAdminRequest {
    private String name;
    private String lastName;
    private String nationalId;
    private String email;
    private String password;
}
