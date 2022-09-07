package com.epam.library.project.controller;

import com.epam.library.project.button.author.AuthorButtonStrategyProvider;
import com.epam.library.project.button.ButtonProvider;
import com.epam.library.project.button.ButtonType;
import com.epam.library.project.button.CommonButtonStrategyHandler;
import com.epam.library.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "authorOperation", urlPatterns = {"/authorOperation"})
public class AuthorOperationServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(AddAuthorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/authors.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/authors.jsp").forward(req, resp);
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
