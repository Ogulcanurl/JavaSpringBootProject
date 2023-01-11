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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
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
        try{
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
                    return new SuccessDataResult<>(candidateDao.save(candidate));
                }
            } else if (!validationRules.isThereSuchRecordWithNationalId(createCandidateRequest.getNationalId())) {
                return new ErrorResult("ERR_CANDIDATE_01");
            } else if (!validationRules.isThereSuchRecordWithEmail(createCandidateRequest.getEmail())) {
                return new ErrorResult("ERR_CANDIDATE_02");
            } else if (!validationRules.isSizeValidationWithCandidate(createCandidateRequest.getNationalId(), createCandidateRequest.getYearOfBirth())) {
                return new ErrorResult("ERR_CANDIDATE_03");
            } else if (!validationRules.isRepeatPasswordWithPassword(createCandidateRequest.getPassword(), createCandidateRequest.getRepeatPassword())) {
                return new ErrorResult("ERR_CANDIDATE_04");
            } else if (!validationRules.isPasswordCheck(createCandidateRequest.getPassword())) {
                return new ErrorResult("ERR_CANDIDATE_05");
            } else if (!validationRules.cannotBeEmptyWithCandidate(createCandidateRequest.getNationalId()
                    , createCandidateRequest.getYearOfBirth()
                    , createCandidateRequest.getEmail(), createCandidateRequest.getName(), createCandidateRequest.getLastName()
                    , createCandidateRequest.getPassword())) {
                return new ErrorResult("ERR_CANDIDATE_06");
            } else if (!iMernisChek.isRealPerson(createCandidateRequest.getNationalId())) {
                return new ErrorResult("ERR_CANDIDATE_07");
            } else if (!validationRules.isEmailValid(createCandidateRequest.getEmail())) {
                return new ErrorResult("ERR_CANDIDATE_08");
            } else if (!iEmailVerification.sendEmail(createCandidateRequest.getEmail())) {
                return new ErrorResult("ERR_CANDIDATE_09");
            }
        }catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
        return new ErrorResult("ERR_CANDIDATE_00");
    }

    @Override
    public SuccessDataResult<List<GetAllCandidateResponse>> getAll() {
        return new SuccessDataResult<>(candidateDao.findAll().stream().map(GetAllCandidateResponse::new).collect(Collectors.toList()),"Data Listelendi");
    }
}
