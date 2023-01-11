package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.Candidate;
import Demo.demoo.entities.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCandidateResponse {
    private int id;
    private String name;
    private String lastName;
    private String nationalId;
    private String yearOfBirth;
    private User user;

    public GetAllCandidateResponse(Candidate candidate) {
        this.user = candidate.getUser();
        this.name = candidate.getName();
        this.lastName = candidate.getLastName();
        this.nationalId = candidate.getNationalId();
        this.yearOfBirth = candidate.getYearOfBirth();
        this.id = candidate.getId();
    }
}
