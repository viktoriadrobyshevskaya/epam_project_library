package com.epam.library.project.service;

import com.epam.library.project.entity.Book;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface BookService {

    public List<Book> showAllBooks() throws ServiceException;
    public void addBook(Book book) throws ServiceException;
    public void deleteBookById(int id) throws ServiceException;
    public void updateBookById(int id, Book book) throws ServiceException;
}
