package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.User;

import java.util.List;

public interface UserDAO {
    public void signIn(String login, String password) throws DAOException;
    public void registration(User user) throws DAOException;
    public void updatePassword(int userId, int password) throws DAOException;
    public void updateEmail(int userId, int email) throws DAOException;
    public List<User> getAllUsers() throws DAOException;
    public User findUserByLogin(String login) throws DAOException;
    public void deleteUser(int id) throws DAOException;
}
