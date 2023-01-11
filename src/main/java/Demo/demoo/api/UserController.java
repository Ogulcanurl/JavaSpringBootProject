package Demo.demoo.api;

import Demo.demoo.business.abstracts.IUser;
import Demo.demoo.core.utitilies.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    IUser user;


    @GetMapping("login")
    public Result login(String email, String password){
        return user.login(email, password);
    }

}
