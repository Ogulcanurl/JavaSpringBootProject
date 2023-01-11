package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ISchoolInfo;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.SchoolInfo;
import Demo.demoo.entities.dtos.requests.CreateSchoolInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("api/school/")
public class CandidateSchoolInfoController {
    @Autowired
    ISchoolInfo schoolInfo;
    @PostMapping("add")
    public Result add(CreateSchoolInfoRequest createSchoolInfoRequest){
        return schoolInfo.add(createSchoolInfoRequest);
    }
    @GetMapping("getall")
    public DataResult<List<SchoolInfo>> getAll(){
        return schoolInfo.getAll();
    }
}
