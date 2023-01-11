package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.CandidateCvInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CvInfoDao extends JpaRepository<CandidateCvInfo, Integer> {
    List<CandidateCvInfo> getByJobExperience_StartingWorkAsc();
}
