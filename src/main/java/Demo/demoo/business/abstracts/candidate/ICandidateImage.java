package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.responses.GetAllImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICandidateImage {
    Result add(MultipartFile file, int candidateId, int cvId);
    Result delete(int id) throws IOException;
    DataResult<List<GetAllImageResponse>> getAll();
}
