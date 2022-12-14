package com.epam.library.project.service;

import com.epam.library.project.entity.Book;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface BookService {

    public List<Book> showAllBooks() throws ServiceException;

    public void addBook(Book book) throws ServiceException;

    public void deleteBookById(int id) throws ServiceException;

    public void updateBookById(int id, Book book) throws ServiceException;

    public void updateNumberOfCopies(int bookId, int number) throws ServiceException;

    public Book findBookById(int bookId) throws ServiceException;

    public List<Book> getBooksBySearch(String surnameAuthor) throws ServiceException;
}
