package com.epam.library.project.controller;

import com.epam.library.project.entity.Order;
import com.epam.library.project.entity.OrderStatus;
import com.epam.library.project.entity.User;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.OrderService;
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

@WebServlet(name = "orders", urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(OrderServlet.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final BookService bookService = ServiceFactory.getInstance().getBookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/orders").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/orders").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        request.setAttribute("currentPage", "orders");
        if (request.getParameterMap().keySet().size() == 0) {
            List<Order> orders = orderService.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            int idBook = Integer.parseInt(request.getParameter("id_book"));
            int idOrder = Integer.parseInt(request.getParameter("id_order"));
            if (request.getParameterMap().get("approve") != null) {
                int number = bookService.findBookById(idBook).getNumberOfCopies();
                if (orderService.getOrderById(idOrder).getStatus().equals("IN_PROGRESS")) {
                    bookService.updateNumberOfCopies(idBook, number - 1);
                }
                orderService.updateOrder(idOrder, OrderStatus.APPROVED.toString());
            } else if (request.getParameterMap().get("cancel") != null) {
                orderService.updateOrder(idOrder, OrderStatus.CANCELLED.toString());
            } else if (request.getParameterMap().get("accept") != null) {
                int number = bookService.findBookById(idBook).getNumberOfCopies();
                orderService.updateOrder(idOrder, OrderStatus.ACCEPTED.toString());
                orderService.deleteOrder(idOrder);
                bookService.updateNumberOfCopies(idBook, number + 1);
            } else if (request.getParameterMap().get("delete") != null) {
                orderService.deleteOrder(idOrder);
            }
            User user = (User) request.getSession().getAttribute("user");
            List<Order> orders = (user.getRole().getTitle().equals("admin")) ? orderService.getAllOrders() : orderService.getOrdersByIdUser(user.getId());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/orders.jsp").forward(request, response);
        }
    }
}
