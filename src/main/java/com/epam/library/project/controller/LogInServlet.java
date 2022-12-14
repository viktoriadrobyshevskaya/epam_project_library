package com.epam.library.project.controller;

import com.epam.library.project.entity.User;
import com.epam.library.project.service.RoleService;
import com.epam.library.project.service.UserService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logIn", urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(LogInServlet.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final RoleService roleService = ServiceFactory.getInstance().getRoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/logIn.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String login = req.getParameter("loginInput");
        String password = req.getParameter("passwordInput");
        User user = userService.findUser(new User(login, password));
        HttpSession session = req.getSession(true);
        if (user != null){
            user.setRole(roleService.getRoleById(user.getRoleId()));
            session.setAttribute("user", user);
            session.setAttribute("roles", roleService.getAllRoles());
            resp.sendRedirect(req.getContextPath() + "/books");
        } else {
            req.setAttribute("error", "Неверный логин или пароль!");
            req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
        }
    }
}
