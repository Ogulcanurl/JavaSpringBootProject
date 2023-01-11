package Demo.demoo.business.concrates.systemAdmin;

import Demo.demoo.business.abstracts.systemAdmin.ISystemAdminWithConfirmEmployer;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.ErrorDataResult;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.dataAccess.employer.EmployerDao;
import Demo.demoo.dataAccess.systemAdmin.SystemAdminDao;
import Demo.demoo.dataAccess.systemAdmin.SystemAdminWithConfirmEmployersDao;
import Demo.demoo.entities.Employer;
import Demo.demoo.entities.SystemAdmin;
import Demo.demoo.entities.systemAdmin.SystemAdminWithConfirmEmployer;
import Demo.demoo.entities.dtos.responses.GetAllSystemAdminWithConfirmEmployerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SystemAdminWithConfirmEmployerManager implements ISystemAdminWithConfirmEmployer {

    @Autowired
    EmployerDao employerDao;
    @Autowired
    SystemAdminWithConfirmEmployersDao systemAdminWithConfirmEmployersDao;
    @Autowired
    SystemAdminDao systemAdminDao;

    @Override
    public DataResult<List<SystemAdminWithConfirmEmployer>> confirm(int confirmId, int employerId, int systemAdminId, boolean approval) {
        try {
            SystemAdminWithConfirmEmployer systemAdminWithConfirmEmployer = systemAdminWithConfirmEmployersDao.findById(confirmId).get();
            Employer employer = employerDao.findById(employerId).get();
            SystemAdmin systemAdmin = systemAdminDao.findById(systemAdminId).get();
            systemAdminWithConfirmEmployer.setEmployer(employer);
            systemAdminWithConfirmEmployer.setSystemAdmin(systemAdmin);
            if (approval){
                systemAdminWithConfirmEmployer.setApproval(true);
                systemAdminWithConfirmEmployersDao.save(systemAdminWithConfirmEmployer);
                return new SuccessDataResult<>(systemAdminWithConfirmEmployersDao.findAll(),"Doğrulama gerçekleşti");
            } else {
                systemAdminWithConfirmEmployer.setApproval(false);
                systemAdminWithConfirmEmployersDao.save(systemAdminWithConfirmEmployer);
                return new SuccessDataResult<>(systemAdminWithConfirmEmployersDao.findAll(), "ERR_SYSTEM_ADMİN_CONFİRM_EMPLOYER_01");
            }
        } catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorDataResult<>();
        }
    }
    @Override
    public DataResult<List<GetAllSystemAdminWithConfirmEmployerResponse>> getAll() {
        return new SuccessDataResult<>(systemAdminWithConfirmEmployersDao.findAll().stream().map(GetAllSystemAdminWithConfirmEmployerResponse::new).collect(Collectors.toList()), "Data Listelendi");
    }
}
