package service;

import model.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
    public void deleteRole(Role role) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(role);
            transaction.commit();
            session.close();
        }
    }

}
