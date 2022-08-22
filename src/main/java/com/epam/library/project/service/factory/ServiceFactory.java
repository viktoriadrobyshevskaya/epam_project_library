package com.epam.library.project.service.factory;

import com.epam.library.project.service.AuthorService;
import com.epam.library.project.service.BookService;
import com.epam.library.project.service.UserService;
import com.epam.library.project.service.impl.AuthorServiceImpl;
import com.epam.library.project.service.impl.BookServiceImpl;
import com.epam.library.project.service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final BookService bookService = new BookServiceImpl();
    private final AuthorService authorService = new AuthorServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BookService getBookService() {
        return bookService;
    }

    public AuthorService getAuthorService(){
        return authorService;
    }

    public UserService getUserService(){
        return userService;
    }
}
