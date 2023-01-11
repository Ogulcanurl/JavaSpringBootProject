package Demo.demoo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data @Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private int userId;
    @Column(name = "email") @Email
    @NotNull(message = "boş geçilemez") @NotBlank(message = "boş geçilemez")
    private String email;
    @Column(name = "password") @NotBlank(message = "boş geçilemez") @NotBlank(message = "boş geçilemez")
    @Size(min = 8, message = "8 karakterli olmalıdır")
    private String password;
}
