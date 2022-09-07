package com.epam.library.project.controller;

import com.epam.library.project.button.ButtonProvider;
import com.epam.library.project.button.ButtonType;
import com.epam.library.project.button.CommonButtonStrategyHandler;
import com.epam.library.project.button.book.BookButtonStrategyProvider;
import com.epam.library.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookOperation", urlPatterns = {"/bookOperation"})
public class BookOperationServlet extends HttpServlet {

    private final Logger logger = org.apache.log4j.Logger.getLogger(BookOperationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/books").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            logger.error("Error during creation of authors", e);
            req.setAttribute("problem", "Произошла ошибка! Обратитесь в тех.поддержку.");
            req.getRequestDispatcher("/books").forward(req, resp);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String button = BookButtonStrategyProvider.getActiveButtonNameByRequest(request);
        ButtonProvider buttonProvider = CommonButtonStrategyHandler.getInstance().getButtonProvider(ButtonType.BOOK);
        buttonProvider.getButtonStrategy(button).execution(request, response);
    }
}
