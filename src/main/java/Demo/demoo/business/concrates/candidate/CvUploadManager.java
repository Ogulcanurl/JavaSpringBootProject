package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ICvUpload;
import Demo.demoo.core.adapter.ICloudinary;
import Demo.demoo.core.utitilies.StringConvertHelper;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.candidate.CvUploadDao;
import Demo.demoo.entities.Candidate;
import Demo.demoo.entities.candidate.CvUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CvUploadManager implements ICvUpload {
    @Autowired
    CvUploadDao cvUploadDao;
    @Autowired
    CandidateDao candidateDao;
    @Autowired
    ICloudinary cloudinary;

    @Override
    public Result add(MultipartFile file, int candidateId) {
        var result = this.cloudinary.upload(file);
        StringConvertHelper multiFile = new StringConvertHelper();
        if(!result.isSuccess()) {
            return new ErrorResult(result.getMessage());
        }
        CvUpload cvUpload = new CvUpload();
        String url = result.getData().get("url");
        String[] readList = {"text", "pdf", "docx"};
        Candidate candidate = candidateDao.findById(candidateId).get();
        cvUpload.setCandidateId(candidate);
        cvUpload.setCvUrl(multiFile.multiFileValidation(url, readList, "png"));
        cvUploadDao.save(cvUpload);
        return new SuccessResult("Kayıt edildi");
    }

    @Override
    public DataResult<List<CvUpload>> getAll() {

        return new SuccessDataResult<>(cvUploadDao.findAll());
    }
}
