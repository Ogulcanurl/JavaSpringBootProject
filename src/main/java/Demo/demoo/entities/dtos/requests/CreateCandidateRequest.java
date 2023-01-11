package Demo.demoo.entities.dtos.requests;


import lombok.Data;

@Data
public class CreateCandidateRequest {
    private String name;
    private String lastName;
    private String nationalId;
    private String yearOfBirth;
    private String email;
    private String password;
    private String repeatPassword;
}
