package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.requests.CreateCandidateRequest;
import Demo.demoo.entities.dtos.responses.GetAllCandidateResponse;

import java.util.List;

public interface ICandidate {
     Result add(CreateCandidateRequest createCandidateRequest);

     DataResult<List<GetAllCandidateResponse>> getAll();

}
