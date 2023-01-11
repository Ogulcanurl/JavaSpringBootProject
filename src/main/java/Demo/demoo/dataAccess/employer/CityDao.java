package Demo.demoo.dataAccess.employer;

import Demo.demoo.entities.employer.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {
     City getByCity(String city);
}
