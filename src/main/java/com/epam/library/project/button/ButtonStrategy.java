package com.epam.library.project.button;

import com.epam.library.project.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ButtonStrategy {

        void execution(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException;
}
