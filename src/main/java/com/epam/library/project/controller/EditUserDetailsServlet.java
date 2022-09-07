package com.epam.library.project.controller;

import com.epam.library.project.entity.UserDetails;
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

@WebServlet(name = "editUserDetails", urlPatterns = {"/editUserDetails"})
public class EditUserDetailsServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(EditUserDetailsServlet.class);
    private final UserDetailsService userDetailsService = ServiceFactory.getInstance().getUserDetailsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editUserDetails.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editUserDetails.jsp").forward(req, resp);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String newName = request.getParameter("user_name");
        String newSurname = request.getParameter("user_surname");
        String newPhone = request.getParameter("user_phone");
        String newAddress = request.getParameter("user_address");

        userDetailsService.updateUserDetails(id, new UserDetails(user_id, newName, newSurname, newPhone, newAddress));

        request.setAttribute("userDetailsId", id);
        request.getRequestDispatcher("/userDetailsOperation").forward(request, response);
    }

}
