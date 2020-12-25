package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util;

    public UserDaoJDBCImpl() {
        this.util = new Util();
    }

    public void createUsersTable() {
        try (Connection connection = util.getConnect();
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    " name VARCHAR(50) NOT NULL ," +
                    "lastname VARCHAR(70) NOT NULL," +
                    "age TINYINT NOT NULL);";
            statement.execute(sql);
            System.out.println("Таблица 'users' создана !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnect();
             Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users";
            statement.execute(sql);
            System.out.println("Таблица 'users' успешна удалена !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) VALUES " +
                "(?,?,?)";
        try (Connection connection = util.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = util.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int i = statement.executeUpdate();
            if (i > 0) {
                System.out.printf("User с id: %d удален !\n", id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = util.getConnect();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                String lastname = result.getString(3);
                byte age = result.getByte(4);
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setAge(age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnect();
             Statement statement = connection.createStatement()) {
            String sql = "TRUNCATE TABLE users";
            int i = statement.executeUpdate(sql);
            System.out.println("Таблица users успешно очищена !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
