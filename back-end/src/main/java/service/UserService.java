package service;

import model.User;
import util.Response;

import java.util.List;

public interface UserService {
    User insertUser(User user);

    User alterUser(User user, Long id);

    List<User> getAllUsers();

    Response deleteById(Long id);

    User findById(Long id);
}
