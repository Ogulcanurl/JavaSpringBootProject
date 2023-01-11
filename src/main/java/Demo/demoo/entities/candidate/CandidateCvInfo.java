package Demo.demoo.entities.candidate;

import Demo.demoo.entities.Candidate;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "cvInfo")
public class CandidateCvInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;
    @OneToOne()
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Candidate candidateId;
    @OneToOne()
    @JoinColumn(name = "candidateImageId", referencedColumnName = "candidateImageId")
    private CandidateImage candidateImage;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cvTblSchools",
    joinColumns =
            @JoinColumn(name = "cvId"),
    inverseJoinColumns =
            @JoinColumn(name = "schoolInfoId")
    )
    private Set<SchoolInfo> schoolInfo = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cvTblJobExperiences",
            joinColumns = @JoinColumn(name = "cvId"),
            inverseJoinColumns = @JoinColumn(name = "jobExperienceId")
    )
    private Set<JobExperience> jobExperience = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cvTblLanguages",
            joinColumns = @JoinColumn(name = "cvId"),
            inverseJoinColumns = @JoinColumn(name = "languageLevelId")
    )
    private Set<LanguageLevel> languageLevels = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cvTblLinks",
            joinColumns = @JoinColumn(name = "cvId"),
            inverseJoinColumns = @JoinColumn(name = "linkId")
    )
    private Set<CandidateLink> candidateLinks = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cvTblProgramLanguages",
            joinColumns = @JoinColumn(name = "cvId"),
            inverseJoinColumns = @JoinColumn(name = "programLanguageId")
    )
    private Set<ProgramLanguage> programLanguage = new HashSet<>();
    @Column(name = "coverLetter")
    private String coverLetter;

    public void addSchool(SchoolInfo schoolInfo){
        this.schoolInfo.add(schoolInfo);
    }
    public void addJobExperience(JobExperience jobExperience){
        this.jobExperience.add(jobExperience);
    }
    public void addLanguageLevels(LanguageLevel level){
        this.languageLevels.add(level);
    }
    public void addLinks(CandidateLink link){
        this.candidateLinks.add(link);
    }
    public void addProgramLanguage(ProgramLanguage programLanguage){
        this.programLanguage.add(programLanguage);
    }
}
