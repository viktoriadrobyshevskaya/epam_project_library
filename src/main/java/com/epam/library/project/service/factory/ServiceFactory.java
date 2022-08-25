package com.epam.library.project.service.factory;

import com.epam.library.project.entity.UserDetails;
import com.epam.library.project.service.*;
import com.epam.library.project.service.impl.*;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final BookService bookService = new BookServiceImpl();
    private final AuthorService authorService = new AuthorServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final UserDetailsService userDetailsService = new UserDetailsServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BookService getBookService() {
        return bookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
