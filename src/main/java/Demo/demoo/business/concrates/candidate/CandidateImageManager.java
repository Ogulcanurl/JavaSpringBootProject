package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidateImage;
import Demo.demoo.core.adapter.ICloudinary;
import Demo.demoo.core.utitilies.StringConvertHelper;
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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
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

        try {
            var result = this.cloudinary.upload(file);
            CandidateImage candidateImage = new CandidateImage();
            StringConvertHelper convertHelper = new StringConvertHelper();
            if (!result.isSuccess()) {
                return new ErrorResult(result.getMessage());
            }
            if(file == null){
                candidateImage.setImage("Resim Yüklenmemiş");
            }
            Candidate candidate = candidateDao.findById(candidateId).get();
            CandidateCvInfo candidateCvInfo = cvInfoDao.findById(cvId).get();
            candidateImage.setCandidate(candidate);
            String[] readList = {"pdf","txt","docx"};
            String url = result.getData().get("url");
            candidateImage.setImage(convertHelper.multiFileValidation(url, readList, "png"));
            candidateImageDao.save(candidateImage);
            candidateCvInfo.setCandidateImage(candidateImage);
            cvInfoDao.save(candidateCvInfo);
            return new SuccessResult();
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }

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
