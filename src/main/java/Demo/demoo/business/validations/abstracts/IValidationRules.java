package Demo.demoo.business.validations.abstracts;

public interface IValidationRules {
     boolean isThereSuchRecordWithEmail(String email);
     boolean isThereSuchRecordWithNationalId(String nationalId);

     boolean cannotBeEmptyWithCandidate(String nationalId, String yearOfBirth,
                                        String email, String firstName, String lastName, String password);

     boolean isSizeValidationWithCandidate(String nationalId, String yearOfBirth);

     boolean isRepeatPasswordWithPassword(String candidate, String repeatPassword);

     boolean isEmailValid(String email);

     boolean isPasswordCheck(String password);

     boolean isWebsiteEqualToEmail(String domain, String website);

     boolean cannotBeEmptyWithEmployer(String companyName, String phone, String website,
                                       String email, String password);

     boolean isSizeValidationWithPhone(String phone);

     boolean cannotBeEmptyWithSystemAdmin(String name, String lastName, String nationalId,
                                          String email, String password);

     boolean isThereSuchRecordWithPhone(String phone);

     boolean isThereSuchRecordWithCompanyName(String companyName);

     boolean isThereSuchRecordWithWebsite(String website);

     boolean cannotBeEmptyWithJobTittle(String jobTittle);

     boolean isThereSuchRecordWithJobTittle(String jobTittle);

     boolean isThereSuchRecordWithCity(String city);

     boolean cannotBeEmptyWithJobAdvertisement(int employerId, int jobTittleId, String jobDescription,
                                               int cityId, String maxSalary, String minSalary,
                                               String numberOfOpenPosition, String applicationDateLine);
     boolean loginValidationWithEmployerEmail(String email);

     boolean loginValidationWithEmployerPassword(String password, int id);

     boolean cannotBeEmptyWithSchool(String schoolName, String department, String yerOfEducation);


}
