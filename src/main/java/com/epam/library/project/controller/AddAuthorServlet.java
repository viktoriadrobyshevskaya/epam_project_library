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

@WebServlet(name = "addAuthor", urlPatterns = {"/addAuthor"})
public class AddAuthorServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddAuthorServlet.class);
    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET: creation of author");
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/addAuthor.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST: creation of author");
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/addAuthor.jsp").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        Author newAuthor = new Author();
        newAuthor.setName(req.getParameter("author_name"));
        newAuthor.setMiddleName(req.getParameter("author_middleName"));
        newAuthor.setSurname(req.getParameter("author_surname"));
        newAuthor.setYearOfBirth(Integer.parseInt(req.getParameter("author_year")));
        authorService.addAuthor(newAuthor);
        logger.debug(String.format("saving new author to db [%s]", newAuthor));
        List<Author> authors = authorService.getAllAuthors();
        req.setAttribute("authors", authors);
        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}
