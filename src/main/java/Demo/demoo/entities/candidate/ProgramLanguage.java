package Demo.demoo.entities.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "programLanguage")
public class ProgramLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programLanguageId;
    @Column(name = "programLanguageName")
    private String programLanguageName;
    @Column(name = "programFrameworkName")
    private String programFrameworkName;
}
