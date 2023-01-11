package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateLinkRequest {
    private int cvId;
    private String githubLink;
    private String linkedinLink;
}
