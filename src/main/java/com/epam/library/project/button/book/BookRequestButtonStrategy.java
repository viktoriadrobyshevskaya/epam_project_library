package com.epam.library.project.button.book;

import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.entity.Book;
import com.epam.library.project.entity.Order;
import com.epam.library.project.entity.OrderStatus;
import com.epam.library.project.entity.User;
import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.OrderService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookRequestButtonStrategy implements ButtonStrategy {

    private final BookService bookService = ServiceFactory.getInstance().getBookService();
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {

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
