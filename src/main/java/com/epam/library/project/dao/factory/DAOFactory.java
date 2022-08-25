package com.epam.library.project.dao.factory;

import com.epam.library.project.dao.*;
import com.epam.library.project.dao.impl.*;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserDetailsDAO userDetailsDAO = new UserDetailsDAOImpl();
    private final AuthorDAO authorDAO = new AuthorDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();
    private final RoleDAO roleDAO = new RoleDAOImpl();

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

    public UserDetailsDAO getUserDetailsDAO(){
        return userDetailsDAO;
    }

    public RoleDAO getRoleDAO(){
        return roleDAO;
    }
}
