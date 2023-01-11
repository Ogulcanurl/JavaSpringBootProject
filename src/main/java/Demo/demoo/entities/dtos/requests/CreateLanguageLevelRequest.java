package Demo.demoo.entities.dtos.requests;

import lombok.Data;

import java.util.logging.Level;

@Data
public class CreateLanguageLevelRequest {
    private int cvId;
    private String languageName;
    private String level;
}
