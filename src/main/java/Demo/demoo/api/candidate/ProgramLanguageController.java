package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.IProgramLanguage;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.ProgramLanguage;
import Demo.demoo.entities.dtos.requests.CreateProgramLanguageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/programlanguage/")
public class ProgramLanguageController {
    @Autowired
    IProgramLanguage programLanguage;
    @PostMapping("add")
    public Result add(CreateProgramLanguageRequest createProgramLanguageRequest){
        return programLanguage.add(createProgramLanguageRequest);
    }
    @GetMapping("getall")
    public DataResult<List<ProgramLanguage>> getAll(){
        return programLanguage.getAll();
    }
}
