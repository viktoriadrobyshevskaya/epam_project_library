package com.epam.library.project.service.impl;

import com.epam.library.project.dao.UserDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.User;
import com.epam.library.project.service.UserService;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addUser(User user) throws ServiceException {
        try {
            userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUser(User user) throws ServiceException {
        try {
            return userDAO.findUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        try {
            return userDAO.findUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        try {
            userDAO.deleteUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUser(int userId, User user) throws ServiceException {
        try {
            userDAO.updateUser(userId, user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
