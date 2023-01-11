package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolInfoDao extends JpaRepository<SchoolInfo, Integer> {

}
