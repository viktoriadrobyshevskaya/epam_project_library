package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Book;

import java.util.List;

public interface BookDAO {

    public List<Book> showAllBooks() throws DAOException;
    public void addBook(Book book) throws DAOException;
    public void deleteBookById(int id) throws DAOException;
    public void updateBookById(int id, Book book) throws DAOException;

}