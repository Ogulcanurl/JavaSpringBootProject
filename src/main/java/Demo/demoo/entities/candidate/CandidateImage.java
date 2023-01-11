package Demo.demoo.entities.candidate;

import Demo.demoo.entities.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "candidateImage")
public class CandidateImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateImageId;
    @OneToOne()
    @JsonIgnore
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Candidate candidate;
    @Column(name = "image")
    private String image;
}
