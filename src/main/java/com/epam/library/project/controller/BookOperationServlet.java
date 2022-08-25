package com.epam.library.project.controller;

import com.epam.library.project.service.BookService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookOperation", urlPatterns = {"/bookOperation"})
public class BookOperationServlet extends HttpServlet {

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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        if (request.getParameterMap().get("remove") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
            bookService.deleteBookById(id);
            request.setAttribute("books", bookService.showAllBooks());
            response.sendRedirect(request.getContextPath() + "/books");
        } else if (request.getParameterMap().get("edit") != null) {
            int bookId = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
            request.setAttribute("edit-book", bookService.findBookById(bookId));
            request.getRequestDispatcher("/editBook.jsp").forward(request, response);
        }else if (request.getParameterMap().get("addBook") != null) {
            request.getRequestDispatcher("/addBook.jsp").forward(request, response);
        }else if (request.getParameterMap().get("read") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
            request.getRequestDispatcher("/read.jsp").forward(request, response);
        }else if (request.getParameterMap().get("order") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        }
    }
}
