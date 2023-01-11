package Demo.demoo.business.concrates;

import Demo.demoo.business.abstracts.IUser;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.ErrorResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessResult;
import Demo.demoo.dataAccess.employer.EmployerDao;
import Demo.demoo.dataAccess.systemAdmin.SystemAdminWithConfirmEmployersDao;
import Demo.demoo.dataAccess.UserDao;
import Demo.demoo.entities.Employer;
import Demo.demoo.entities.systemAdmin.SystemAdminWithConfirmEmployer;
import Demo.demoo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements IUser {

    @Autowired
    UserDao userDao;
    @Autowired
    IValidationRules iValidationRules;
    @Autowired
    EmployerDao employerDao;
    @Autowired
    SystemAdminWithConfirmEmployersDao systemAdminWithConfirmEmployersDao;
    @Override
    public Result login(String email, String password) {
        User user = userDao.getByEmail(email);
        Employer employer = employerDao.getByUser_Email(email);
        if (!iValidationRules.loginValidationWithEmployerEmail(email) && !iValidationRules.loginValidationWithEmployerPassword(password, user.getUserId())) {
            if(employer != null){
                SystemAdminWithConfirmEmployer systemAdminWithConfirmEmployer = systemAdminWithConfirmEmployersDao.findById(employer.getId()).get();
                if (user.getUserId() == employer.getUser().getUserId()){
                    if(systemAdminWithConfirmEmployer.isApproval()){
                        return new SuccessResult("Giriş Başarılı");
                    }else{
                        return new ErrorResult("Doğrulama bekleniyor.");
                    }
                }
            } else {
                return new SuccessResult("Giriş başarılı");
            }
        } else if (iValidationRules.loginValidationWithEmployerEmail(email) || iValidationRules.loginValidationWithEmployerPassword(password, user.getUserId())) {
            return new ErrorResult("Şifrenizi veya Epostanızı kontrol edin.");
        }
        return new ErrorResult("Beklenemdik bir hata oluştu");
    }
}
