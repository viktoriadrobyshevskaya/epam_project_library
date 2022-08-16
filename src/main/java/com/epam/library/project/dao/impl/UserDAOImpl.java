package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.UserDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void signIn(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UserQuery.SIGN_IN);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DAOException("Пользователь с таким логином и паролем не найден.");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void registration(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UserQuery.REGISTRATION_USER);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRoleId());
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public void updatePassword(int userId, int password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UserQuery.UPDATE_PASSWORD);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, password);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public void updateEmail(int userId, int email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UserQuery.UPDATE_EMAIL);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, email);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UserQuery.All_USERS);

            resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                int roleId = resultSet.getInt(4);

                users.add(new User(userId, login, password, roleId));
            }

            return users;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public User findUserByLogin(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(UserQuery.FIND_USER);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            User user = null;

            if (!resultSet.next()) {
                throw new DAOException("Пользователь с таким логином и паролем не найден.");
            }

            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String password = resultSet.getString(3);
                int roleId = resultSet.getInt(4);

                user = new User(userId, login, password, roleId);
            }

            return user;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UserQuery.DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Something wrong!" + e);
        }
    }
}
