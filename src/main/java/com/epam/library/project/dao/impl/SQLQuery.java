package com.epam.library.project.dao.impl;

public final class SQLQuery {

    public static String All_AUTHORS = "SELECT * FROM authors;";
    public static final String ADD_AUTHOR = "INSERT INTO authors (name, middleName, surname, yearOfBirth) VALUES (?,?,?,?);";
    public static final String DELETE_AUTHOR = "DELETE FROM authors WHERE id_author = ?;";
    public static final String UPDATE_AUTHOR = "UPDATE authors SET name = ?, middleName = ?, surname = ?, yearOfBirth = ? WHERE id_author = ?;";
    public static final String FIND_AUTHOR = "SELECT * FROM authors WHERE id_author = ?;";

    public static String All_BOOKS = "SELECT * FROM books;";
    public static final String ADD_BOOK = "INSERT INTO books (title, id_author, year, numberOfCopies) VALUES (?,?,?,?);";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id_book = ?;";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, id_author = ?, year = ?, numberOfCopies = ? WHERE id_book = ?;";
    public static final String FIND_BOOK = "SELECT * FROM books WHERE id_book = ?;";

    public static String All_ROLES = "SELECT * FROM roles;";
    public static final String ADD_ROLE = "INSERT INTO roles (title) VALUES (?);";
    public static final String DELETE_ROLE = "DELETE FROM roles WHERE id = ?;";
    public static final String FIND_ROLE = "SELECT * FROM roles WHERE id = ?;";

    public static final String All_USER_DETAILS = "SELECT * FROM usersdetails;";
    public static final String ADD_USER_DETAILS = "INSERT INTO usersdetails (id_user, name, surname, phone, address) VALUES (?,?,?,?,?);";
    public static final String FIND_USER_DETAILS_BY_ID = "SELECT * FROM usersdetails WHERE id_user = ?;";
    public static final String DELETE_USER_DETAILS = "DELETE FROM usersdetails WHERE id = ?;";
    public static final String UPDATE_USER_DETAILS = "UPDATE usersdetails SET name = ?, surname = ?, phone = ?, address = ? WHERE id = ?;";

    public static final String UPDATE_USER = "UPDATE users SET password = ?, role_id = ? WHERE id_user = ?;";
    public static final String All_USERS = "SELECT * FROM users;";
    public static final String FIND_USER = "SELECT * FROM users WHERE login = ? AND password = ?;";
    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id_user = ?;";
    public static final String DELETE_USER = "DELETE FROM users WHERE id_user = ?;";
    public static final String ADD_USER = "INSERT INTO users (login, password, role_id) VALUES (?,?,?);";

    public static final String ADD_ORDER = "INSERT INTO orders (id_user, id_book) VALUES (?,?);";
    public static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?;";
}
