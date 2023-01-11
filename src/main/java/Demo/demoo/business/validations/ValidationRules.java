package Demo.demoo.business.validations;

import Demo.demoo.business.validations.abstracts.IValidationRules;
import Demo.demoo.dataAccess.*;
import Demo.demoo.dataAccess.candidate.CandidateDao;
import Demo.demoo.dataAccess.employer.CityDao;
import Demo.demoo.dataAccess.employer.EmployerDao;
import Demo.demoo.dataAccess.employer.JobTittleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationRules implements IValidationRules {

    @Autowired
    CandidateDao candidateDao;
    @Autowired
    UserDao userDao;
    @Autowired
    EmployerDao employerDao;
    @Autowired
    JobTittleDao jobTittleDao;
    @Autowired
    CityDao cityDao;

    @Override
    public boolean isThereSuchRecordWithEmail(String email) {
        return candidateDao.getByUser_Email(email.toUpperCase().trim().toLowerCase()) == null;
    }

    @Override
    public boolean isThereSuchRecordWithNationalId(String nationalId) {
        return candidateDao.getByNationalId(nationalId) == null;
    }

    @Override
    public boolean cannotBeEmptyWithCandidate(String nationalId, String yearOfBirth,
                                              String email, String firstName, String lastName, String password) {
        return !email.trim().isEmpty() && !password.trim().isEmpty() && !nationalId.trim().isEmpty()
                && !firstName.trim().isEmpty() && !yearOfBirth.trim().isEmpty() && !lastName.trim().isEmpty();
    }

    @Override
    public boolean isSizeValidationWithCandidate(String nationalId, String yearOfBirth) {
        return nationalId.length() == 11 && yearOfBirth.length() == 4;
    }

    @Override
    public boolean isRepeatPasswordWithPassword(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    @Override
    public boolean isEmailValid(String email) {
        String emailCheck = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailCheck);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public boolean isPasswordCheck(String password){
        if (password.length() <= 7){
            return false;
        }else {
            for (int i = 0; i < password.length(); i++){
                char c = password.charAt(i);
                if (Character.isUpperCase(c)){
                    return true;
                }
                if (Character.isDigit(c)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isWebsiteEqualToEmail(String email, String domain) {
        String[] emails = email.split("[@.]+");
        String[] domains = domain.split("\\.");
        return domains[0].equalsIgnoreCase(emails[1]);
    }

    @Override
    public boolean cannotBeEmptyWithEmployer(String companyName, String phone, String website, String email, String password) {
        return !companyName.trim().isEmpty() && !phone.trim().isEmpty() && !email.trim().isEmpty()
                && !website.trim().isEmpty() && !password.trim().isEmpty();
    }

    @Override
    public boolean isSizeValidationWithPhone(String phone) {
        return phone.trim().length() == 11;
    }

    @Override
    public boolean isThereSuchRecordWithPhone(String phone) {
        return employerDao.getByPhoneNumber(phone) == null;
    }

    @Override
    public boolean isThereSuchRecordWithCompanyName(String companyName) {
        return employerDao.getByCompanyName(companyName) == null;
    }

    @Override
    public boolean isThereSuchRecordWithWebsite(String website) {
        return employerDao.getByWebsite(website) == null;
    }

    @Override
    public boolean cannotBeEmptyWithJobTittle(String jobTittle) {
        return !jobTittle.trim().isEmpty();
    }

    @Override
    public boolean isThereSuchRecordWithJobTittle(String jobTittle) {
        return jobTittleDao.getByJobTittle(jobTittle.toLowerCase().trim().toUpperCase()) == null;
    }

    @Override
    public boolean isThereSuchRecordWithCity(String city) {
        return cityDao.getByCity(city.trim().toUpperCase()) == null;
    }

    @Override
    public boolean cannotBeEmptyWithJobAdvertisement(int employerId, int jobTittleId, String jobDescription, int cityId, String maxSalary, String minSalary, String numberOfOpenPosition,  String applicationDateLine) {
        return employerId == 0 && jobTittleId == 0 && !jobDescription.trim().isEmpty() && cityId == 0 && !maxSalary.trim().isEmpty() && !minSalary.trim().isEmpty() && !numberOfOpenPosition.trim().isEmpty() && !applicationDateLine.trim().isEmpty();
    }

    @Override
    public boolean loginValidationWithEmployerEmail(String email) {
        return userDao.getByEmail(email) == null;
    }

    @Override
    public boolean loginValidationWithEmployerPassword(String password, int id){
        return userDao.getByPasswordAndUserId(password, id) == null;
    }

    @Override
    public boolean cannotBeEmptyWithSchool(String schoolName, String department, String yearOfEducation) {
        return !schoolName.trim().isEmpty() && !department.trim().isEmpty() && !yearOfEducation.trim().isEmpty();
    }

    @Override
    public boolean cannotBeEmptyWithSystemAdmin(String name, String lastName, String nationalId, String email, String password) {
        return !name.trim().isEmpty() && !lastName.trim().isEmpty() && !nationalId.trim().isEmpty()
                && !email.trim().isEmpty() && !password.trim().isEmpty();
    }

}
