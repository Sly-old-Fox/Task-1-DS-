package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.userSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = """
                    CREATE TABLE IF NOT EXISTS "user"
                    (
                        id       SERIAL PRIMARY KEY,
                        name     VARCHAR(128),
                        last_name VARCHAR(128),
                        age      SMALLINT
                    );
                    """;
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.userSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = """
                    DROP TABLE IF EXISTS "user";
                    """;
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.userSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.userSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (session.find(User.class, id) != null) {
                session.remove(session.find(User.class, id));
            } else {
                System.out.println("User с id=" + id + " отсутствует");
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory sessionFactory = Util.userSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select u from User u", User.class).list();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.userSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User u").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
