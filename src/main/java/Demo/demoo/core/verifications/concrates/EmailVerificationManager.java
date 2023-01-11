package Demo.demoo.core.verifications.concrates;


import Demo.demoo.core.verifications.abstracts.IEmailVerification;
import org.springframework.stereotype.Service;


@Service
public class EmailVerificationManager implements IEmailVerification {

    @Override
    public boolean sendEmail(String email) {
        return true;
    }
}
