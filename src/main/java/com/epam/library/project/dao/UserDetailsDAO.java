package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.UserDetails;

import java.util.List;

public interface UserDetailsDAO {

    public List<UserDetails> getAllUserDetails() throws DAOException;

    public int addUserDetails(UserDetails userDetails) throws DAOException;

    public UserDetails findUserDetailsByIdUser(int id) throws DAOException;

    public UserDetails findUserDetailsById(int id) throws DAOException;

    public void deleteUserDetails(int id) throws DAOException;

    public void updateUserDetails(int id, UserDetails userDetails) throws DAOException;
}
