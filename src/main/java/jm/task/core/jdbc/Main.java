package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        List<User> users = new ArrayList<>();
        users.add(new User("Lisa", "Member", (byte) 24));
        users.add(new User("Lisiya", "Memb", (byte) 21));
        users.add(new User("Jack", "Frost", (byte) 27));
        users.add(new User("Боб", "Рейвенс", (byte) 40));
        for (User u : users) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
        }

        users.clear();
        users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
