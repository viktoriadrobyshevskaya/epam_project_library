package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.UserDetailsDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDAOImpl implements UserDetailsDAO {

    @Override
    public List<UserDetails> getAllUserDetails() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.All_USER_DETAILS);

            resultSet = preparedStatement.executeQuery();

            List<UserDetails> userDetails = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String surname = resultSet.getString(4);
                String phone = resultSet.getString(5);
                String address = resultSet.getString(6);

                UserDetails userDetails1 = new UserDetails(id, userId, name, surname, phone, address);
                userDetails1.setUser(DAOFactory.getInstance().getUserDAO().findUserById(userId));
                userDetails.add(userDetails1);
            }

            return userDetails;

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int addUserDetails(UserDetails userDetails) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ADD_USER_DETAILS, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userDetails.getUserId());
            statement.setString(2, userDetails.getName());
            statement.setString(3, userDetails.getSurname());
            statement.setString(4, userDetails.getPhone());
            statement.setString(5, userDetails.getAddress());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public UserDetails findUserDetailsByIdUser(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.FIND_USER_DETAILS_BY_ID_USER);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            UserDetails foundUserDetails = null;

            while (resultSet.next()) {
                int idDet = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String surname = resultSet.getString(4);
                String phone = resultSet.getString(5);
                String address = resultSet.getString(6);

                foundUserDetails = new UserDetails(idDet, userId, name, surname, phone, address);
                foundUserDetails.setUser(DAOFactory.getInstance().getUserDAO().findUserById(userId));
            }

            return foundUserDetails;

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public UserDetails findUserDetailsById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.FIND_USER_DETAILS_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            UserDetails foundUserDetails = null;

            while (resultSet.next()) {
                int idDet = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String surname = resultSet.getString(4);
                String phone = resultSet.getString(5);
                String address = resultSet.getString(6);

                foundUserDetails = new UserDetails(idDet, userId, name, surname, phone, address);
                foundUserDetails.setUser(DAOFactory.getInstance().getUserDAO().findUserById(userId));
            }

            return foundUserDetails;

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }


    @Override
    public void deleteUserDetails(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_USER_DETAILS)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public void updateUserDetails(int id, UserDetails userDetails) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.UPDATE_USER_DETAILS);

            preparedStatement.setString(1, userDetails.getName());
            preparedStatement.setString(2, userDetails.getSurname());
            preparedStatement.setString(3, userDetails.getPhone());
            preparedStatement.setString(4, userDetails.getAddress());
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }
}
