package Demo.demoo.dataAccess;

import Demo.demoo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User getByEmail(String email);

    User getByPasswordAndUserId(String password, int id);
}
