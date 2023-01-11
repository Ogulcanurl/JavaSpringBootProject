package Demo.demoo.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "jobExperience")
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobExperienceId;
    @Column(name = "workplaceName")
    private String workplaceName;
    @Column(name = "position")
    private String position;
    @Column(name = "startingWork")
    private String startingWork;
    @Column(name = "quitWork")
    private String quitWork;
}
