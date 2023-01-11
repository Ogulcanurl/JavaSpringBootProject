package Demo.demoo.business.concrates.systemAdmin;

import Demo.demoo.business.abstracts.systemAdmin.ISystemAdmin;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.systemAdmin.SystemAdminDao;
import Demo.demoo.dataAccess.UserDao;
import Demo.demoo.entities.SystemAdmin;
import Demo.demoo.entities.User;
import Demo.demoo.entities.dtos.requests.CreateSystemAdminRequest;
import Demo.demoo.entities.dtos.responses.GetAllSystemAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemAdminManager implements ISystemAdmin {

    @Autowired
    IValidationRules iValidationRules;
    @Autowired
    SystemAdminDao systemAdminDao;
    @Autowired
    UserDao userDao;
    // Validation kuralları eklenecek.
    // SystemAdmin Emloyerı nasıl ontrol edecek düşün.
    @Override
    public Result add(CreateSystemAdminRequest createSystemAdminRequest) {
        if (iValidationRules.isEmailValid(createSystemAdminRequest.getEmail())
                && iValidationRules.isPasswordCheck(createSystemAdminRequest.getPassword())
                && iValidationRules.isThereSuchRecordWithEmail(createSystemAdminRequest.getEmail())
                && iValidationRules.isThereSuchRecordWithNationalId(createSystemAdminRequest.getNationalId())
                && iValidationRules.cannotBeEmptyWithSystemAdmin(createSystemAdminRequest.getName(), createSystemAdminRequest.getLastName(),
                createSystemAdminRequest.getNationalId(), createSystemAdminRequest.getEmail(), createSystemAdminRequest.getPassword())){

            SystemAdmin systemAdmin = new SystemAdmin();
            User user = new User();
            systemAdmin.setLastName(createSystemAdminRequest.getLastName());
            systemAdmin.setName(createSystemAdminRequest.getName());
            systemAdmin.setNationalId(createSystemAdminRequest.getNationalId());
            user.setEmail(createSystemAdminRequest.getEmail());
            user.setPassword(createSystemAdminRequest.getPassword());
            userDao.save(user);
            systemAdmin.setUser(user);
            systemAdminDao.save(systemAdmin);
            return new SuccessResult("Kayıt başarılı");
        } else if (!iValidationRules.isEmailValid(createSystemAdminRequest.getEmail())) {
            return new ErrorResult("geçerli bir email giriniz");
        } else if (!iValidationRules.isPasswordCheck(createSystemAdminRequest.getPassword())) {
            return new ErrorResult("geçerli bir şifre giriniz");
        } else if (!iValidationRules.isThereSuchRecordWithEmail(createSystemAdminRequest.getEmail())) {
            return new ErrorResult("böyle bir email zaten var");
        } else if (!iValidationRules.isThereSuchRecordWithNationalId(createSystemAdminRequest.getNationalId())) {
            return new ErrorResult("böyle bir kimlik numarası zaten kayıtlı");
        } else if (!iValidationRules.cannotBeEmptyWithSystemAdmin(createSystemAdminRequest.getName(), createSystemAdminRequest.getLastName(),
                createSystemAdminRequest.getNationalId(), createSystemAdminRequest.getEmail(), createSystemAdminRequest.getPassword())) {
            return new ErrorResult("hiçbir alan boş geçilemez");
        }

        return new ErrorResult("ups");
    }

    @Override
    public DataResult<List<GetAllSystemAdminResponse>> getall() {
        return new SuccessDataResult<>(systemAdminDao.findAll().stream().map(GetAllSystemAdminResponse::new).collect(Collectors.toList()),"Data Listelendi");
    }
}
