package com.epam.library.project.service;

import com.epam.library.project.entity.User;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers() throws ServiceException;

    public void addUser(User user) throws ServiceException;

    public User findUser(User user) throws ServiceException;

    public User findUserById(int id) throws ServiceException;

    public void deleteUser(int id) throws ServiceException;

    public void updateUser(int userId, User user) throws ServiceException;
}
