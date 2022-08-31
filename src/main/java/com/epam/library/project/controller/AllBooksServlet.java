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
import java.util.List;

@WebServlet(name = "book", urlPatterns = {"/books"})
public class AllBooksServlet extends HttpServlet {

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
        List<Book> books = bookService.showAllBooks();

        String errorMessage = (String) req.getSession().getAttribute("error");
        req.setAttribute("error", errorMessage);
        req.getSession().removeAttribute("error");

        req.setAttribute("books", books);
        req.setAttribute("currentPage", "books");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
