package com.epam.library.project.service;

import com.epam.library.project.entity.User;
import com.epam.library.project.service.exception.ServiceException;

public interface ClientService {
    public void singIn(String login, String password) throws ServiceException;
    public void singOut(String login) throws ServiceException;
    public void registration(User user) throws ServiceException;
}
