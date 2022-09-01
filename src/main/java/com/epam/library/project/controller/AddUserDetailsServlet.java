package com.epam.library.project.controller;

import com.epam.library.project.entity.User;
import com.epam.library.project.entity.UserDetails;
import com.epam.library.project.service.UserDetailsService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addUserDetails", urlPatterns = {"/addUserDetails"})
public class AddUserDetailsServlet extends HttpServlet {

    private final UserDetailsService userDetailsService = ServiceFactory.getInstance().getUserDetailsService();

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

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(Integer.parseInt(req.getParameter("user_id")));
        userDetails.setName(req.getParameter("user_name"));
        userDetails.setSurname(req.getParameter("user_surname"));
        userDetails.setPhone(req.getParameter("user_phone"));
        userDetails.setAddress(req.getParameter("user_address"));
        int userDetailsId = userDetailsService.addUserDetails(userDetails);

        req.setAttribute("userDetailsId", userDetailsService.findUserDetailsById(userDetailsId).getId());
        req.getRequestDispatcher("/userDetailsOperation").forward(req, resp);
    }
}
