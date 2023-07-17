package service;

import model.Role;

import java.util.List;

public interface RoleDAO {

    Role addRole(Role role);
    Role getById(Long id);
    List<Role> getAllRoles();
    void deleteRole(Role role);

}
