import model.Role;
import model.TypeOfRole;
import model.User;
import service.RoleDAO;
import service.RoleDAOImpl;
import service.UserDAO;
import service.UserDAOImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Application {

    static UserDAO userDAO = new UserDAOImpl();
    static RoleDAO roleDAO = new RoleDAOImpl();

    public static void main(String[] args) {

        Role developer = Role.builder()
                .typeOfRole(TypeOfRole.DEVELOPER).build();
        Role analyst = Role.builder().
                typeOfRole(TypeOfRole.ANALYST).build();
        Role tester = Role.builder()
                .typeOfRole(TypeOfRole.TESTER).build();
        Role manager = Role.builder().
                typeOfRole(TypeOfRole.MANAGER).build();
        Role designer = Role.builder()
                .typeOfRole(TypeOfRole.DESIGNER).build();
        Role defaultRole = Role.builder()
                .typeOfRole(TypeOfRole.DEFAULT).build();

        roleDAO.addRole(developer);
        roleDAO.addRole(analyst);
        roleDAO.addRole(tester);
        roleDAO.addRole(manager);
        roleDAO.addRole(designer);
        roleDAO.addRole(defaultRole);

        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleDAO.getById(1L));
        roles1.add(roleDAO.getById(2L));

        User user1 = User.builder()
                .name("Клим")
                .login("kleeeeee")
                .password("password")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .roles(roles1)
                .build();
        // добавлять нового пользователя с ролями в БД
        User addedUser1 = userDAO.addUser(user1);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleDAO.getById(5L));

        User user2 = User.builder()
                .name("Калиста")
                .login("callistaaa")
                .password("password")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .roles(roles2)
                .build();
        User addedUser2 = userDAO.addUser(user2);

        // получать список пользователей из БД
        System.out.println("Список всех пользователей:");
        userDAO.getAllUsers();

        // получать конкретного пользователя из БД
        User foundUser = userDAO.getById(2L);
        System.out.println("Найденный пользователь" + foundUser);

        // редактировать существующего пользователя в БД
        roles1.add(roleDAO.getById(3L));
        addedUser1.setRoles(roles1);
        userDAO.updateUser(addedUser1);

        // добавить роль пользователю
        userDAO.addRoleToUser(addedUser2, roleDAO.getById(4L));

        // получать список пользователей по конкретной роли;
        System.out.println("Список пользователей:");
        roleDAO.getUsers(developer);

        // удалять пользователя в БД
        userDAO.deleteUser(user1);
        userDAO.deleteUser(user2);
    }

}
