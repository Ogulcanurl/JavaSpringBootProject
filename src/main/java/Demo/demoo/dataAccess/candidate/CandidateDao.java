package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CandidateDao extends JpaRepository<Candidate, Integer> {
    Candidate getByUser_Email(String email);
    Candidate getByNationalId(String id);


}
