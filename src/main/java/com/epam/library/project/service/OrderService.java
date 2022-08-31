package com.epam.library.project.service;

import com.epam.library.project.entity.Order;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    public void addOrder(Order order) throws ServiceException;

    public void deleteOrder(int id) throws ServiceException;

    public void updateOrder(int id, String status) throws ServiceException;

    public List<Order> getAllOrders() throws ServiceException;

    public Order getOrderById(int id) throws ServiceException;

    public List<Order> getOrdersByIdUser(int id) throws ServiceException;

    public List<Order> getOrdersByIdUserAndIdBook(int userId, int bookId) throws ServiceException;
}
