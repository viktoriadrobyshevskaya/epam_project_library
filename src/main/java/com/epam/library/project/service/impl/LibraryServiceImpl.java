package com.epam.library.project.service.impl;

import com.epam.library.project.entity.Book;
import com.epam.library.project.service.LibraryService;
import com.epam.library.project.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void addNewBook(Book book) throws ServiceException {
        try (Connection connection = DriverManager.getConnection("")) {

        } catch (SQLException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void addEditedBook(Book book) throws ServiceException {
        try (Connection connection = DriverManager.getConnection("")) {

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
