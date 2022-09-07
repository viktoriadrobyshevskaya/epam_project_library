package com.epam.library.project.controller;

import com.epam.library.project.entity.User;
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
import java.util.List;

@WebServlet(name = "editUser", urlPatterns = {"/editUser"})
public class EditUserServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(EditUserServlet.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(request.getParameterMap().get("user_id")[0]);
        String login = request.getParameter("user_login");
        String password = request.getParameter("user_password");
        int id_role = Integer.parseInt(request.getParameter("role"));

        userService.updateUser(id, new User(login, password, id_role));

        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        response.sendRedirect(request.getContextPath() + "/users");
    }

}
