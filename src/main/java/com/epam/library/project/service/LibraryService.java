package com.epam.library.project.service;

import com.epam.library.project.entity.Book;
import com.epam.library.project.service.exception.ServiceException;

public interface LibraryService {
    public void addNewBook(Book book) throws ServiceException;
    public void addEditedBook(Book book) throws ServiceException;
}
