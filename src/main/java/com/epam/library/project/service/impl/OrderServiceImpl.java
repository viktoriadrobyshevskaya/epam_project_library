package com.epam.library.project.service.impl;

import com.epam.library.project.dao.OrderDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Order;
import com.epam.library.project.service.OrderService;
import com.epam.library.project.service.exception.ServiceException;

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
}
