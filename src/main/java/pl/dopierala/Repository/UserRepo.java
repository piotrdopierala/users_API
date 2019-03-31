package pl.dopierala.Repository;

import pl.dopierala.Domain.User;
import pl.dopierala.Domain.UserDTO;

import java.util.List;

public interface UserRepo {
    List<User> getUsers();

    void saveUser(User usr);

    User getById(int id);

    User deleteUserById(int id);

    User updateUserById(int id, UserDTO userUpdateData);
}
