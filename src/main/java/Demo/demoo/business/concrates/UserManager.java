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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

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
        try {
            User user = userDao.getByEmail(email.toLowerCase());
            Employer employer = employerDao.getByUser_Email(email.toLowerCase());
            if (!iValidationRules.loginValidationWithEmployerEmail(email.toLowerCase()) && !iValidationRules.loginValidationWithEmployerPassword(password, user.getUserId())) {
                if(employer != null){
                    SystemAdminWithConfirmEmployer systemAdminWithConfirmEmployer = systemAdminWithConfirmEmployersDao.findById(employer.getId()).get();
                    if (user.getUserId() == employer.getUser().getUserId()){
                        if(systemAdminWithConfirmEmployer.isApproval()){
                            return new SuccessResult("Giriş Başarılı");
                        }else{
                            return new ErrorResult("ERR_USER_02");
                        }
                    }
                } else {
                    return new SuccessResult("Giriş başarılı");
                }
            } else if (iValidationRules.loginValidationWithEmployerEmail(email.toLowerCase()) || iValidationRules.loginValidationWithEmployerPassword(password, user.getUserId())) {
                return new ErrorResult("ERR_USER_01");
            }
            return new ErrorResult("Beklenemdik bir hata oluştu");
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }
}
