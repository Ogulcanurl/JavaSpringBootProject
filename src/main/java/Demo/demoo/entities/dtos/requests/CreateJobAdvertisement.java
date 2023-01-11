package Demo.demoo.entities.dtos.requests;

import lombok.Data;

@Data
public class CreateJobAdvertisement {
    private int employerId;
    private int jobTittleId;
    private String jobDescription;
    private int cityId;
    private String maxSalary;
    private String minSalary;
    private String numberOfOpenPosition;
    private String applicationDeadLine;
}
