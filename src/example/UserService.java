package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface UserService {

    @WebMethod
    boolean addUser(User u);
    @WebMethod
    boolean deleteUser(int id);
    @WebMethod
    User getUser(int id);
    @WebMethod
    ArrayList<User> getUsers();

}
