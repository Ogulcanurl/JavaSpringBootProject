package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.ProgramLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramLanguageDao extends JpaRepository<ProgramLanguage, Integer> {
}
