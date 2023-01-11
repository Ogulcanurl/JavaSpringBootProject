package Demo.demoo.business.concrates.employer;

import Demo.demoo.business.abstracts.employer.IEmployer;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.employer.EmployerDao;
import Demo.demoo.dataAccess.systemAdmin.SystemAdminWithConfirmEmployersDao;
import Demo.demoo.dataAccess.UserDao;
import Demo.demoo.entities.Employer;
import Demo.demoo.entities.systemAdmin.SystemAdminWithConfirmEmployer;
import Demo.demoo.entities.User;
import Demo.demoo.entities.dtos.requests.CreateEmployerRequest;
import Demo.demoo.entities.dtos.responses.GetAllEmployerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerManager implements IEmployer {

    @Autowired
    IValidationRules iValidationRules;
    @Autowired
    EmployerDao employerDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SystemAdminWithConfirmEmployersDao systemAdminWithConfirmEmployersDao;

    @Override
    public Result add(CreateEmployerRequest createEmployerRequest) {
        if (iValidationRules.isPasswordCheck(createEmployerRequest.getPassword())
                && iValidationRules.isWebsiteEqualToEmail(createEmployerRequest.getEmail()
                , createEmployerRequest.getWebsite())
                && iValidationRules.isThereSuchRecordWithEmail(createEmployerRequest.getEmail())
                && iValidationRules.isRepeatPasswordWithPassword(createEmployerRequest.getPassword()
                , createEmployerRequest.getRepeatPassword())
                && iValidationRules.isThereSuchRecordWithWebsite(createEmployerRequest.getWebsite())
                && iValidationRules.isThereSuchRecordWithPhone(createEmployerRequest.getPhone())
                && iValidationRules.isThereSuchRecordWithCompanyName(createEmployerRequest.getCompanyName())
                && iValidationRules.isSizeValidationWithPhone(createEmployerRequest.getPhone())
                && iValidationRules.cannotBeEmptyWithEmployer(createEmployerRequest.getCompanyName(), createEmployerRequest.getPhone(),
                createEmployerRequest.getWebsite(), createEmployerRequest.getEmail(), createEmployerRequest.getPassword())){
            Employer employer = new Employer();
            User user = new User();
            employer.setPhoneNumber(createEmployerRequest.getPhone());
            employer.setWebsite(createEmployerRequest.getWebsite());
            employer.setCompanyName(createEmployerRequest.getCompanyName());
            user.setPassword(createEmployerRequest.getPassword());
            user.setEmail(createEmployerRequest.getEmail());
            userDao.save(user);
            employer.setUser(user);
            employerDao.save(employer);
            SystemAdminWithConfirmEmployer systemAdminWithConfirmEmployer = new SystemAdminWithConfirmEmployer();
            systemAdminWithConfirmEmployer.setEmployer(employer);
            systemAdminWithConfirmEmployer.setSystemAdmin(null);
            systemAdminWithConfirmEmployer.setApproval(false);
            systemAdminWithConfirmEmployersDao.save(systemAdminWithConfirmEmployer);
            return new SuccessDataResult<>("Kayıt başarılı");
        } else if (!iValidationRules.isWebsiteEqualToEmail(createEmployerRequest.getEmail(), createEmployerRequest.getWebsite())) {
            return new ErrorResult("Email adresiniz web siteniz ile aynı değil");
        } else if (!iValidationRules.isRepeatPasswordWithPassword(createEmployerRequest.getPassword(), createEmployerRequest.getRepeatPassword())) {
            return new ErrorResult("Şifre tekarı yanlış");
        } else if (!iValidationRules.isThereSuchRecordWithEmail(createEmployerRequest.getEmail())){
            return new ErrorResult("Email zaten var");
        } else if (!iValidationRules.isEmailValid(createEmployerRequest.getEmail())) {
            return new ErrorResult("Geçerli bir email giriniz");
        } else if (!iValidationRules.isThereSuchRecordWithWebsite(createEmployerRequest.getWebsite())) {
            return new ErrorResult("Website zaten kayıtlı");
        } else if (!iValidationRules.isThereSuchRecordWithCompanyName(createEmployerRequest.getCompanyName())) {
            return new ErrorResult("Böyle bir şirket adı zaten var");
        } else if (!iValidationRules.isThereSuchRecordWithPhone(createEmployerRequest.getPhone())) {
            return new ErrorResult("Böyle bir telefon numarası zaten kayıtlı");
        } else if (!iValidationRules.isPasswordCheck(createEmployerRequest.getPassword())) {
            return new ErrorResult("Şifrenizi doğru giriniz");
        } else if (!iValidationRules.isSizeValidationWithPhone(createEmployerRequest.getPhone())) {
            return new ErrorResult("Telefon numaranız 11 haneli olmalıdır");
        } else if (!iValidationRules.cannotBeEmptyWithEmployer(createEmployerRequest.getCompanyName(), createEmployerRequest.getPhone(),
                createEmployerRequest.getWebsite(), createEmployerRequest.getEmail(), createEmployerRequest.getPassword())) {
            return new ErrorResult("Hiçbir alan boş geçilemez");
        }
        return new ErrorResult("ups");
    }
    @Override
    public DataResult<List<GetAllEmployerResponse>> getall() {
        return new SuccessDataResult<>(employerDao.findAll().stream().map(GetAllEmployerResponse::new).collect(Collectors.toList()),"Data Listelendi");
    }
}
