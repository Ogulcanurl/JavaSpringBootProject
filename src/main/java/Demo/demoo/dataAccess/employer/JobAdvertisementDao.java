package Demo.demoo.dataAccess.employer;

import Demo.demoo.entities.employer.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
    List<JobAdvertisement> getByActivateTrue();
    List<JobAdvertisement> getByActivateTrueOrderByApplicationDeadlineAsc();

    List<JobAdvertisement> getByActivateTrueAndEmployer_CompanyName(String companyName);

    List<JobAdvertisement> getByActivateTrueOrderByReleaseDateAsc();
}
