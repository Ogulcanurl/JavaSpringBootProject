package Demo.demoo.api.candidate;

import Demo.demoo.business.abstracts.candidate.ICvUpload;
import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.entities.candidate.CvUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/cvupload/")
public class CvUploadController {

    @Autowired
    ICvUpload iCvUpload;

    @PostMapping("add")
    public Result add(@PathVariable MultipartFile file, int id){
        return iCvUpload.add(file,id);
    }
    @GetMapping("getAll")
    public DataResult<List<CvUpload>> getAll(){
        return iCvUpload.getAll();
    }
}
