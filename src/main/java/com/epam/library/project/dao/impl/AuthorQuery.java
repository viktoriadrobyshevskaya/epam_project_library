package com.epam.library.project.dao.impl;

public final class AuthorQuery {

    public static String All_AUTHORS = "SELECT * FROM authors;";
    public static final String ADD_AUTHOR = "INSERT INTO authors (name, middleName, surname, yearOfBirth) VALUES (?,?,?,?);";
    public static final String DELETE_AUTHOR = "DELETE FROM authors WHERE id_author = ?;";
    public static final String UPDATE_AUTHOR = "UPDATE authors SET name = ?, middleName = ?, surname = ?, yearOfBirth = ? WHERE id_author = ?;";
    public static final String FIND_AUTHOR = "SELECT * FROM authors WHERE id_author = ?;";
}
