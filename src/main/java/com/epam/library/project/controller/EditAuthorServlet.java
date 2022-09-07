package com.epam.library.project.controller;

import com.epam.library.project.entity.Author;
import com.epam.library.project.service.AuthorService;
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

@WebServlet(name = "editAuthor", urlPatterns = {"/editAuthor"})
public class EditAuthorServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(AddAuthorServlet.class);
    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editAuthor.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/editAuthor.jsp").forward(req, resp);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int author_id = Integer.parseInt(request.getParameterMap().get("author_id")[0]);

        String newName = request.getParameter("author_name");
        String newMiddleName = request.getParameter("author_middleName");
        String newSurname = request.getParameter("author_surname");
        int newYearOfBirthday = Integer.parseInt(request.getParameter("author_year"));

        authorService.updateAuthor(author_id, new Author(newName, newMiddleName, newSurname, newYearOfBirthday));

        List<Author> authors = authorService.getAllAuthors();
        request.setAttribute("authors", authors);
        response.sendRedirect(request.getContextPath() + "/authors");
    }

}
