package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidateImage;
import Demo.demoo.core.adapter.ICloudinary;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.candidate.CandidateImageDao;
import Demo.demoo.dataAccess.candidate.CvInfoDao;
import Demo.demoo.entities.Candidate;
import Demo.demoo.entities.candidate.CandidateCvInfo;
import Demo.demoo.entities.candidate.CandidateImage;
import Demo.demoo.entities.dtos.responses.GetAllImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateImageManager implements ICandidateImage {
    @Autowired
    CandidateImageDao candidateImageDao;
    @Autowired
    CandidateDao candidateDao;
    @Autowired
    CvInfoDao cvInfoDao;
    @Autowired
    ICloudinary cloudinary;


    @Override
    public Result add(MultipartFile file, int candidateId, int cvId) {
        var result = this.cloudinary.upload(file);
        if (!result.isSuccess()) {
            return new ErrorResult(result.getMessage());
        }
        CandidateImage candidateImage = new CandidateImage();
        Candidate candidate = candidateDao.findById(candidateId).get();
        CandidateCvInfo candidateCvInfo = cvInfoDao.findById(cvId).get();
        candidateImage.setCandidate(candidate);
        candidateImage.setImage(result.getData().get("url"));
        candidateImageDao.save(candidateImage);
        candidateCvInfo.setCandidateImage(candidateImage);
        cvInfoDao.save(candidateCvInfo);
        return new SuccessResult("Kayıt başarılı");
    }

    @Override
    public Result delete(int id) throws IOException {
        var candidateId = candidateImageDao.findById(id).get().getCandidateImageId();
        var result = this.cloudinary.delete(String.valueOf(candidateId));
        this.candidateImageDao.deleteById(id);
        return new SuccessResult("deleted");
    }

    @Override
    public DataResult<List<GetAllImageResponse>> getAll() {
        return new SuccessDataResult<>(candidateImageDao.findAll().stream().map(GetAllImageResponse::new).collect(Collectors.toList()), "Data Listelendi");
    }
}
