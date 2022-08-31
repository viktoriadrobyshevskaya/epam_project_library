package com.epam.library.project.service.impl;

import com.epam.library.project.dao.BookDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Book;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();

    @Override
    public List<Book> showAllBooks() throws ServiceException {
        try {
            return bookDAO.showAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBookById(int id) throws ServiceException {
        try {
            bookDAO.deleteBookById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBookById(int id, Book book) throws ServiceException {
        try {
            bookDAO.updateBookById(id, book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNumberOfCopies(int bookId, int number) throws ServiceException {
        try {
            bookDAO.updateNumberOfCopies(bookId, number);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book findBookById(int bookId) throws ServiceException {
        try {
            return bookDAO.findBookById(bookId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> getBooksBySearch(String surnameAuthor) throws ServiceException {
        try {
            return bookDAO.getBooksBySearch(surnameAuthor);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
