package com.epam.library.project.service.impl;

import com.epam.library.project.dao.UserDAO;
import com.epam.library.project.dao.UserDetailsDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.UserDetails;
import com.epam.library.project.service.UserDetailsService;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsDAO userDetailsDAO = DAOFactory.getInstance().getUserDetailsDAO();

    @Override
    public List<UserDetails> getAllUserDetails() throws ServiceException {
        try {
            return userDetailsDAO.getAllUserDetails();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addUserDetails(UserDetails userDetails) throws ServiceException {
        try {
            userDetailsDAO.addUserDetails(userDetails);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDetails findUserDetailsById(int id) throws ServiceException {
        try {
            return userDetailsDAO.findUserDetailsById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUserDetails(int id) throws ServiceException {
        try {
            userDetailsDAO.deleteUserDetails(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserDetails(int id, UserDetails userDetails) throws ServiceException {
        try {
            userDetailsDAO.updateUserDetails(id, userDetails);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
