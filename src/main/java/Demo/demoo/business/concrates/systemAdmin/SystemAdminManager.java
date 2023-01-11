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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SystemAdminManager implements ISystemAdmin {

    @Autowired
    IValidationRules iValidationRules;
    @Autowired
    SystemAdminDao systemAdminDao;
    @Autowired
    UserDao userDao;

    @Override
    public Result add(CreateSystemAdminRequest createSystemAdminRequest) {

        try {
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
                return new ErrorResult("ERR_SYSTEM_ADMIN_05");
            } else if (!iValidationRules.isPasswordCheck(createSystemAdminRequest.getPassword())) {
                return new ErrorResult("ERR_SYSTEM_ADMIN_02");
            } else if (!iValidationRules.isThereSuchRecordWithEmail(createSystemAdminRequest.getEmail())) {
                return new ErrorResult("ERR_SYSTEM_ADMIN_03");
            } else if (!iValidationRules.isThereSuchRecordWithNationalId(createSystemAdminRequest.getNationalId())) {
                return new ErrorResult("ERR_SYSTEM_ADMIN_04");
            } else if (!iValidationRules.cannotBeEmptyWithSystemAdmin(createSystemAdminRequest.getName(), createSystemAdminRequest.getLastName(),
                    createSystemAdminRequest.getNationalId(), createSystemAdminRequest.getEmail(), createSystemAdminRequest.getPassword())) {
                return new ErrorResult("ERR_SYSTEM_ADMIN_01");
            }
            return new ErrorResult("ERR_SYSTEM_ADMIN_00");
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<GetAllSystemAdminResponse>> getall() {
        return new SuccessDataResult<>(systemAdminDao.findAll().stream().map(GetAllSystemAdminResponse::new).collect(Collectors.toList()),"Data Listelendi");
    }
}
