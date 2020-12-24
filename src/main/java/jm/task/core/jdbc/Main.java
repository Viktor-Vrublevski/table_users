package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Борис", "Смирнов", (byte) 20);
        service.saveUser("Алексей", "Петров", (byte) 19);
        service.saveUser("Тамара", "Иванова", (byte) 23);
        service.saveUser("Ольга", "Прокапович", (byte) 30);

        List<User> list = service.getAllUsers();
        list.forEach(System.out::println);

        service.cleanUsersTable();

        service.dropUsersTable();

    }
}
