package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Author;

import java.util.List;

public interface AuthorDAO {
    public void addAuthor(Author author) throws DAOException;
    public void removeAuthor(int id) throws DAOException;
    public Author getById(int id) throws DAOException;
    public List<Author> getAllAuthors() throws DAOException;

}
