package com.epam.library.project.controller;

import com.epam.library.project.service.UserDetailsService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userDetailsOperation", urlPatterns = {"/userDetailsOperation"})
public class UserDetailsOperationServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        if (request.getParameterMap().get("editDetails") != null) {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            request.setAttribute("edit-userDetail", userDetailsService.findUserDetailsByIdUser(userId));
            request.getRequestDispatcher("/editUserDetails.jsp").forward(request, response);
        } else {
            if (request.getAttribute("userDetailsId") != null) {
                int userDetailsId = (int) request.getAttribute("userDetailsId");
                request.setAttribute("userDetails", userDetailsService.findUserDetailsById(userDetailsId));
            }
            request.getRequestDispatcher("/showDetails.jsp").forward(request, response);
        }
    }

}
