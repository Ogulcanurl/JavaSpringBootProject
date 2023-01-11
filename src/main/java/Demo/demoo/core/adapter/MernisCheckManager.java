package Demo.demoo.core.adapter;

import org.springframework.stereotype.Service;

@Service
public class MernisCheckManager implements IMernisChek{
    @Override
    public boolean isRealPerson(String nationalId) {
        try {
            return true;
        }catch (RuntimeException e){
            e.fillInStackTrace();
            return false;
        }
    }
}
