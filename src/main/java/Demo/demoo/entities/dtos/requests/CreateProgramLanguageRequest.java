package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateProgramLanguageRequest {
    private int cvId;
    private String programLanguageName;
    private String programFrameworkName;
}
