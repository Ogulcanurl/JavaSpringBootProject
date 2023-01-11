package Demo.demoo.dataAccess.employer;

import Demo.demoo.entities.employer.JobTittle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTittleDao extends JpaRepository<JobTittle, Integer> {
    JobTittle getByJobTittle(String jobTittle);
}
