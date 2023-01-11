package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.employer.JobTittle;
import lombok.Data;

@Data
public class GetAllJobTittleResponse {
    private String jobTittle;

    public GetAllJobTittleResponse(JobTittle jobTittle){
        this.jobTittle = jobTittle.getJobTittle();
    }
}
