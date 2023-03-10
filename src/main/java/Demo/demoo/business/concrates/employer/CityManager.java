package Demo.demoo.business.concrates.employer;

import Demo.demoo.business.abstracts.employer.ICity;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.employer.CityDao;
import Demo.demoo.entities.employer.City;
import Demo.demoo.entities.dtos.requests.CreateCityRequest;
import Demo.demoo.entities.dtos.responses.GetAllCityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CityManager implements ICity {
    @Autowired
    CityDao cityDao;
    @Autowired
    IValidationRules iValidationRules;

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        try {
            if (iValidationRules.isThereSuchRecordWithCity(createCityRequest.getCity())){
                City city = new City();
                city.setCity(createCityRequest.getCity());
                return new SuccessDataResult<>(cityDao.save(city),"Şehir kaydedildi.");
            }else if (!iValidationRules.isThereSuchRecordWithCity(createCityRequest.getCity())){
                return new ErrorResult("ERR_CITY_01");
            }
            return new ErrorResult("ERR_CITY_00");
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<GetAllCityResponse>> getAll() {
        return new SuccessDataResult<>(cityDao.findAll().stream().map(GetAllCityResponse::new).collect(Collectors.toList()),"Data Listelendi");
    }
}
