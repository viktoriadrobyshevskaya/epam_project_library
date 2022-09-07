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
import java.util.List;

@WebServlet(name = "editBook", urlPatterns = {"/editBook"})
public class EditBookServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(EditBookServlet.class);
    private final BookService bookService = ServiceFactory.getInstance().getBookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editBook.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editBook.jsp").forward(req, resp);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int book_id = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
        String newTitle = request.getParameter("book_title");
        int newAuthor = Integer.parseInt(request.getParameter("author"));
        String newYear = request.getParameter("book_year");
        int newCopies = Integer.parseInt(request.getParameter("book_number"));

        bookService.updateBookById(book_id, new Book(newTitle, newAuthor, newYear, newCopies));

        List<Book> books = bookService.showAllBooks();
        request.setAttribute("books", books);
        response.sendRedirect(request.getContextPath() + "/books");
    }

}
