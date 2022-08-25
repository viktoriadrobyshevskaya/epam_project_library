package com.epam.library.project.controller;

import com.epam.library.project.entity.User;
import com.epam.library.project.service.UserService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logIn", urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/logIn.jsp").forward(req, resp);

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
        String login = req.getParameter("loginInput");
        String password = req.getParameter("passwordInput");
        User user = userService.findUser(new User(login, password));
        HttpSession session = req.getSession(true);
        if (user != null){
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/books");
        } else {
            req.setAttribute("error", "User with this data does not exist!");
            req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
        }
    }
}
