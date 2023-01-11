package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.employer.City;
import lombok.Data;

@Data
public class GetAllCityResponse {
    private int id;
    private String city;

   public GetAllCityResponse(City city){
       this.city = city.getCity();
       this.id = city.getCityId();
   }
}
