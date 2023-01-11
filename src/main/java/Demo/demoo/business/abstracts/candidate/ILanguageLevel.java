package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.LanguageLevel;
import Demo.demoo.entities.dtos.requests.CreateLanguageLevelRequest;

import java.util.List;

public interface ILanguageLevel {
    Result add(CreateLanguageLevelRequest createLanguageLevelRequest);
    DataResult<List<LanguageLevel>>  getAll();
}
