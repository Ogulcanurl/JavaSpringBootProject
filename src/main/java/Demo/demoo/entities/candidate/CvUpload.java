package Demo.demoo.entities.candidate;

import Demo.demoo.entities.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "cvUpload")
public class CvUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvUploadId;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Candidate candidateId;
    @Column(name = "cvUrl")
    private String cvUrl;
}
