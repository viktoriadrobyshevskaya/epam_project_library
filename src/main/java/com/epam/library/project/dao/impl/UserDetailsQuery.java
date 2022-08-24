package com.epam.library.project.dao.impl;

public final class UserDetailsQuery {

    public static final String All_USER_DETAILS = "SELECT * FROM usersdetails;";
    public static final String ADD_USER_DETAILS = "INSERT INTO usersdetails (id_user, name, surname, phone, address) VALUES (?,?,?,?,?);";
    public static final String FIND_USER_DETAILS_BY_ID = "SELECT * FROM usersdetails WHERE id_user = ?;";
    public static final String DELETE_USER_DETAILS = "DELETE FROM usersdetails WHERE id = ?;";
    public static final String UPDATE_USER_DETAILS = "UPDATE usersdetails SET name = ?, surname = ?, phone = ?, address = ? WHERE id = ?;";
}
