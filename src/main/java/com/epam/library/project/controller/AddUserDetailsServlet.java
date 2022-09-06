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
        User user = (User) req.getSession().getAttribute("user");
        int userId = (user.getRole().getTitle().equals("user")) ? user.getId() : getUserIdForAdmin(req, user);

        UserDetails userDetails = userDetailsService.findUserDetailsByIdUser(userId);

        int userDetailsId = (userDetails == null) ? userDetailsService.addUserDetails(createModifiedUserDetails(userId, req)) : updateModifiedUserDetails(userDetails, req);

        req.setAttribute("userDetailsId", userDetailsService.findUserDetailsById(userDetailsId).getId());
        req.getRequestDispatcher("/userDetailsOperation").forward(req, resp);
    }

    private int updateModifiedUserDetails(UserDetails userDetails, HttpServletRequest req) throws ServiceException {
        UserDetails modified = createModifiedUserDetails(userDetails.getUserId(), req);

        if (!userDetails.getName().equals(modified.getName())) {
            userDetails.setName(modified.getName());
        }
        if (!userDetails.getSurname().equals(modified.getSurname())) {
            userDetails.setSurname(modified.getSurname());
        }
        if (!userDetails.getPhone().equals(modified.getPhone())) {
            userDetails.setPhone(modified.getPhone());
        }
        if (!userDetails.getAddress().equals(modified.getAddress())) {
            userDetails.setAddress(modified.getAddress());
        }
        userDetailsService.updateUserDetails(userDetails.getId(), userDetails);
        return userDetails.getId();
    }

    private static int getUserIdForAdmin(HttpServletRequest req, User user) {
        return (req.getParameter("user_id").isEmpty()) ? user.getId() : Integer.parseInt(req.getParameter("user_id"));
    }


    private static UserDetails createModifiedUserDetails(int userId, HttpServletRequest req) {
        return new UserDetails(userId, req.getParameter("user_name"), req.getParameter("user_surname"), req.getParameter("user_phone"), req.getParameter("user_address"));
    }
}
