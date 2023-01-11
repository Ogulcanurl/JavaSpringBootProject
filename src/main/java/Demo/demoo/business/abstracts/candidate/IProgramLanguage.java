package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.ProgramLanguage;
import Demo.demoo.entities.dtos.requests.CreateProgramLanguageRequest;

import java.util.List;

public interface IProgramLanguage {
    Result add(CreateProgramLanguageRequest createProgramLanguageRequest);
    DataResult<List<ProgramLanguage>> getAll();
}
