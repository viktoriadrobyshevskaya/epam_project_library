package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Order;

public interface OrderDAO {

    public void addOrder(Order order) throws DAOException;

    public void deleteOrder(int id) throws DAOException;

}
