package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.employer.City;
import Demo.demoo.entities.Employer;
import Demo.demoo.entities.employer.JobAdvertisement;
import Demo.demoo.entities.employer.JobTittle;
import lombok.Data;

@Data
public class GetAllJobAdvertisementResponse {
    private JobTittle jobTittle;
    private Employer employer;
    private String jobDescription;
    private City city;
    private String maxSalary;
    private String minSalary;
    private String numberOfOpenPosition;
    private String applicationDeadline;

    public GetAllJobAdvertisementResponse(JobAdvertisement jobAdvertisement){
        this.jobTittle = jobAdvertisement.getJobTittle();
        this.employer = jobAdvertisement.getEmployer();
        this.jobDescription = jobAdvertisement.getJobDescription();
        this.city = jobAdvertisement.getCity();
        this.maxSalary = jobAdvertisement.getMaxSalary();
        this.minSalary = jobAdvertisement.getMinSalary();
        this.numberOfOpenPosition = jobAdvertisement.getNumberOfOpenPosition();
        this.applicationDeadline = jobAdvertisement.getApplicationDeadline();
    }
}
