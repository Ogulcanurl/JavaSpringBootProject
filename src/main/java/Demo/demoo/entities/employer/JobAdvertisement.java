package Demo.demoo.entities.employer;


import Demo.demoo.entities.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "jobAdvertisements")
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Employer employer;
    @ManyToOne()
    private JobTittle jobTittle;
    @Column(name = "jobDescription", nullable = false)
    private String jobDescription;
    @ManyToOne()
    private City city;
    @Column(name = "maxSalary")
    private String maxSalary;
    @Column(name = "minSalary")
    private String minSalary;
    @Column(name = "numberOfOpenPosition", nullable = false)
    private String numberOfOpenPosition;
    @Column(name = "releaseDate")
    private LocalDate releaseDate = LocalDate.now();
    @Column(name = "applicationDeadline")
    private String applicationDeadline;
    @Column(name = "activate")
    private boolean activate;

}
