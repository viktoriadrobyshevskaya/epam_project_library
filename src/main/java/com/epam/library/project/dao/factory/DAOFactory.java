package com.epam.library.project.dao.factory;

import com.epam.library.project.dao.AuthorDAO;
import com.epam.library.project.dao.BookDAO;
import com.epam.library.project.dao.UserDAO;
import com.epam.library.project.dao.impl.AuthorDAOImpl;
import com.epam.library.project.dao.impl.BookDAOImpl;
import com.epam.library.project.dao.impl.UserDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final AuthorDAO authorDAO = new AuthorDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}