package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.RoleDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Book;
import com.epam.library.project.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public void addRole(Role role) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(RoleQuery.ADD_ROLE)) {
            statement.setString(1, role.getTitle());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public void deleteRole(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(RoleQuery.DELETE_ROLE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Something wrong!" + e);
        }
    }

    @Override
    public Role getRoleById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(RoleQuery.FIND_ROLE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            Role role = null;

            while (resultSet.next()) {
                int id_role = resultSet.getInt(1);
                String title = resultSet.getString(2);

                role = new Role(id_role, title);
            }

            return role;

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Role> getAllRoles() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(RoleQuery.All_ROLES);
            resultSet = preparedStatement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                roles.add(new Role(id, title));
            }
            return roles;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }
}
