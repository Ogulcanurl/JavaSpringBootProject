package Demo.demoo.api.employer;

import Demo.demoo.business.abstracts.employer.ICity;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateCityRequest;
import Demo.demoo.entities.dtos.responses.GetAllCityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/city/")
public class CityController {
    @Autowired
    ICity iCity;
    @PostMapping("add")
    public Result add(CreateCityRequest createCityRequest){
        return iCity.add(createCityRequest);
    }
    @GetMapping("getall")
    public DataResult<List<GetAllCityResponse>> getAll(){
        return iCity.getAll();
    }
}
