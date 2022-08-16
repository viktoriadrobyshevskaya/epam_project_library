package com.epam.library.project.service.impl;

import com.epam.library.project.dao.UserDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.User;
import com.epam.library.project.service.ClientService;
import com.epam.library.project.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {
    @Override
    public void singIn(String login, String password) throws ServiceException {
        // проверяем параметры
        if(login == null){
            throw new ServiceException("Incorrect login");
        }
        //реализуем функционал логинализации пользователя в системе
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.signIn(login, password);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        //
    }

    @Override
    public void singOut(String login) throws ServiceException {

    }

    @Override
    public void registration(User user) throws ServiceException{

    }
}
