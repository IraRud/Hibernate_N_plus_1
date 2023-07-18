package service;

import model.Role;
import model.User;

import java.util.List;
import java.util.Set;

public interface RoleDAO {

    Role addRole(Role role);
    Role getById(Long id);
    List<Role> getAllRoles();
    Set<User> getUsers(Role role);
    void deleteRole(Role role);

}
