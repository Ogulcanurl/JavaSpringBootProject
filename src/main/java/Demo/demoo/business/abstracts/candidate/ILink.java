package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.CandidateLink;
import Demo.demoo.entities.dtos.requests.CreateLinkRequest;

import java.util.List;


public interface ILink {
    Result add(CreateLinkRequest createLinkRequest);
    DataResult<List<CandidateLink>> getAll();
}
