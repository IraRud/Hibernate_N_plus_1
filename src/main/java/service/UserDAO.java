package service;

import model.Role;
import model.User;

import java.util.List;

public interface UserDAO {

    User addUser(User user);
    User getById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(User user);
    void addRoleToUser(User user, Role role);

}
