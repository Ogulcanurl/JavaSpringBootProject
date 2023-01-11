package Demo.demoo.entities.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "languageLevel")
public class LanguageLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageLevelId;
    @Column(name = "languageName")
    private String languageName;
    @Column(name = "languageLevel")
    @Max(5)
    @Min(1)
    private String level;
}
