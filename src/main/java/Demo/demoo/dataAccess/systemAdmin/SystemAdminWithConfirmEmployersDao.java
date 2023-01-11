package Demo.demoo.dataAccess.systemAdmin;

import Demo.demoo.entities.systemAdmin.SystemAdminWithConfirmEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminWithConfirmEmployersDao extends JpaRepository<SystemAdminWithConfirmEmployer, Integer> {

}
