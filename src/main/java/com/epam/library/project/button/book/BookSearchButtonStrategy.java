package com.epam.library.project.button.book;


import com.epam.library.project.button.ButtonStrategy;
import com.epam.library.project.entity.Book;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.exception.ServiceException;
import com.epam.library.project.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookSearchButtonStrategy implements ButtonStrategy {

    private final BookService bookService = ServiceFactory.getInstance().getBookService();

    @Override
    public void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        String surnameAuthor = request.getParameter("search");
        List<Book> books = bookService.getBooksBySearch(surnameAuthor);
        request.setAttribute("books", books);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
