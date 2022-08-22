package com.epam.library.project.dao.impl;

public final class BookQuery {

    public static String All_BOOKS = "SELECT * FROM books;";
    public static final String ADD_BOOK = "INSERT INTO books (title, id_author, year, numberOfCopies) VALUES (?,?,?,?);";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id_book = ?;";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, id_author = ?, year = ?, numberOfCopies = ? WHERE id_book = ?;";
    public static final String FIND_BOOK = "SELECT * FROM books WHERE id_book = ?;";
}
