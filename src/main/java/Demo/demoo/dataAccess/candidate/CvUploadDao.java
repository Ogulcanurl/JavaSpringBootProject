package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.CvUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvUploadDao extends JpaRepository<CvUpload, Integer> {
}
