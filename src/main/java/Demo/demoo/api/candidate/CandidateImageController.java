package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidateImage;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.dtos.responses.GetAllImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/candidateImage/")
public class CandidateImageController {
    @Autowired
    ICandidateImage iCandidateImage;
    @PostMapping("add")
    public Result add(@PathVariable MultipartFile file, int candidateId, int cvId) throws IOException {
        return iCandidateImage.add(file, candidateId, cvId);
    }
    @GetMapping("getall")
    private DataResult<List<GetAllImageResponse>> getAll(){
        return iCandidateImage.getAll();
    }
}
