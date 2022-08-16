package com.epam.library.project.service.factory;

import com.epam.library.project.service.BookService;
import com.epam.library.project.service.ClientService;
import com.epam.library.project.service.LibraryService;
import com.epam.library.project.service.impl.BookServiceImpl;
import com.epam.library.project.service.impl.ClientServiceImpl;
import com.epam.library.project.service.impl.LibraryServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
