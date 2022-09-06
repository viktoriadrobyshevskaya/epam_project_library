package com.epam.library.project.controller;

import com.epam.library.project.entity.Book;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addBook", urlPatterns = {"/addBook"})
public class AddBookServlet extends HttpServlet {

    private final BookService bookService = ServiceFactory.getInstance().getBookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
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
