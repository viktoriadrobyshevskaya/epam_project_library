package com.epam.library.project.button.book;


import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.OrderService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookRemoveButtonStrategy implements ButtonStrategy {

    private final BookService bookService = ServiceFactory.getInstance().getBookService();

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameterMap().get("book_id")[0]);
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath() + "/books");
    }
}
