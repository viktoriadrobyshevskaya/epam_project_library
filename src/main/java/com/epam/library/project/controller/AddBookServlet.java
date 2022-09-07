package com.epam.library.project.controller;

import com.epam.library.project.entity.Book;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addBook", urlPatterns = {"/addBook"})
public class AddBookServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(AddBookServlet.class);
    private final BookService bookService = ServiceFactory.getInstance().getBookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET: creation of book");
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку. " + e);
            req.getRequestDispatcher("/addBook.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST: creation of book");
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку. " + e);
            req.getRequestDispatcher("/addBook.jsp").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        Book newBook = new Book();
        newBook.setTitle(req.getParameter("book_title"));
        newBook.setId_author(Integer.parseInt(req.getParameter("author")));
        newBook.setYearOfPublication(req.getParameter("book_year"));
        newBook.setNumberOfCopies(Integer.parseInt(req.getParameter("book_number")));
        bookService.addBook(newBook);
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
