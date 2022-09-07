package com.epam.library.project.button.author;


import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorRemoveButtonStrategy implements ButtonStrategy {

    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameterMap().get("author_id")[0]);
        authorService.removeAuthor(id);
        request.setAttribute("authors", authorService.getAllAuthors());
        response.sendRedirect(request.getContextPath() + "/authors");
    }
}
