package com.epam.library.project.service;

import com.epam.library.project.entity.Author;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface AuthorService {
    public void addAuthor(Author author) throws ServiceException;

    public void removeAuthor(int id) throws ServiceException;

    public void updateAuthor(int id, Author author) throws ServiceException;

    public Author getAuthorById(int id) throws ServiceException;

    public List<Author> getAllAuthors() throws ServiceException;

}
