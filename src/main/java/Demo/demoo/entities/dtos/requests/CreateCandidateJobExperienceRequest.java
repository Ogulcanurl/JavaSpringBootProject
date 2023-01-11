package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateCandidateJobExperienceRequest {
    private int cvId;
    private String workplaceName;
    private String position;
    private String startingWork;
    private String quitWork;
}
