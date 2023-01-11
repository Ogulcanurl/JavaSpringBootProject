package Demo.demoo.dataAccess.systemAdmin;

import Demo.demoo.entities.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminDao extends JpaRepository<SystemAdmin,Integer> {
}
