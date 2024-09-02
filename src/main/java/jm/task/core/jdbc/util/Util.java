package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";

    private Util(){}

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
                PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USERNAME_KEY),
                PropertiesUtil.get(PASSWORD_KEY));
    }

    public static SessionFactory userSessionFactory(){
        Properties properties = new Properties();
        properties.put(Environment.USER, PropertiesUtil.get(USERNAME_KEY));
        properties.put(Environment.URL, PropertiesUtil.get(URL_KEY));
        properties.put(Environment.PASS, PropertiesUtil.get(PASSWORD_KEY));
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        return new Configuration().setProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
    }
}
