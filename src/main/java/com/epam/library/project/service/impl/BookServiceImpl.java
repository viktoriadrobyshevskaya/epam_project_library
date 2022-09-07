package com.epam.library.project.service.impl;

import com.epam.library.project.dao.BookDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Book;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private final Logger logger = org.apache.log4j.Logger.getLogger(BookServiceImpl.class);


    @Override
    public List<Book> showAllBooks() throws ServiceException {
        try {
            List<Book> books = bookDAO.showAllBooks();
            logger.debug(String.format("getting all books from db [size:%s]", books.size()));
            return books;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            logger.debug(String.format("saving new book to db [%s]", book));
            bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBookById(int id) throws ServiceException {
        try {
            logger.debug(String.format("deleting a book  from db by ID [%s]", id));
            bookDAO.deleteBookById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBookById(int id, Book book) throws ServiceException {
        try {
            logger.debug(String.format("updating a book  from db by ID [%s]", id));
            bookDAO.updateBookById(id, book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNumberOfCopies(int bookId, int number) throws ServiceException {
        try {
            logger.debug(String.format("updating number [%s] for book [%s]", bookId, number));
            bookDAO.updateNumberOfCopies(bookId, number);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book findBookById(int bookId) throws ServiceException {
        try {
            logger.debug(String.format("find a book from db by ID [%s]", bookId));
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
