package com.epam.library.project.button.book;


import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookAddButtonStrategy implements ButtonStrategy {

    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        request.setAttribute("authors", authorService.getAllAuthors());
        request.getRequestDispatcher("/addBook.jsp").forward(request, response);
    }
}
