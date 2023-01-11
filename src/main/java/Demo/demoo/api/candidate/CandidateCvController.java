package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidateCvInfo;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.dtos.requests.CreateCvInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cv/")
public class CandidateCvController {
    @Autowired
    ICandidateCvInfo iCandidateCvInfo;
    @GetMapping("getall")
    public DataResult<List<CandidateCvInfo>> getAll(){
        return iCandidateCvInfo.getAll();
    }
    @PostMapping("add")
    public Result add(CreateCvInfoRequest cvInfo){
        return iCandidateCvInfo.add(cvInfo);
    }
}
