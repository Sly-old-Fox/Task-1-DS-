package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final UserServiceImpl userService = new UserServiceImpl();

        //Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор.
        List<User> users = new ArrayList<>();
        users.add(new User("Lisa", "Member", (byte) 24));
        users.add(new User("Lisiya", "Memb", (byte) 21));
        users.add(new User("Jack", "Frost", (byte) 27));
        users.add(new User("Bob", "Reivens", (byte) 40));
        for (User u : users) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
        }

        //Получение всех User из базы и вывод в консоль
        users.clear();
        users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}
