package Demo.demoo.entities.dtos.requests;

import lombok.Data;



@Data
public class CreateCvInfoRequest {
    private int candidateId;
    private String coverLetter;
}
