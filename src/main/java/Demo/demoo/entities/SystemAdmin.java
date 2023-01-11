package Demo.demoo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "systemAdmins")
public class SystemAdmin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int systemAdminId;
    @Column(name = "name") private String name;
    @Column(name = "lastName") private String lastName;
    @Column(name = "nationalId") private String nationalId;
    @OneToOne @JoinColumn(name = "userId", referencedColumnName = "userId") private User user;
}
