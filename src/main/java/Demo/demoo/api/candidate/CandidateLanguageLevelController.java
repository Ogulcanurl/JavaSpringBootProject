package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ILanguageLevel;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.LanguageLevel;
import Demo.demoo.entities.dtos.requests.CreateLanguageLevelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/language/")
public class CandidateLanguageLevelController {
    @Autowired
    ILanguageLevel level;
    @PostMapping("add")
    public Result add(CreateLanguageLevelRequest createLanguageLevelRequest){
        return level.add(createLanguageLevelRequest);
    }
    @GetMapping("getall")
    public DataResult<List<LanguageLevel>> getAll(){
        return level.getAll();
    }
}
