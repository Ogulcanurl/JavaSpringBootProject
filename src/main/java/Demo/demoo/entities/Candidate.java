package Demo.demoo.entities;


import Demo.demoo.entities.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstName")
    @NotBlank(message = "boş geçilemez")
    @NotNull(message = "boş geçilemez")
    private String name;
    @NotBlank(message = "boş geçilemez")
    @NotNull(message = "boş geçilemez")
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "nationalId")
    @NotBlank(message = "boş geçilemez") @NotNull(message = "boş geçilemez")
    @Size(min = 11, max = 11)
    private String nationalId;
    @Column(name = "yearOfBirth")
    @NotBlank(message = "boş geçilemez") @NotNull(message = "boş geçilemez")
    @Size(min = 4, max = 4)
    private String yearOfBirth;
    @OneToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

}
