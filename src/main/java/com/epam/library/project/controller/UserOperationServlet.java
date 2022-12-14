package com.epam.library.project.controller;

import com.epam.library.project.service.UserDetailsService;
import com.epam.library.project.service.UserService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userOperation", urlPatterns = {"/userOperation"})
public class UserOperationServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(UserOperationServlet.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final UserDetailsService userDetailsService = ServiceFactory.getInstance().getUserDetailsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/users").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/users").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        if (request.getParameterMap().get("remove") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("user_id")[0]);
            userService.deleteUser(id);
            request.setAttribute("users", userService.getAllUsers());
            response.sendRedirect(request.getContextPath() + "/users");
        } else if (request.getParameterMap().get("edit") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("user_id")[0]);
            request.setAttribute("edit-user", userService.findUserById(id));
            request.getRequestDispatcher("/editUser.jsp").forward(request, response);
        } else if (request.getParameterMap().get("showDetails") != null) {
            int id = Integer.parseInt(request.getParameter("user_id"));
            request.setAttribute("user_ID", id);
            request.setAttribute("userDetails", userDetailsService.findUserDetailsByIdUser(id));
            request.getRequestDispatcher("/showDetails.jsp").forward(request, response);
        } else if (request.getParameterMap().get("addUser") != null) {
            response.sendRedirect(request.getContextPath() + "/addUser.jsp");
        }
    }
}
