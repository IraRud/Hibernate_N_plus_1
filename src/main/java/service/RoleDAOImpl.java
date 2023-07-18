package service;

import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDAOImpl implements RoleDAO {

    @Override
    public Role addRole(Role role) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
            session.close();
        }
        return role;
    }

    @Override
    public Role getById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Role.class, id);
        }
    }

    @Override
    public List<Role> getAllRoles() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Role").list();
        }
    }

    @Override
    public Set<User> getUsers(Role role) {
        Set<User> foundUsers = new HashSet<>();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Role foundRole = session.find(Role.class, role.getId());
            foundUsers = foundRole.getUsers();
            transaction.commit();
            session.close();
            return foundUsers;
        }
    }

    @Override
    public void deleteRole(Role role) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(role);
            transaction.commit();
            session.close();
        }
    }

}
