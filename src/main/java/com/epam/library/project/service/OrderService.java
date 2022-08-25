package com.epam.library.project.service;

import com.epam.library.project.entity.Order;
import com.epam.library.project.service.exception.ServiceException;

public interface OrderService {

    public void addOrder(Order order) throws ServiceException;

    public void deleteOrder(int id) throws ServiceException;
}
