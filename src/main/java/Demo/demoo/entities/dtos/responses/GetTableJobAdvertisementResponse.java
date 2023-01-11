package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.Employer;
import Demo.demoo.entities.employer.JobAdvertisement;
import Demo.demoo.entities.employer.JobTittle;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetTableJobAdvertisementResponse {
    private Employer employer;
    private JobTittle jobTittle;
    private String numberOfOpenPosition;
    private String applicationDeadline;
    private LocalDate releaseDate;
    private boolean activate;


    public GetTableJobAdvertisementResponse(JobAdvertisement jobAdvertisement){
        this.jobTittle = jobAdvertisement.getJobTittle();
        this.employer = jobAdvertisement.getEmployer();
        this.numberOfOpenPosition = jobAdvertisement.getNumberOfOpenPosition();
        this.applicationDeadline = jobAdvertisement.getApplicationDeadline();
        this.releaseDate  = jobAdvertisement.getReleaseDate();
        this.activate = jobAdvertisement.isActivate();
    }
}
