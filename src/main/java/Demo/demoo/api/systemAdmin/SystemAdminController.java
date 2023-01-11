package Demo.demoo.api.systemAdmin;

import Demo.demoo.business.abstracts.systemAdmin.ISystemAdmin;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateSystemAdminRequest;
import Demo.demoo.entities.dtos.responses.GetAllSystemAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/systemadmin/")
@RestController
public class SystemAdminController {
    @Autowired
    ISystemAdmin systemAdmin;
    @GetMapping("getall")
    public DataResult<List<GetAllSystemAdminResponse>> getall(){
        return systemAdmin.getall();
    }
    @PostMapping("add")
    public Result add(CreateSystemAdminRequest createSystemAdminRequest){
        return systemAdmin.add(createSystemAdminRequest);
    }
}
