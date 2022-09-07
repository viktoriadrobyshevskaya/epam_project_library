package com.epam.library.project.controller;

import com.epam.library.project.button.ButtonProvider;
import com.epam.library.project.button.ButtonType;
import com.epam.library.project.button.CommonButtonStrategyHandler;
import com.epam.library.project.button.book.BookButtonStrategyProvider;
import com.epam.library.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookOperation", urlPatterns = {"/bookOperation"})
public class BookOperationServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(AddAuthorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/books").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/books").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        if (request.getParameterMap().get("remove") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
            bookService.deleteBookById(id);
            response.sendRedirect(request.getContextPath() + "/books");
        } else if (request.getParameterMap().get("edit") != null) {
            int bookId = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
            request.setAttribute("authors", authorService.getAllAuthors());
            request.setAttribute("edit-book", bookService.findBookById(bookId));
            request.getRequestDispatcher("/editBook.jsp").forward(request, response);
        } else if (request.getParameterMap().get("addBook") != null) {
            request.setAttribute("authors", authorService.getAllAuthors());
            request.getRequestDispatcher("/addBook.jsp").forward(request, response);
        } else if (request.getParameterMap().get("search") != null) {
            String surnameAuthor = request.getParameterMap().get("search")[0];
            List<Book> books = bookService.getBooksBySearch(surnameAuthor);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/books.jsp").forward(request, response);
        } else if (request.getParameterMap().get("request") != null) {
            int userId = ((User) request.getSession().getAttribute("user")).getId();
            int bookId = Integer.parseInt(request.getParameter("book_id"));

            Book book = bookService.findBookById(bookId);
            List<Order> orders = orderService.getOrdersByIdUserAndIdBook(userId, bookId);
            if (book.getNumberOfCopies() == 0) {
                request.getSession().setAttribute("error", String.format("Сейчас книги '%s' нет в наличии", book.getTitle()));
            } else if (orders.size() > 0) {
                request.getSession().setAttribute("error", String.format("Запрос для книги '%s' уже был отправлен", book.getTitle()));
            } else {
                orderService.addOrder(new Order(userId, bookId, OrderStatus.IN_PROGRESS.toString()));
            }
            response.sendRedirect(request.getContextPath() + "/books");
        }
    }
}
