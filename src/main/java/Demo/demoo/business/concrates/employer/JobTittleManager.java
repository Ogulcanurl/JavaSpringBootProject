package Demo.demoo.business.concrates.employer;

import Demo.demoo.business.abstracts.employer.IJobTittle;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.employer.JobTittleDao;
import Demo.demoo.entities.employer.JobTittle;
import Demo.demoo.entities.dtos.requests.CreateJobTittleRequest;
import Demo.demoo.entities.dtos.responses.GetAllJobTittleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class JobTittleManager implements IJobTittle {

    @Autowired
    IValidationRules iValidationRules;
    @Autowired
    JobTittleDao jobTittleDao;

    @Override
    public Result add(CreateJobTittleRequest createJobTittleRequest) {
        try {
            if (iValidationRules.cannotBeEmptyWithJobTittle(createJobTittleRequest.getJobTittle())
                    && iValidationRules.isThereSuchRecordWithJobTittle(createJobTittleRequest.getJobTittle())){
                JobTittle jobTittle = new JobTittle();
                jobTittle.setJobTittle(createJobTittleRequest.getJobTittle());
                jobTittle.setJobDescription(createJobTittleRequest.getDescription());
                jobTittleDao.save(jobTittle);
                return new SuccessResult();
            } else if (!iValidationRules.cannotBeEmptyWithJobTittle(createJobTittleRequest.getJobTittle())) {
                return new ErrorResult("ERR_JOB_TITTLE_01");
            } else if (!iValidationRules.isThereSuchRecordWithJobTittle(createJobTittleRequest.getJobTittle())) {
                return new ErrorResult("ERR_JOB_TITTLE_02");
            }
            return new ErrorResult("ERR_JOB_TITTLE_00");
        } catch (MethodArgumentTypeMismatchException | NoSuchElementException | NullPointerException e){
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<GetAllJobTittleResponse>> getall() {
        return new SuccessDataResult<>(jobTittleDao.findAll().stream().map(GetAllJobTittleResponse::new).collect(Collectors.toList()), "Data Listelendi");
    }
}
