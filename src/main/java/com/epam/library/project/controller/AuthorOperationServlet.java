package com.epam.library.project.controller;

import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "authorOperation", urlPatterns = {"/authorOperation"})
public class AuthorOperationServlet extends HttpServlet {

    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        if (request.getParameterMap().get("remove") != null) {
            int id = Integer.parseInt(request.getParameterMap().get("author_id")[0]);
            authorService.removeAuthor(id);
            request.setAttribute("authors", authorService.getAllAuthors());
            response.sendRedirect(request.getContextPath() + "/authors");
        } else if (request.getParameterMap().get("edit") != null) {
            int authorId = Integer.parseInt(request.getParameterMap().get("author_id")[0]);
            request.setAttribute("edit-author", authorService.getAuthorById(authorId));
            request.getRequestDispatcher("/editAuthor.jsp").forward(request, response);
        }else if (request.getParameterMap().get("addAuthor") != null) {
            request.getRequestDispatcher("/addAuthor.jsp").forward(request, response);
        }
    }
}
