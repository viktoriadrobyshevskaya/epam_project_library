package com.epam.library.project.controller;

import com.epam.library.project.entity.User;
import com.epam.library.project.service.OrderService;
import com.epam.library.project.service.UserDetailsService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "orderOperation", urlPatterns = {"/orderOperation"})
public class OrderOperationServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(AddAuthorServlet.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final UserDetailsService userDetailsService = ServiceFactory.getInstance().getUserDetailsService();

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
        if (request.getParameterMap().get("orders") != null) {
            int userId = ((User) request.getSession().getAttribute("user")).getId();
            request.setAttribute("orders", orderService.getOrdersByIdUser(userId));
            request.getRequestDispatcher("/orders.jsp").forward(request, response);

        } else if (request.getParameterMap().get("showDetails") != null) {
            int userId = ((User) request.getSession().getAttribute("user")).getId();
            request.setAttribute("userDetails", userDetailsService.findUserDetailsByIdUser(userId));
            request.getRequestDispatcher("/showDetails.jsp").forward(request, response);
        }
    }
}
