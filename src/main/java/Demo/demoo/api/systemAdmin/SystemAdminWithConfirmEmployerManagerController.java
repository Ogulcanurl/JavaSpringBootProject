package Demo.demoo.api.systemAdmin;

import Demo.demoo.business.abstracts.systemAdmin.ISystemAdminWithConfirmEmployer;
import Demo.demoo.business.concrates.systemAdmin.SystemAdminWithConfirmEmployerManager;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.responses.GetAllSystemAdminWithConfirmEmployerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/confirm/")
public class SystemAdminWithConfirmEmployerManagerController {
    @Autowired
    ISystemAdminWithConfirmEmployer systemAdminWithConfirmEmployer;
    @Autowired
    SystemAdminWithConfirmEmployerManager systemAdminWithConfirmEmployerManager;
    @GetMapping("confirmAdd")
    public Result confirm(int confirmId,int employerId, int systemAdminId, boolean approval){
        return systemAdminWithConfirmEmployer.confirm(confirmId,employerId,systemAdminId,approval);
    }
    @GetMapping("getAll")
    public DataResult<List<GetAllSystemAdminWithConfirmEmployerResponse>> getAll(){
        return systemAdminWithConfirmEmployerManager.getAll();
    }
}
