package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.dtos.requests.CreateCvInfoRequest;

import java.util.List;


public interface ICandidateCvInfo {
    Result add(CreateCvInfoRequest cvInfo);
    DataResult<List<CandidateCvInfo>> getAll();
    DataResult<CandidateCvInfo> getById(int id);

    DataResult<List<CandidateCvInfo>> getByJobExperienceAsc();
}
