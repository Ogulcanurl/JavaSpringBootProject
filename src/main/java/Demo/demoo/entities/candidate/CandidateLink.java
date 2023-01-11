package Demo.demoo.entities.candidate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "candidateLink")
public class CandidateLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int linkId;
    @Column(name = "githubLink")
    private String githubLink;
    @Column(name = "linkedinLink")
    private String linkedinLink;
}
