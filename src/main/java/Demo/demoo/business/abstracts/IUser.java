package Demo.demoo.business.abstracts;

import Demo.demoo.core.utitilies.results.Result;

public interface IUser {
    Result login(String email, String password);
}
