package com.epam.library.project.controller;

import com.epam.library.project.entity.Author;
import com.epam.library.project.entity.Book;
import com.epam.library.project.service.AuthorService;
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
    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

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
        List<Author> authors = authorService.getAllAuthors();
            req.setAttribute("currentPage", "books");
            req.setAttribute("books", books);
            req.setAttribute("authors", authors);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
