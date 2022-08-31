package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Order;

import java.util.List;

public interface OrderDAO {

    public void addOrder(Order order) throws DAOException;

    public void updateOrder(int id, String status) throws DAOException;

    public void deleteOrder(int id) throws DAOException;

    public Order getOrderById(int id) throws DAOException;

    public List<Order> getAllOrders() throws DAOException;

    public List<Order> getOrdersByIdUser(int id) throws DAOException;

    public List<Order> getOrdersByIdUserAndIdBook(int userId, int bookId) throws DAOException;
}
