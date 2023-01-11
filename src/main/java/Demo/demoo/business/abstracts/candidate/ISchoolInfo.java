package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.SchoolInfo;
import Demo.demoo.entities.dtos.requests.CreateSchoolInfoRequest;

import java.util.List;

public interface ISchoolInfo {
    Result add(CreateSchoolInfoRequest createSchoolInfoRequest);
    DataResult<List<SchoolInfo>> getAll();
}
