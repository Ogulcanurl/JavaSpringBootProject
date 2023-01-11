package Demo.demoo.business.concrates.employer;

import Demo.demoo.business.abstracts.employer.IJobAdvertisement;
import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.core.utitilies.results.*;
import Demo.demoo.dataAccess.employer.CityDao;
import Demo.demoo.dataAccess.employer.EmployerDao;
import Demo.demoo.dataAccess.employer.JobAdvertisementDao;
import Demo.demoo.dataAccess.employer.JobTittleDao;
import Demo.demoo.entities.employer.City;
import Demo.demoo.entities.Employer;
import Demo.demoo.entities.employer.JobAdvertisement;
import Demo.demoo.entities.employer.JobTittle;
import Demo.demoo.entities.dtos.requests.CreateJobAdvertisement;
import Demo.demoo.entities.dtos.responses.GetTableJobAdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class JobAdvertisementManager implements IJobAdvertisement {

    @Autowired
    IValidationRules iValidationRules;
    @Autowired
    JobAdvertisementDao jobAdvertisementDao;
    @Autowired
    EmployerDao employerDao;
    @Autowired
    JobTittleDao jobTittleDao;
    @Autowired
    CityDao cityDao;

    @Override
    public Result add(CreateJobAdvertisement createJobAdvertisement) {
        if (iValidationRules.cannotBeEmptyWithJobAdvertisement(createJobAdvertisement.getEmployerId(), createJobAdvertisement.getJobTittleId(), createJobAdvertisement.getJobDescription(), createJobAdvertisement.getCityId(), createJobAdvertisement.getMaxSalary(), createJobAdvertisement.getMinSalary(), createJobAdvertisement.getNumberOfOpenPosition(), createJobAdvertisement.getApplicationDeadLine())){
            JobAdvertisement jobAdvertisement = new JobAdvertisement();
            JobTittle jobTittle = jobTittleDao.findById(createJobAdvertisement.getJobTittleId()).get();
            City city = cityDao.findById(createJobAdvertisement.getCityId()).get();
            Employer employer = employerDao.findById(createJobAdvertisement.getEmployerId()).get();
            jobAdvertisement.setCity(city);
            jobAdvertisement.setJobDescription(createJobAdvertisement.getJobDescription());
            jobAdvertisement.setJobTittle(jobTittle);
            jobAdvertisement.setEmployer(employer);
            jobAdvertisement.setApplicationDeadline(createJobAdvertisement.getApplicationDeadLine());
            jobAdvertisement.setMaxSalary(createJobAdvertisement.getMaxSalary());
            jobAdvertisement.setMinSalary(createJobAdvertisement.getMinSalary());
            jobAdvertisement.setNumberOfOpenPosition(createJobAdvertisement.getNumberOfOpenPosition());
            jobAdvertisement.setActivate(true);
            return new SuccessDataResult<>(jobAdvertisementDao.save(jobAdvertisement),"a");
        } else if (!iValidationRules.cannotBeEmptyWithJobAdvertisement(createJobAdvertisement.getEmployerId(), createJobAdvertisement.getJobTittleId(), createJobAdvertisement.getJobDescription(), createJobAdvertisement.getCityId(), createJobAdvertisement.getMaxSalary(), createJobAdvertisement.getMinSalary(), createJobAdvertisement.getNumberOfOpenPosition(), createJobAdvertisement.getApplicationDeadLine())) {
            return new ErrorResult("Hiçbir alan boş geçilemez.");
        }
        return new ErrorResult("ups");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<>(jobAdvertisementDao.findAll(),"b");
    }

    @Override
    public DataResult<List<GetTableJobAdvertisementResponse>> activateJobAdvertisement(int employerId, int jobAdvertisementId, boolean activate) {
        JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId).get();
        jobAdvertisement.setId(jobAdvertisementId);
        if (activate && employerId == jobAdvertisement.getEmployer().getId()){
            jobAdvertisement.setActivate(true);
            jobAdvertisementDao.save(jobAdvertisement);
            return new SuccessDataResult<>(jobAdvertisementDao.findAll().stream().map(GetTableJobAdvertisementResponse :: new).collect(Collectors.toList()), "İlan aktifleşti");
        } else if ( !activate && employerId == jobAdvertisement.getEmployer().getId()){
            jobAdvertisement.setActivate(false);
            jobAdvertisementDao.save(jobAdvertisement);
            return new SuccessDataResult<>(jobAdvertisementDao.findAll().stream().map(GetTableJobAdvertisementResponse :: new).collect(Collectors.toList()), "İlan pasifleşti");
        }
        return new ErrorDataResult<>(null,"Böyle bir ilanınız bulunamadı.");
    }

    @Override
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivated() {
        return new SuccessDataResult<>(jobAdvertisementDao.getByActivateTrue().stream().map(GetTableJobAdvertisementResponse:: new).collect(Collectors.toList()),"Aktif iş ilanları listelendi.");
    }

    @Override
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueOrderByApplicationDeadlineAsc() {
        return new SuccessDataResult<>(jobAdvertisementDao.getByActivateTrueOrderByApplicationDeadlineAsc().stream().map(GetTableJobAdvertisementResponse :: new).collect(Collectors.toList()),"Aktif iş ilanları tarihe göre listelendi.");
    }

    @Override
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueAndEmployer_CompanyName(String companyName) {
        return new SuccessDataResult<>(jobAdvertisementDao.getByActivateTrueAndEmployer_CompanyName(companyName).stream().map(GetTableJobAdvertisementResponse :: new).collect(Collectors.toList()));
    }

    @Override
    public DataResult<List<GetTableJobAdvertisementResponse>> getByActivateTrueOrderByReleaseDateAsc() {
        return new SuccessDataResult<>(jobAdvertisementDao.getByActivateTrueOrderByReleaseDateAsc().stream().map(GetTableJobAdvertisementResponse :: new).collect(Collectors.toList()));
    }

   /*  @Override
   // public DataResult<Integer> deActivateJobAdvertisement(int id) {
     //   var jobAdvertisementOpt = jobAdvertisementDao.findById(id);
       // if (jobAdvertisementOpt.isPresent()) {
         //   jobAdvertisementOpt.get().setActivate(false);
            return new SuccessDataResult<>(id);
        } else
            return new ErrorDataResult<>(id);
    }
   */
}
