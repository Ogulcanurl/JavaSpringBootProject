package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.CandidateCvInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CvInfoDao extends JpaRepository<CandidateCvInfo, Integer> {
    CandidateCvInfo findByCandidateId_Id(int id);
}
