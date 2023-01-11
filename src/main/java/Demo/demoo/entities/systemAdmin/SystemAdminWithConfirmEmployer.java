package Demo.demoo.entities.systemAdmin;

import Demo.demoo.entities.Employer;
import Demo.demoo.entities.SystemAdmin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "systemAdminWithConfirmEmployers")
public class SystemAdminWithConfirmEmployer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
    @OneToOne @JoinColumn(name = "id", referencedColumnName = "id") private Employer employer;
    @OneToOne @JoinColumn(name = "systemAdminId", referencedColumnName = "systemAdminId") private SystemAdmin systemAdmin;
    @Column(name = "approval") private boolean approval;
}
