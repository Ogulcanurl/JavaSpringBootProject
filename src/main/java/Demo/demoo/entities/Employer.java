package Demo.demoo.entities;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import Demo.demoo.entities.User;
import Demo.demoo.entities.employer.JobAdvertisement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "employers")
public class Employer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
    @NotBlank(message = "boş geçilemez") @NotNull(message = "boş geçilemez")
    @Column(name = "website") private String website;
    @NotBlank(message = "boş geçilemez") @NotNull(message = "boş geçilemez")
    @Column(name = "companyName") private String companyName;
    @NotBlank(message = "boş geçilemez") @NotNull(message = "boş geçilemez")
    @Size(min = 11, max = 11)
    @Column(name = "phoneNumber") private String phoneNumber;
    @OneToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @OneToMany(mappedBy = "employer")
    @JsonIgnore
    private List<JobAdvertisement> jobAdvertisements;
}
