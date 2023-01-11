package Demo.demoo.business.concrates.candidate;

import Demo.demoo.business.abstracts.candidate.ICandidate;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.adapter.IMernisChek;
import Demo.demoo.core.utitilies.results.ErrorResult;
import Demo.demoo.core.utitilies.results.Result;
import Demo.demoo.core.utitilies.results.SuccessDataResult;
import Demo.demoo.core.verifications.abstracts.IEmailVerification;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.UserDao;
import Demo.demoo.entities.Candidate;
import Demo.demoo.entities.User;
import Demo.demoo.entities.dtos.requests.CreateCandidateRequest;
import Demo.demoo.entities.dtos.responses.GetAllCandidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateManager implements ICandidate {

    @Autowired
    CandidateDao candidateDao;
    @Autowired
    UserDao userDao;
    @Autowired
    IValidationRules validationRules;
    @Autowired
    IEmailVerification iEmailVerification;
    @Autowired
    IMernisChek iMernisChek;
    @Override
    public Result add(CreateCandidateRequest createCandidateRequest) {

        if (validationRules.isThereSuchRecordWithEmail(createCandidateRequest.getEmail())
                && validationRules.isThereSuchRecordWithNationalId(createCandidateRequest.getNationalId())
                && validationRules.isEmailValid(createCandidateRequest.getEmail())
                && validationRules.isPasswordCheck(createCandidateRequest.getPassword())
                && validationRules.isSizeValidationWithCandidate(createCandidateRequest.getNationalId(),
                createCandidateRequest.getYearOfBirth())
                && validationRules.cannotBeEmptyWithCandidate(createCandidateRequest.getNationalId()
                , createCandidateRequest.getYearOfBirth()
                , createCandidateRequest.getEmail(), createCandidateRequest.getName(), createCandidateRequest.getLastName()
                , createCandidateRequest.getPassword())
                && validationRules.isRepeatPasswordWithPassword(createCandidateRequest.getPassword(),
                createCandidateRequest.getRepeatPassword())
                && validationRules.isSizeValidationWithCandidate(createCandidateRequest.getNationalId()
                , createCandidateRequest.getYearOfBirth())
                && iMernisChek.isRealPerson(createCandidateRequest.getNationalId())) {
            if(iEmailVerification.sendEmail(createCandidateRequest.getEmail())){
                Candidate candidate = new Candidate();
                User user = new User();
                candidate.setName(createCandidateRequest.getName());
                candidate.setLastName(createCandidateRequest.getLastName());
                candidate.setNationalId(createCandidateRequest.getNationalId());
                candidate.setYearOfBirth(createCandidateRequest.getYearOfBirth());
                user.setEmail(createCandidateRequest.getEmail().toLowerCase());
                user.setPassword(createCandidateRequest.getPassword());
                userDao.save(user);
                candidate.setUser(user);
                return new SuccessDataResult<>(candidateDao.save(candidate), "Kayıt başarılı");
            }
        } else if (!validationRules.isThereSuchRecordWithNationalId(createCandidateRequest.getNationalId())) {
            return new ErrorResult("Kimlik numarası zaten kayıtlı");
        } else if (!validationRules.isThereSuchRecordWithEmail(createCandidateRequest.getEmail())) {
            return new ErrorResult("E-mail zaten kayıtlı");
        } else if (!validationRules.isSizeValidationWithCandidate(createCandidateRequest.getNationalId(), createCandidateRequest.getYearOfBirth())) {
            return new ErrorResult("Girdiğiniz kimlik numarası veya Doğum yılıo yanlış.");
        } else if (!validationRules.isRepeatPasswordWithPassword(createCandidateRequest.getPassword(), createCandidateRequest.getRepeatPassword())) {
            return new ErrorResult("Şifre tekrarı yanlış.");
        } else if (!validationRules.isPasswordCheck(createCandidateRequest.getPassword())) {
            return new ErrorResult("Lütfen Geçerli Şifre Giriniz.");
        } else if (!validationRules.cannotBeEmptyWithCandidate(createCandidateRequest.getNationalId()
                , createCandidateRequest.getYearOfBirth()
                , createCandidateRequest.getEmail(), createCandidateRequest.getName(), createCandidateRequest.getLastName()
                , createCandidateRequest.getPassword())) {
            return new ErrorResult("hiçbir alan boş geçilemez");
        } else if (!iMernisChek.isRealPerson(createCandidateRequest.getNationalId())) {
            return new ErrorResult("Kimlik numarası doğru değil");
        } else if (!validationRules.isEmailValid(createCandidateRequest.getEmail())) {
            return new ErrorResult("Geçerli bir E-mail giriniz");
        } else if (!iEmailVerification.sendEmail(createCandidateRequest.getEmail())) {
            return new ErrorResult("doğrulama kodu gönderilemedi.");
        }
        return new ErrorResult("ups");
    }

    @Override
    public SuccessDataResult<List<GetAllCandidateResponse>> getAll() {
        return new SuccessDataResult<>(candidateDao.findAll().stream().map(GetAllCandidateResponse::new).collect(Collectors.toList()),"Data Listelendi");
    }
}
