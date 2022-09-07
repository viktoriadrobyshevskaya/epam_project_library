package com.epam.library.project.button.author;

import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorAddButtonStrategy implements ButtonStrategy {

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        request.getRequestDispatcher("/addAuthor.jsp").forward(request, response);
    }
}
