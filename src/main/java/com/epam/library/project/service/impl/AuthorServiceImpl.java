package com.epam.library.project.service.impl;

import com.epam.library.project.dao.AuthorDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Author;
import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();

    @Override
    public void addAuthor(Author author) throws ServiceException {
        try {
            authorDAO.addAuthor(author);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeAuthor(int id) throws ServiceException {
        try {
            authorDAO.removeAuthor(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateAuthor(int id, Author author) throws ServiceException {
        try {
            authorDAO.updateAuthor(id, author);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Author getById(int id) throws ServiceException {
        try {
            return authorDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        try {
            return authorDAO.getAllAuthors();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
