package com.epam.library.project.dao.impl;

import com.epam.library.project.dao.OrderDAO;
import com.epam.library.project.dao.connection.ConnectionPool;
import com.epam.library.project.dao.connection.ConnectionPoolException;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public void addOrder(Order order) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ADD_ORDER)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getBookId());
            statement.setString(3, order.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }

    @Override
    public void updateOrder(int id, String status) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.UPDATE_ORDER);

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement);
        }
    }

    @Override
    public void deleteOrder(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_ORDER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        }
    }


    @Override
    public List<Order> getAllOrders() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.ALL_ORDERS);
            resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idUser = resultSet.getInt(2);
                int idBook = resultSet.getInt(3);
                String orderStatus = resultSet.getString(4);

                Order order = new Order(id, idUser, idBook, orderStatus);
                order.setUser(DAOFactory.getInstance().getUserDAO().findUserById(idUser));
                order.setBook(DAOFactory.getInstance().getBookDAO().findBookById(idBook));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Order getOrderById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.FIND_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            Order order = null;

            while (resultSet.next()) {
                int idUser = resultSet.getInt(2);
                int idBook = resultSet.getInt(3);
                String status = resultSet.getString(4);

                order = new Order(id, idUser, idBook, status);

            }
            return order;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Order> getOrdersByIdUser(int currentUserId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.FIND_ORDERS_BY_ID_USER);
            preparedStatement.setInt(1, currentUserId);
            resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idUser = resultSet.getInt(2);
                int idBook = resultSet.getInt(3);
                String orderStatus = resultSet.getString(4);

                Order order = new Order(id, idUser, idBook, orderStatus);
                order.setUser(DAOFactory.getInstance().getUserDAO().findUserById(idUser));
                order.setBook(DAOFactory.getInstance().getBookDAO().findBookById(idBook));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Order> getOrdersByIdUserAndIdBook(int userId, int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQLQuery.FIND_ORDERS_BY_ID_USER_AND_ID_BOOK);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idUser = resultSet.getInt(2);
                int idBook = resultSet.getInt(3);
                String orderStatus = resultSet.getString(4);

                Order order = new Order(id, idUser, idBook, orderStatus);
                order.setUser(DAOFactory.getInstance().getUserDAO().findUserById(idUser));
                order.setBook(DAOFactory.getInstance().getBookDAO().findBookById(idBook));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException("Something wrong!" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Something wrong with Connection Pool!" + e);
        } finally {
            ConnectionPool.getInstance().closeConnectionQueue(connection, preparedStatement, resultSet);
        }
    }
}
