package Demo.demoo.entities.employer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "jobTittles")
public class JobTittle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "jobTittle")
    private String jobTittle;
    @Column(name = "jobDescription")
    private String jobDescription;
    @OneToMany(mappedBy = "jobTittle")
    @JsonIgnore
    private List<JobAdvertisement> jobAdvertisements;
}
