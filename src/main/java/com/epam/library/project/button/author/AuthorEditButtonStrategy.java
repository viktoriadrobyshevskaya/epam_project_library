package com.epam.library.project.button.author;

import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorEditButtonStrategy implements ButtonStrategy {

    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
            int authorId = Integer.parseInt(request.getParameterMap().get("author_id")[0]);
            request.setAttribute("edit-author", authorService.getAuthorById(authorId));
            request.getRequestDispatcher("/editAuthor.jsp").forward(request, response);

    }
}
