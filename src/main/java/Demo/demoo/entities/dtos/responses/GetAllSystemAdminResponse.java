package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.SystemAdmin;
import Demo.demoo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GetAllSystemAdminResponse {

    private int id;
    private String name;
    private String lastName;
    private User user;

    public GetAllSystemAdminResponse(SystemAdmin systemAdmin){
        this.id = systemAdmin.getSystemAdminId();
        this.name = systemAdmin.getName();
        this.lastName = systemAdmin.getLastName();
        this.user = systemAdmin.getUser();
    }
}
