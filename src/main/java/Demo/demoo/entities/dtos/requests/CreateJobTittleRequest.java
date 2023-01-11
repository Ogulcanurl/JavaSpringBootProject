package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateJobTittleRequest {
    private String jobTittle;
    private String description;
}
