package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers() throws DAOException;

    public void addUser(User user) throws DAOException;

    public User findUser(User user) throws DAOException;

    public void deleteUser(int id) throws DAOException;

    public void updateUser(int userId, User user) throws DAOException;
}
