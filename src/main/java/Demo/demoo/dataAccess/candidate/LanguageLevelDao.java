package Demo.demoo.dataAccess.candidate;

import Demo.demoo.entities.candidate.LanguageLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageLevelDao extends JpaRepository<LanguageLevel, Integer> {
}
