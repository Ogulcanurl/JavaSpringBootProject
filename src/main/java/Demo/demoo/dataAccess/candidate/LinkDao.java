package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.CandidateLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkDao extends JpaRepository<CandidateLink,Integer> {

}
