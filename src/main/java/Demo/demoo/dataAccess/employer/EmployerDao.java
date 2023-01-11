package Demo.demoo.dataAccess.employer;

import Demo.demoo.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

    Employer getByPhoneNumber(String phone);
    Employer getByWebsite(String website);
    Employer getByCompanyName(String companyName);

    Employer getByUser_UserId(int id);

    Employer getByUser_Email(String email);

    Employer getByUser_Password(String password);
}
