package Demo.demoo.business.abstracts.candidate;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.CvUpload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICvUpload {
    Result add(MultipartFile file, int candidateId);
    DataResult<List<CvUpload>> getAll();
}
