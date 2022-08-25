package com.epam.library.project.dao.impl;

public final class RoleQuery {

    public static String All_ROLES = "SELECT * FROM roles;";
    public static final String ADD_ROLE = "INSERT INTO roles (title) VALUES (?);";
    public static final String DELETE_ROLE = "DELETE FROM roles WHERE id = ?;";
    public static final String FIND_ROLE = "SELECT * FROM roles WHERE id = ?;";
}
