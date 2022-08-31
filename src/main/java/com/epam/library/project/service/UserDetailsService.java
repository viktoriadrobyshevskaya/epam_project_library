package com.epam.library.project.service;

import com.epam.library.project.entity.UserDetails;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface UserDetailsService {

    public List<UserDetails> getAllUserDetails() throws ServiceException;

    public int addUserDetails(UserDetails userDetails) throws ServiceException;

    public UserDetails findUserDetailsByIdUser(int id) throws ServiceException;

    public UserDetails findUserDetailsById(int id) throws ServiceException;

    public void deleteUserDetails(int id) throws ServiceException;

    public void updateUserDetails(int id, UserDetails userDetails) throws ServiceException;
}
