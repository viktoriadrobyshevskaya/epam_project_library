package com.epam.library.project.service.impl;

import com.epam.library.project.dao.OrderDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Order;
import com.epam.library.project.service.OrderService;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    @Override
    public void addOrder(Order order) throws ServiceException {
        try {
            orderDAO.addOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrder(int id) throws ServiceException {
        try {
            orderDAO.deleteOrder(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOrder(int id, String status) throws ServiceException {
        try {
            orderDAO.updateOrder(id, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try {
            return orderDAO.getAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order getOrderById(int id) throws ServiceException {
        try {
            return orderDAO.getOrderById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByIdUser(int id) throws ServiceException {
        try {
            return orderDAO.getOrdersByIdUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByIdUserAndIdBook(int userId, int bookId) throws ServiceException {
        try {
            return orderDAO.getOrdersByIdUserAndIdBook(userId, bookId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
