package com.epam.library.project;

import com.epam.library.project.dao.AuthorDAO;
import com.epam.library.project.dao.BookDAO;
import com.epam.library.project.dao.UserDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.dao.impl.UserDAOImpl;
import com.epam.library.project.entity.Author;
import com.epam.library.project.entity.Book;
import com.epam.library.project.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        DAOFactory daoFactory = DAOFactory.getInstance();
//        BookDAO bookDAO = daoFactory.getBookDAO();
//        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
//        try {
//            authorDAO.addAuthor(new Author("Николай", "Васильевич", "Гоголь", 1809));
//            bookDAO.addBook(new Book("Капитанская дочка", 1, "1836", 2));
//        } catch (DAOException e) {
//            throw new RuntimeException(e);
//        }


//        DAOFactory daoFactory = DAOFactory.getInstance();
//        BookDAO bookDAO = daoFactory.getBookDAO();
//        try {
//            List<Book> books = bookDAO.showAllBooks();
//            System.out.println(books);
//        } catch (DAOException e) {
//            throw new RuntimeException(e);
//        }

//        DAOFactory daoFactory = DAOFactory.getInstance();
//        BookDAO bookDAO = daoFactory.getBookDAO();
//        try {
//           Book books = bookDAO.findBookById(2);
//            System.out.println(books);
//        } catch (DAOException e) {
//            throw new RuntimeException(e);
//        }

//        DAOFactory daoFactory = DAOFactory.getInstance();
//        UserDAO userDAO = daoFactory.getUserDAO();
//        try {
//            userDAO.deleteUser(2);
//        } catch (DAOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
