package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.JobExperience;
import Demo.demoo.entities.dtos.requests.CreateCandidateJobExperienceRequest;

import java.util.List;

public interface IJobExperience {
    Result add(CreateCandidateJobExperienceRequest createCandidateJobExperienceRequest);
    DataResult<List<JobExperience>> getAll();
}
