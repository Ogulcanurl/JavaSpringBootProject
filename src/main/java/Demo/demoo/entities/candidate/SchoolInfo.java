package Demo.demoo.entities.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "schoolInfo")
public class SchoolInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolInfoId;
    @Column(name = "schoolName")
    private String schoolName;
    @Column(name = "department")
    private String department;
    @Column(name = "YearOfEducation")
    private String yearOfEducation;
    @Column(name = "graduated")
    private String graduated;

}
