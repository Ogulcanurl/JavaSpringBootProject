package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.CandidateImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateImageDao extends JpaRepository<CandidateImage,Integer> {
}
