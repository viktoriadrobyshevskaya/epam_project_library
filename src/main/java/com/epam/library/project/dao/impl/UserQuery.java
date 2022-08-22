package com.epam.library.project.dao.impl;

public final class UserQuery {

    public static final String UPDATE_USER = "UPDATE users SET password = ?, role_id = ? WHERE id_user = ?;";
    public static final String All_USERS = "SELECT * FROM users;";
    public static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?;";
    public static final String FIND_USER = "SELECT * FROM users WHERE login = ? AND password = ?;";
    public static final String DELETE_USER = "DELETE FROM users WHERE id_user = ?";
}
